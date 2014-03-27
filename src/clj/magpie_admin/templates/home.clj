(ns magpie-admin.templates.home
  (:require [net.cgrand.enlive-html :refer [deftemplate defsnippet after content]]))

(defsnippet header-content "public/content.html" [:#header]
  [])

(defsnippet footer-content "public/content.html" [:#footer]
  [])

(deftemplate home "public/index.html"
  [])

(deftemplate supervisors-page "public/index.html"
  [supervisors]
  [:header] (reduce after (map #(content "<p>aaa</p>") supervisors)))

(deftemplate not-found-page "public/404.html" [])
