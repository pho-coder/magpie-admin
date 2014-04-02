(ns magpie-admin.home
  (:require-macros [hiccups.core :refer [html]])
  (:require [hiccups.runtime :as hiccupsrt]
            [domina :refer [by-id set-text! append! destroy! by-class]]
            [domina.events :refer [listen!]]
            [shoreleave.remotes.http-rpc :refer [remote-callback]]))

(defn start_tracking []
  (remote-callback :rpc-start-tracking []
                   (fn [is-ok?]
                     (if is-ok?
                       (set-text! (by-id "track_info") "tracking!")
                       (set-text! (by-id "track_info") "no tracking!")))))

(defn get_supervisors []
  (remote-callback :rpc-get-supervisors
                   []
                   (fn [supervisors]
                     (destroy! (by-class "supervisors"))
                     (if (= (count supervisors) 0)
                       (set-text! (by-id "supervisors") "There are no supervisors alive now!")
                       (doseq [supervisor (keys supervisors)]
                         (append! (by-id "supervisors_list") (html [:li.supervisors-class#supervisor])))))))

(defn ^:export init []
  (if (and js/document (aget js/document "getElementById"))
    (do (listen! (by-id "start_tracking") :click start_tracking)
        (listen! (by-id "get_supervisors") :click get_supervisors))))
