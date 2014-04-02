(ns magpie-admin.remotes
  (:require [compojure.handler :refer [site]]
            [shoreleave.middleware.rpc :refer [defremote wrap-rpc]]
            [clojure.tools.logging :refer [info]]
            
            [magpie-admin.handler :refer [handler]]
            [magpie-admin.state-info :refer [start-tracking *supervisors-info* *tasks-info* *offset-info*]]))

(defremote rpc-get-info []
  (info "rpc get info")
  {:supervisors @*supervisors-info*
   :tasks @*tasks-info*
   :offset @*offset-info*})

(defremote rpc-start-tracking []
  (start-tracking))

(def app (-> (var handler)
             (wrap-rpc)
             (site)))
