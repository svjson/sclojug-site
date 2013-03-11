(ns sclojug-site.common
  (:use [hiccup.page :only [html5 include-css]]))
       
(defn layout [& body]
  (html5 
    [:head
     [:title "Welcome to sclojug-site"]
     (include-css "/css/screen.css")]
    (into [:body] body)))