(ns magpie-admin.handler
  (:use compojure.core)
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.handler :refer [site]]
            [compojure.route :refer [resources not-found]]

            [magpie-admin.templates.home :refer [home supervisors-page not-found-page]]
            [magpie-admin.state-info :refer [get-supervisors]]))

(defroutes app-routes
  (GET "/" [] (home))
  (GET "/supervisors" [] (let [
                               ;;supervisors (get-supervisors)
                               supervisors [1 2 3]]
                           (prn supervisors)
                           (supervisors-page supervisors)))
  (resources "/")
  (not-found (not-found-page)))

(def handler
  (site app-routes))
