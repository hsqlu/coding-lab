# Kong Mesh Global Control Plane 迁移到 Konnect 计划

## 1. 文档目的

本文档用于评审将现有 self-managed Kong Mesh Global Control Plane 迁移到
Kong Konnect Mesh Manager 的方案、执行步骤、检查点、风险和回滚条件。

当前环境概况：

- 一个 Kubernetes Zone
- 两个 Mesh
- Kong Mesh Zone Control Plane 版本：2.13.10
- Konnect region：`au`
- 目标 KDS 地址：`grpcs://au.mesh.sync.konghq.com:443`
- Zone CP 继续运行在现有 Kubernetes 集群
- Global Control Plane 改由 Konnect 托管

本文档默认采用以下 CA 策略：

> 不迁移旧 CA 和旧 signing keys，由 Konnect 为 Mesh 创建新的 builtin CA，
> 在维护窗口内重启 Zone CP、Zone Ingress/Egress 和所有 Mesh workloads，重新建立信任域。

如果业务不能接受维护窗口或全量重启，应改用“迁移旧 CA 后再轮换”的方案，不能直接执行本文默认切换步骤。

---

## 2. 目标架构

迁移前：

```text
Self-managed Global CP
          |
          | KDS
          v
Kubernetes Zone CP
          |
          +-- Mesh A workloads
          +-- Mesh B workloads
```

迁移后：

```text
Konnect-managed Global CP (AU)
          |
          | KDS over gRPC/TLS 443
          v
Existing Kubernetes Zone CP
          |
          +-- Mesh A workloads
          +-- Mesh B workloads
```

迁移后，Konnect 是以下资源的 source of truth：

- Mesh
- Mesh policies
- Mesh-scoped secrets
- ExternalService / MeshExternalService
- 全局策略和 Mesh 配置

Zone CP 负责：

- 管理本 Zone 的 dataplane proxies
- Zone Ingress/Egress
- 将运行状态和 Insight 上报到 Konnect
- 接收 Konnect 下发的 Mesh 配置和策略

---

## 3. 资源迁移范围

### 3.1 必须迁移或重建

| 资源 | 处理方式 | 说明 |
|---|---|---|
| `Mesh` | 在 Konnect 创建/更新 | 两个 Mesh 均需存在，名称及业务配置必须确认 |
| Mesh policies | 导出、评审、导入 | 包括 TrafficPermission、Route、Timeout、Retry 等 |
| `ExternalService` / `MeshExternalService` | 导入对应 Mesh | 属于业务期望配置 |
| mTLS 配置 | 在 Konnect 重建 | 默认方案使用新的 builtin CA |
| `AccessRole` | 按需重建 | 只迁移仍然需要的 Mesh 细粒度权限 |
| `AccessRoleBinding` | 映射后重建 | subject 改为 Konnect 用户邮箱或 Team/Group |
| Zone Connector system account | 在 Konnect 创建 | 仅授予目标 Mesh Control Plane 的 `Connector` role |
| Policy Manager system account | 在 Konnect 创建 | 用于自动化管理 Mesh，权限与 Connector 分离 |

### 3.2 不迁移，由系统重新生成

| 资源 | 原因 |
|---|---|
| `Zone` | Zone CP 成功连接 KDS 后自动注册 |
| `ZoneInsight` | 由连接状态自动生成 |
| `MeshInsight` | 由 Konnect 根据当前资源和状态生成 |
| `ServiceInsight` | 由 dataplane/service 状态自动生成 |
| `Dataplane` / `DataplaneInsight` | Kubernetes workloads 重启后重新注册 |
| `ZoneIngress` / `ZoneEgress` Insight | 对应组件重新连接后生成 |

### 3.3 默认不迁移的 secrets

默认方案不导入：

- 旧 builtin CA certificate/key
- 旧 dataplane token signing keys
- 旧 zone token signing keys
- 旧 user token signing keys
- `admin-user-token`
- Kubernetes ServiceAccount token
- Helm release secrets
- admission webhook TLS secrets

Konnect Zone Connector 使用单独的 System Account Access Token（`spat_`），该 token
存储在 Azure Key Vault，并通过 Secrets Store CSI Driver 只读挂载到 Zone CP Pod。token
不得进入 Terraform state、Git、workflow artifact 或日志。

---

## 4. CA 和 signing key 策略

### 4.1 默认方案：创建新 CA，不迁移旧 CA

