(defproject magpie-admin "0.1.0-SNAPSHOT"
  :description "magpie admin interface"
  :url "https://github.com/pho-coder/magpie-admin"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]
                 [log4j "1.2.17"]
                 [org.clojure/tools.logging "0.2.6"]
                 [org.apache.zookeeper/zookeeper "3.4.5"]
                 [org.apache.curator/curator-framework "2.4.0"]]
  :plugins [[lein-ring "0.8.10"]]
  :source-paths ["src/clj"]
  :ring {:handler magpie-admin.handler/app}
  :global-vars {*warn-on-reflection* true}
  :aliases {"clean-run!" ["do" "clean," "ring" "server-headless"]}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
