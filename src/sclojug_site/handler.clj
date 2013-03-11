(ns sclojug-site.handler
  (:use compojure.core                
        ring.middleware.resource
        ring.middleware.file-info
        ring.middleware.edn
        hiccup.middleware)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [sclojug-site.common :as common]
            [sclojug-site.util.oauth :as oauth]
            [clj-json.core :as json]))


(defn available-food []
  {:status 200
   :body (pr-str [{:name "Vegetarian"}
            {:name "Vegan"}
            {:name "LCHF"}
            {:name "Kött"}
            {:name "Fisk"}])})
(defn home [] 
  (common/layout [:h1 "Login"]
                 [:div
                  [:a {:href (:uri (oauth/get-login-url))} "Login with your Google-account"]
                  [:a {:href "/food/available"} "Välj mat"]]))

(defn echo [name params]
  {:status 200
   :headers {"Content-Type" "application/edn"}
   :body (pr-str {:name name
                  :params params})})

(defroutes app-routes
  (GET "/" [] (home))
  (GET "/oauth2callback" {params :params session :session} (let [user-info (oauth/get-user-info (oauth/get-access-token params))]
                                                             (common/layout 
                                                              [:div (str "Hej, " (:email (json/parse-string (:body user-info) true)))])))

  (GET "/echo/:name"
       [name :as {params :params}]
       (echo name params))
  (GET "/food/available"
       []
       (available-food))

  (route/resources "/")

  (route/not-found "Not Found"))

(def app (handler/site app-routes))

(def war-handler 
  (-> app    
      (wrap-resource "public")
      (wrap-edn-params)
      (wrap-base-url)
      (wrap-file-info)))


