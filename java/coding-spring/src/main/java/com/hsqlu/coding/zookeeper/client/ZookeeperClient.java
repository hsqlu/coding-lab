package com.hsqlu.coding.zookeeper.client;

import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * Created: 2016/4/25.
 * Author: Qiannan Lu
 */
public interface ZookeeperClient {
    void addConnStateListener(ConnectionStateListener listener);

    void addNodeChangedListener(String path, final NodeChangedListener listener);

    void addChildNodeListener(String path, final PathChildrenCacheListener cnl);

    void cacheNode(String path);

    void cacheChildNode(String path);

    void updateNode(String path, int withVersion);

    void updateNode(String path, byte[] data);

    byte[] getNode(String path);

    void delNodeCache(String path);

    void delChildNodeCache(String path);

    void deleteNode(String path, boolean delChildIfNeeded);

    void createNode(String path, CreateMode mode, boolean parentsIfNeeded,
                    byte[] data);

    void createNode(String path, CreateMode mode, boolean parentsIfNeeded);

    boolean existNode(String path);

    void start(String url, String namespace, boolean readOnly,ConnectionStateListener stateListener);

    void start(String url, String namespace, boolean readOnly);

    void start(String url, String namespace);

    void start(String url, boolean readOnly);

    void close();

    PathChildrenCache getChildNodeCache(String path);

    interface ChildNodeListener{
        void addChild();
    }

    interface NodeChangedListener{
        void nodeChanged(Stat stat, byte[] bytes);
    }

    enum ZookeeperWatcherType {
        GET_DATA, GET_CHILDREN, EXITS, CREATE_ON_NO_EXITS
    }
}
