(ns magpie-admin.remotes
  (:require [compojure.handler :refer [site]]
            [shoreleave.middleware.rpc :refer [defremote wrap-rpc]]

            [magpie-admin.handler :refer [handler]]
            [magpie-admin.state-info :refer [start-tracking get-supervisors]]))

(defremote rpc-get-supervisors []
  (get-supervisors))

(defremote rpc-start-tracking []
  (start-tracking))

(def app (-> (var handler)
             (wrap-rpc)
             (site)))
