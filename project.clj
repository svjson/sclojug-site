(defproject sclojug-site "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [compojure "1.1.3"]
                 [hiccup "1.0.2"]
                 [ring/ring-jetty-adapter "1.1.0"]
                 [bultitude "0.1.7"]
                 [clj-json "0.5.3"]
                 [stuarth/clj-oauth2 "0.3.2"]]
  :plugins [[lein-cljsbuild "0.3.0"]
            [lein-ring "0.8.3"]]
  :ring {:handler sclojug-site.handler/war-handler}
  :main sclojug-site.server
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.3"]
                        [webfui "0.2.1"]
                        [ring/ring-devel "1.1.0"]]}}
  :cljsbuild {
    :builds [{
        ; The path to the top-level ClojureScript source directory:
        :source-paths ["src-cljs"]
        ; The standard ClojureScript compiler options:
        ; (See the ClojureScript compiler documentation for details.)
        :compiler {
          :output-to "resources/public/js/main.js"  ; default: target/cljsbuild-main.js
          :optimizations :whitespace
          :pretty-print true}}]})
