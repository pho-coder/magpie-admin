(ns magpie-admin.remotes
  (:require [compojure.handler :refer [site]]
            [shoreleave.middleware.rpc :refer [defremote wrap-rpc]]

            [magpie-admin.handler :refer [handler]]
            [magpie-admin.state-info :refer [start-tracking *supervisors-info* *tasks-info*]]))

(defremote rpc-get-info []
  {:supervisors @*supervisors-info* :tasks @*tasks-info*})

(defremote rpc-start-tracking []
  (start-tracking))

(def app (-> (var handler)
             (wrap-rpc)
             (site)))
