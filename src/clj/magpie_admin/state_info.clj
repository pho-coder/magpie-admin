(ns magpie-admin.state-info
  (:require [magpie-admin.util.zk-util :refer [get-zk-client get-children get-data]]
            [clojure.data.json :refer [read-str]]
            [clojure.tools.logging :refer [info debug]]))

(def ^:dynamtic *zk-client* (atom nil))

(def supervisors-path "/magpie/supervisors")

(def assignments-path "/magpie/assignments-path")

(def ^:dynamtic *supervisors-info* (atom nil))

(def ^:dynamtic *tasks-info* (atom nil))

(def ^:dynamtic *tracking*)

(def ZK-CONNECTION-STRING "172.17.44.176:2181,172.17.44.178:2181,172.17.44.180:2181,172.17.44.184:2181,172.17.44.192:2181")

(defn check-zk-client []
  (if (nil? @*zk-client*)
    (reset! *zk-client* (get-zk-client ZK-CONNECTION-STRING))))

(defn get-supervisors []
  (check-zk-client)
  (let [supervisors (get-children @*zk-client* supervisors-path)
        get-supervisor-data (fn [supervisor]
                          (read-str (String. (get-data @*zk-client* (str supervisors-path "/" supervisor))) :key-fn keyword))]
    (reset! *supervisors-info* (reduce (fn [m k]
                                         (assoc m k (get-supervisor-data k)))
                                       {}
                                       supervisors))
    (info @*supervisors-info*)))

(defn get-tasks []
  (check-zk-client)
  (let [tasks (get-children @*zk-client* assignments-path)
        get-task-data (fn [task]
                        (read-str (String. (get-data @*zk-client* (str assignments-path "/" task))) :key-fn keyword))]
    (reset! *tasks-info* (reduce (fn [m k]
                                   (assoc m k (get-task-data k)))
                                 {}
                                 tasks))
    (info @*tasks-info*)))

(defn start-tracking []
  (check-zk-client)
  (get-supervisors)
  (get-tasks)
  true)
