package com.hsqlu.coding.zookeeper.client;

import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.zookeeper.CreateMode;

/**
 * Created: 2016/4/25.
 * Author: Qiannan Lu
 */
public class CuratorZookeeperClient implements ZookeeperClient {
    @Override
    public void addConnStateListener(ConnectionStateListener listener) {

    }

    @Override
    public void addNodeChangedListener(String path, NodeChangedListener listener) {

    }

    @Override
    public void addChildNodeListener(String path, PathChildrenCacheListener cnl) {

    }

    @Override
    public void cacheNode(String path) {

    }

    @Override
    public void cacheChildNode(String path) {

    }

    @Override
    public void updateNode(String path, int withVersion) {

    }

    @Override
    public void updateNode(String path, byte[] data) {

    }

    @Override
    public byte[] getNode(String path) {
        return new byte[0];
    }

    @Override
    public void delNodeCache(String path) {

    }

    @Override
    public void delChildNodeCache(String path) {

    }

    @Override
    public void deleteNode(String path, boolean delChildIfNeeded) {

    }

    @Override
    public void createNode(String path, CreateMode mode, boolean parentsIfNeeded, byte[] data) {

    }

    @Override
    public void createNode(String path, CreateMode mode, boolean parentsIfNeeded) {

    }

    @Override
    public boolean existNode(String path) {
        return false;
    }

    @Override
    public void start(String url, String namespace, boolean readOnly, ConnectionStateListener stateListener) {

    }

    @Override
    public void start(String url, String namespace, boolean readOnly) {

    }

    @Override
    public void start(String url, String namespace) {

    }

    @Override
    public void start(String url, boolean readOnly) {

    }

    @Override
    public void close() {

    }

    @Override
    public PathChildrenCache getChildNodeCache(String path) {
        return null;
    }
}
