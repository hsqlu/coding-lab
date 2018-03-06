package hsqlu.coding.demo;

import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

import java.util.List;

/**
 * Created: 02/02/2018.
 * Author: Qiannan Lu
 */
public class ConfigCenter {


    public static void main(String[] args) throws Exception {

        CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
                .connectString("192.168.56.2:2181")
                .retryPolicy(new RetryNTimes(1, 1000))
                .connectionTimeoutMs(5000);
//        String authority = url.getAuthority();
//        if (authority != null && authority.length() > 0) {
//            builder = builder.authorization("digest", authority.getBytes());
//        }
        CuratorFramework client = builder.build();
        client.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState state) {

            }
        });
        client.start();

        client.delete().deletingChildrenIfNeeded().forPath("/ETL");
        List<String> children = client.getChildren().forPath("/");
        children.forEach(System.out::println);
    }
}
