(ns magpie-admin.state-info
  (:require [magpie-admin.util.zk-util :refer [get-zk-client get-children get-data]]))

(def ^:dynamtic *zk-client* (atom nil))

(def supervisors-path "/magpie/supervisors")

(def ^:dynamtic *supervisors-info* (atom nil))

(def ^:dynamtic *tracking*)

;; (def ZK-CONNECTION-STRING "10.12.218.191:2181,10.12.218.193:2181")
(def ZK-CONNECTION-STRING "172.17.44.176:2181,172.17.44.178:2181,172.17.44.180:2181,172.17.44.184:2181,172.17.44.192:2181")

(defn check-zk-client []
  (if (nil? @*zk-client*)
    (reset! *zk-client* (get-zk-client ZK-CONNECTION-STRING))))

(defn get-supervisors []
  (check-zk-client)
  (let [supervisors (get-children @*zk-client* supervisors-path)
        supervisors-data (map #(get-data @*zk-client* (str supervisors-path "/" %)) supervisors)]
    (prn supervisors-data)
    supervisors))

(defn start-tracking []
  true)