结果：Konnect 中的 Mesh 建立新的信任根。现有 sidecar 持有的旧证书不受新 CA 信任。

必须执行：

- 重启 Zone CP
- 重启 Zone Ingress
- 重启 Zone Egress（如启用）
- 重启两个 Mesh 中的全部 sidecar workloads
- 对 Universal dataplane 重新签发 token（如存在）
- 重新创建独立 Pod、Job；确认 CronJob 的后续 Pod 使用新配置

迁移过程中可能同时存在新旧证书，因此需要维护窗口。仅重启 Zone CP 不足以完成切换。

### 4.2 备选方案：迁移旧 CA

如无法接受维护窗口：

1. 使用 `kumactl export --profile=federation-with-policies` 从旧 Global CP 导出。
2. 将旧 builtin CA 转换为 Konnect 中引用原 cert/key 的 provided CA。
3. 导入旧 signing keys、Mesh 和 policies。
4. 连接 Zone 并验证现有 dataplane。
5. 后续使用正式 CA rotation 流程逐步切换到新 CA。

该方案中导出文件包含私钥，必须使用加密存储和受控传输，禁止进入 Git、Terraform
state、CI artifact 或日志。

### Checkpoint CP-0：CA 策略批准

- [ ] 业务负责人接受默认方案的维护窗口和全量 workload restart
- [ ] 安全团队批准不保留旧信任根
- [ ] 已确认不存在依赖旧 dataplane token 的 Universal dataplane
- [ ] 如果任意一项不满足，切换到“迁移旧 CA”方案并重新评审

---

## 5. 前置条件

### 5.1 Konnect

- [ ] AU region 中已创建目标 Mesh Global Control Plane
- [ ] 已记录正确的 Mesh Control Plane ID
- [ ] 两个 Mesh 名称与旧环境的映射已确认
- [ ] Connector system account 已创建并仅授予 `Connector` role
- [ ] role scope 指向正确的 Mesh Control Plane，region 为 `au`
- [ ] Connector SAT 已创建，且未输出到日志或写入 Terraform state
- [ ] Policy Manager 身份及权限已经批准

### 5.2 Azure Key Vault / AKS

- [ ] Connector SAT 已写入 Azure Key Vault
- [ ] SecretProviderClass 已部署在 `kong-mesh-system`
- [ ] AKS Workload Identity/Managed Identity 有读取该 secret 的权限
- [ ] Zone CP Pod 能看到 token 文件，文件非空但不打印内容
- [ ] token 挂载为只读

### 5.3 网络

- [ ] Zone CP Pod 可以解析 `au.mesh.sync.konghq.com`
- [ ] DNS 内部域名和公网域名解析均正常
- [ ] egress firewall/proxy 允许访问 `au.mesh.sync.konghq.com:443`
- [ ] NetworkPolicy 允许访问集群 DNS UDP/TCP 53
- [ ] NetworkPolicy 允许 Zone CP 出站 TCP 443
- [ ] 企业 TLS inspection 不会破坏 KDS gRPC/TLS

### 5.4 版本与兼容性

- [ ] Kong Mesh 2.13.10 与目标 Konnect 功能兼容性已确认
- [ ] Helm values 和 post-renderer 已在非生产环境验证
- [ ] 所有 CRD 和 policy 类型已完成兼容性检查
- [ ] 已记录当前 Helm release、chart version 和完整 values

### Checkpoint CP-1：迁移准备就绪

Go 条件：以上前置条件全部通过，DNS 和 TCP 443 测试成功。

No-Go 条件：

- DNS 返回 `Name or service not known`
- KDS 地址不可访问
- Connector role/CP ID/region 尚未确认
- token 文件缺失或为空
- 维护窗口未批准

---

## 6. 发现与备份阶段

### 6.1 导出资源清单

至少记录以下资源：

```text
Mesh
Mesh policies
ExternalService / MeshExternalService
AccessRole
AccessRoleBinding
Zone
ZoneIngress / ZoneEgress
Kong Mesh secrets 的名称、类型和所属 Mesh
所有注入 sidecar 的 namespace/workload
```

使用仓库中的只读脚本收集清单：

```bash
KUBE_CONTEXT=<old-global-context> \
  ./scripts/list-kong-mesh-resources.sh
```

### 6.2 保存回滚所需配置

