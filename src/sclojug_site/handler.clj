(ns sclojug-site.handler
  (:use compojure.core                
        ring.middleware.resource
        ring.middleware.file-info
        ring.middleware.edn
        hiccup.middleware)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [sclojug-site.common :as common]))

(defn home []
  (common/layout [:h1 "Hello World!"]))

(defn echo [name params]
  {:status 200
   :headers {"Content-Type" "application/edn"}
   :body (pr-str {:name name
                  :params params})})

(defroutes app-routes
  (GET "/" [] (home))
  (GET "/echo/:name"
       [name :as {params :params}]
       (echo name params))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app (handler/site app-routes))

(def war-handler 
  (-> app    
      (wrap-resource "public")
      (wrap-edn-params)
      (wrap-base-url)
      (wrap-file-info)))


