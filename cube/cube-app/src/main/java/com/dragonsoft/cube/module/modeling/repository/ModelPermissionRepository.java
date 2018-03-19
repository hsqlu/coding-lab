package com.dragonsoft.cube.module.modeling.repository;

import com.dragonsoft.cube.module.modeling.entity.ModelPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created: 2016/9/7.
 * Author: Qiannan Lu
 */
@Repository
public interface ModelPermissionRepository extends JpaRepository<ModelPermission, Long> {
}