- [ ] 旧 Global CP Helm values 已备份
- [ ] Zone CP 当前 Helm values 已备份
- [ ] 旧 KDS 地址已记录
- [ ] 旧 Global CP 保持运行，迁移验证前不得删除
- [ ] 当前 policies 和 Mesh YAML 已导出
- [ ] 当前 workload 副本数和健康状态已记录
- [ ] 当前业务健康检查和关键调用基线已记录

### Checkpoint CP-2：资源清单完整

- [ ] 两个 Mesh 的资源数量已由平台团队和应用团队共同确认
- [ ] 每个 ExternalService 已映射到正确 Mesh
- [ ] 所有 policy 已确定迁移、替换或弃用结论
- [ ] RBAC subject 已完成 Konnect 用户/Team 映射
- [ ] 所有需要重启的 workload 都有 owner

---

## 7. Konnect 配置阶段

### 7.1 创建两个 Mesh

每个 Mesh 至少评审：

- `constraints.dataplaneProxy.requirements`
- `meshServices.mode`
- `mtls.backends`
- `mtls.enabledBackend`
- `networking`
- `routing`
- `skipCreatingInitialPolicies`

默认 CA 示例参数：

```text
type: builtin
RSA bits: 2048
CA expiration: 10y
dataplane certificate rotation: 1d
```

注意：相同的 CA 名称、RSA bits 和 expiration 不代表同一 CA；Konnect 会生成新的
certificate 和 private key。

### 7.2 导入业务配置

顺序建议：

1. Mesh
2. ExternalService / MeshExternalService
3. 基础安全 policies
4. routing、retry、timeout、observability 等 policies
5. AccessRole 和重新映射后的 AccessRoleBinding

如果设置了 `skipCreatingInitialPolicies=true` 并启用 mTLS，必须在连接生产 workload 前
准备允许业务正常通信的 MeshTrafficPermission，否则可能发生 default-deny。

### Checkpoint CP-3：Konnect 配置完成，尚未连接生产 Zone

- [ ] 两个 Mesh 在 Konnect 中存在且配置正确
- [ ] 两个 Mesh 都使用预期的新 builtin CA
- [ ] 必需的 MeshTrafficPermission 已存在
- [ ] ExternalServices 已核对
- [ ] policies 数量和关键字段与迁移清单一致
- [ ] RBAC 已用测试身份验证
- [ ] 尚未修改生产 Zone 的 KDS 地址

---

## 8. Zone CP 连接配置

Kong Mesh 2.13.10 的关键配置应为：

```text
KUMA_MODE=zone
KUMA_MULTIZONE_ZONE_NAME=<existing-zone-name>
KUMA_MULTIZONE_ZONE_GLOBAL_ADDRESS=grpcs://au.mesh.sync.konghq.com:443
KMESH_MULTIZONE_ZONE_KONNECT_CP_ID=<konnect-control-plane-id>
KMESH_MULTIZONE_ZONE_KDS_AUTH_CP_TOKEN_PATH=/mnt/secrets-store/cp-token
```

注意：Global 地址的正确变量为：

```text
KUMA_MULTIZONE_ZONE_GLOBAL_ADDRESS
```

迁移时保持原 zone name，不要创建第二个同名 Zone CP。

### Checkpoint CP-4：切换前最终 Go/No-Go

Go 条件：

- [ ] Konnect 配置 checkpoint 全部通过
- [ ] Connector token 挂载测试通过
- [ ] DNS 和 TCP 443 测试通过
- [ ] 维护窗口开始
- [ ] 应用团队在线
- [ ] 回滚操作人和决策人在线
- [ ] 旧 Global CP 仍然健康且可恢复连接

No-Go 条件：任何关键 owner 缺席，或网络、token、RBAC、policy 验证失败。

---

## 9. 切换执行 Runbook

### 9.1 冻结变更

- 暂停旧 Global CP 上的 Mesh/policy 变更
- 暂停相关应用发布
- 记录切换开始时间
- 保存最终资源清单和健康基线

### 9.2 更新 Zone CP

使用 Helm values/post-renderer 更新 Zone CP：

- 保留现有 zone name
- 设置 AU KDS 地址
- 设置 Konnect Mesh Control Plane ID
- 挂载 Key Vault 中的 Connector SAT
- 不把 token 放进 values、命令行参数或日志

等待 rollout：

```bash
kubectl --context "$ZONE_CONTEXT" -n kong-mesh-system \
  rollout status deployment/kong-mesh-control-plane --timeout=5m
```

