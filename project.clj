(defproject magpie-admin "0.1.0-SNAPSHOT"
  :description "magpie admin interface"
  :url "https://github.com/pho-coder/magpie-admin"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.6"]
                 [log4j "1.2.17"]
                 [org.clojure/tools.logging "0.2.6"]
                 [org.apache.zookeeper/zookeeper "3.4.5"]
                 [org.apache.curator/curator-framework "2.4.0"]
                 [enlive "1.1.5"]
                 [domina "1.0.3-SNAPSHOT"]
                 [hiccups "0.2.0"]
                 [org.clojars.magomimmo/shoreleave-remote-ring "0.3.1-SNAPSHOT"]
                 [org.clojars.magomimmo/shoreleave-remote "0.3.1-SNAPSHOT"]]
  :plugins [[lein-cljsbuild "1.0.2"]
            [lein-ring "0.8.10"]]
  :source-paths ["src/clj" "src/cljs" "src/brepl"]
  :ring {:handler magpie-admin.remotes/app}
  :global-vars {*warn-on-reflection* true}
  :aliases {"clean-run!" ["do" "clean," "ring" "server-headless"]
            "clean-cljsbuild!" ["do" "clean," "cljsbuild" "clean," "cljsbuild" "once" "dev," "ring" "server-headless"]}
  :cljsbuild {:builds
              {:dev {:source-paths ["src/cljs" "src/brepl"]
                     :compiler {:output-to "resources/public/js/magpie_admin_dbg.js"
                                :optimizations :whitespace
                                :pretty-print true}}
               :prod {:source-paths ["src/cljs"]
                      :compiler {:output-to "resources/public/js/magpie_admin.js"
                                 :optimizations :advanced
                                 :pretty-print false}}}}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring-mock "0.1.5"]]}})
