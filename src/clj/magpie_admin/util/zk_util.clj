(ns magpie-admin.util.zk-util
  (:import [org.apache.curator.retry ExponentialBackoffRetry]
           [org.apache.curator.framework CuratorFrameworkFactory CuratorFramework]
           [org.apache.curator.framework.api GetChildrenBuilder GetDataBuilder]))

(defn get-zk-client
  [connectionString]
  (let [retry-policy (ExponentialBackoffRetry. 1000 3)
        client (CuratorFrameworkFactory/newClient connectionString retry-policy)]
    (.start client)
    client))

(defn get-children [client path]
  (.forPath ^GetChildrenBuilder (.getChildren ^CuratorFramework client) path))

(defn get-data [client path]
  (.forPath ^GetDataBuilder (.getData ^CuratorFramework client) path))