### 9.3 验证 KDS 连接

检查日志：

```bash
kubectl --context "$ZONE_CONTEXT" -n kong-mesh-system \
  logs deployment/kong-mesh-control-plane -c control-plane --since=30m |
grep -Ei 'kds|konnect|connected|secret|certificate|unauth|permission|error'
```

错误处理：

| 错误 | 优先检查 |
|---|---|
| `Unauthenticated` | token 是否有效、过期或挂载错误 |
| `PermissionDenied` | Connector role、CP ID、scope、region |
| `no such host` | Pod DNS、CoreDNS、上游 DNS |
| `deadline exceeded` | firewall、proxy、NetworkPolicy |
| `x509` | TLS inspection、CA/proxy |
| Secret conflict | Zone 内残留的同名旧 Kuma Secret |

### Checkpoint CP-5：Zone 已连接 Konnect

- [ ] Konnect 显示原 zone name
- [ ] Zone 状态为 `Online`
- [ ] Zone CP 日志没有持续 KDS/auth/TLS 错误
- [ ] 两个 Mesh 配置已同步到 Zone
- [ ] 尚未开始 workload 全量重启前，团队接受业务可能处于过渡状态

如果该 checkpoint 失败，立即进入回滚，不继续重启业务。

### 9.4 处理旧 CA Secret 冲突

由于默认方案不迁移旧 CA，Zone 中可能残留旧 Global CP 同步的同名 secrets。
Kong Mesh 2.10+ 不会覆盖 Zone 中已有的冲突 secret。

先列出并逐个确认：

```bash
kubectl --context "$ZONE_CONTEXT" -n kong-mesh-system \
  get secrets --field-selector='type=system.kuma.io/secret' \
  -o custom-columns='NAME:.metadata.name,MESH:.metadata.labels.kuma\.io/mesh,TYPE:.type'
```

仅删除已经确认属于旧信任域、且阻止新 CA 同步的 CA/signing secrets。禁止使用通配符
批量删除，禁止删除 Konnect Connector token。删除前记录资源名称并获得当班变更负责人确认。

删除后重启 Zone CP，再次检查 Secret 同步和日志。

### 9.5 重启数据平面

按 Mesh 和应用依赖顺序分批执行：

1. Zone Ingress/Egress
2. 非关键 workload/canary
3. 验证 canary mTLS 和业务调用
4. Mesh A 其余 workloads
5. Mesh B 其余 workloads
6. StatefulSet、DaemonSet、独立 Pod、Job/CronJob

Deployment 示例：

```bash
kubectl --context "$ZONE_CONTEXT" -n <namespace> rollout restart deployment
kubectl --context "$ZONE_CONTEXT" -n <namespace> rollout status deployment/<name> --timeout=5m
```

禁止无清单地对整个集群执行全量 restart。每批重启后都必须完成健康检查再继续。

### Checkpoint CP-6：每批 workload 验证

- [ ] 新 Pod 已注入 sidecar
- [ ] Pod Ready，副本数符合预期
- [ ] dataplane 在 Konnect 中显示 Online
- [ ] 没有 certificate/unknown authority 错误
- [ ] 没有 dataplane `Unauthenticated`
- [ ] 同 Mesh 服务间调用正常
- [ ] 跨 Mesh 调用符合 policy 预期
- [ ] ExternalService 调用正常
- [ ] 应用错误率、延迟和流量指标无异常

任一批次失败时停止后续批次，并根据回滚标准决定修复或回滚。

---

## 10. 最终验证

### 10.1 控制面

- [ ] Konnect Zone 持续 `Online` 至少 30 分钟
- [ ] 两个 Mesh 均可见
- [ ] MeshInsight、ZoneInsight、ServiceInsight 正常生成
- [ ] Zone CP 没有持续 KDS 重连
- [ ] 没有 Secret conflict
- [ ] policies 和 ExternalServices 与批准清单一致

### 10.2 数据面

- [ ] 所有预期 dataplane 在线
- [ ] Zone Ingress/Egress 在线
- [ ] 所有 sidecar 均在切换后重新创建
- [ ] mTLS 测试成功
- [ ] 核心业务 smoke test 成功
- [ ] 跨服务、跨 Mesh、外部服务调用验证成功
- [ ] 错误率、P95/P99 延迟和资源使用正常

