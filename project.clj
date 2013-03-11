(defproject sclojug-site "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.5.0"]
                 [compojure "1.1.3"]
                 [hiccup "1.0.2"]
                 [ring/ring-jetty-adapter "1.1.0"]
                 [fogus/ring-edn "0.2.0-SNAPSHOT"]
                 [bultitude "0.1.7"]]
  :plugins [[lein-ring "0.8.3"]]
  :ring {:handler sclojug-site.handler/war-handler}
  :main sclojug-site.server
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.3"]
                        [ring/ring-devel "1.1.0"]]}})
