(ns sclojug-site.common
  (:use [hiccup.page :only [html5 include-css include-js]]))

(defn layout [& body]
  (html5
    [:head
     [:title "Welcome to sclojug-site"]
     (include-css "/css/screen.css")
     (include-js "/js/main.js")]
    (into [:body] body)))