### 10.3 安全与运维

- [ ] Connector system account 只有 `Connector` role
- [ ] Policy Manager 与 Connector 身份分离
- [ ] token 仅存在于 Key Vault 和运行时挂载
- [ ] Git、Terraform state、Actions logs/artifacts 中无 token/private key
- [ ] token rotation 和失效流程已记录
- [ ] 告警和 dashboard 已更新到 Konnect 架构

### Checkpoint CP-7：迁移完成

只有 CP-7 全部通过，才能宣布迁移成功。旧 Global CP 进入观察期，不立即删除。

---

## 11. 回滚计划

### 11.1 回滚触发条件

满足任意条件应评估立即回滚：

- Zone 无法在约定时间内连接 Konnect
- DNS、firewall 或 proxy 问题无法在窗口内解决
- 大量 dataplane 无法重新注册
- mTLS 大面积失败
- 关键 policy 缺失或行为与旧环境不一致
- 核心业务 smoke test 失败
- 错误率或延迟超过约定阈值

具体阈值和最大排障时间必须在变更前由团队填写：

```text
最大 Zone 连接等待时间：________
最大单批 workload 恢复时间：____
允许错误率：____________________
允许 P95/P99 延迟：_____________
最终回滚决策时间：______________
```

### 11.2 回滚动作

1. 停止后续 workload restart。
2. 将 Zone CP Helm values 恢复为旧 Global CP KDS 地址和认证配置。
3. Helm rollback/upgrade Zone CP。
4. 确认 Zone 重新连接旧 Global CP。
5. 如果部分 workloads 已使用新 CA 重启，需要再次重启这些 workloads，使其从旧 Global CP 获取旧信任链。
6. 验证旧环境中的 dataplane、policies 和业务调用。
7. 保留日志和时间线用于问题分析。

由于默认方案会更换 CA，一旦 workloads 已经开始使用新 CA，回滚同样需要重启这些
workloads。旧 Global CP 和旧 CA secrets 在整个观察期内不得删除。

### Checkpoint CP-R：回滚完成

- [ ] Zone 已重新连接旧 Global CP
- [ ] 已切换过的 workloads 全部恢复旧信任链
- [ ] 核心业务检查通过
- [ ] 变更冻结已恢复或延长
- [ ] 已记录失败原因和后续行动

---

## 12. 观察期和旧环境退役

建议至少保留一个经过团队批准的观察期，例如 3 至 7 天。观察期内：

- 旧 Global CP 保持可恢复，但禁止配置变更
- 持续监控 Zone KDS 连接
- 持续监控 dataplane 在线率
- 检查证书轮换是否正常
- 检查 policy、ExternalService 和业务指标
- 验证 Connector token rotation 流程

退役旧 Global CP 前需要最终批准：

### Checkpoint CP-8：旧 Global CP 退役批准

- [ ] 观察期完成且无未解决问题
- [ ] Konnect 配置已纳入 IaC/版本控制
- [ ] 回滚窗口已正式关闭
- [ ] 安全、平台和应用负责人批准退役
- [ ] 旧 secrets 的销毁方式已批准
- [ ] 运维文档、on-call runbook 和告警已更新

---

## 13. 团队评审决策记录

| 决策项 | 结论 | Owner | 日期 |
|---|---|---|---|
| CA 策略：新 CA 或迁移旧 CA | 待确认 |  |  |
| 维护窗口 | 待确认 |  |  |
| 两个 Mesh 的名称和映射 | 待确认 |  |  |
| policy 迁移清单 | 待确认 |  |  |
| RBAC 映射 | 待确认 |  |  |
| workload 分批顺序 | 待确认 |  |  |
| Go/No-Go 决策人 | 待确认 |  |  |
| 回滚阈值 | 待确认 |  |  |
| 观察期长度 | 待确认 |  |  |

---

## 14. 官方参考

- [Kong Mesh in Konnect](https://developer.konghq.com/mesh/konnect/)
- [Migrate a self-managed Zone Control Plane to Konnect](https://developer.konghq.com/mesh/migrate-self-managed-zone-to-konnect/)
- [Managing secrets in Kong Mesh](https://developer.konghq.com/mesh/manage-secrets/)
- [Mutual TLS](https://developer.konghq.com/mesh/policies/mutual-tls/)
- [Dataplane proxy authentication](https://developer.konghq.com/mesh/data-plane-proxy-authentication/)
