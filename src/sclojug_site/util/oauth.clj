(ns sclojug-site.util.oauth
  (:require [clj-oauth2.client :as oauth2]))

(def login-uri "https://accounts.google.com")

(def google-oauth2
  {:authorization-uri (str login-uri "/o/oauth2/auth")
   :access-token-uri (str login-uri "/o/oauth2/token")
   :redirect-uri "http://localhost:8080/oauth2callback"
   :client-id "832476044277.apps.googleusercontent.com"
   :client-secret "LSSTFQv8-t2mzLVXbxdagFvN"
   :access-query-param :access_token
   :scope ["https://www.googleapis.com/auth/userinfo.email"]
   :grant-type "authorization_code"
   :access-type "online"
   :approval_prompt "Banana?"})

(defn get-login-url []
  (oauth2/make-auth-request google-oauth2))

(defn get-access-token [auth-resp]
   (oauth2/get-access-token google-oauth2 auth-resp get-login-url))

(defn get-user-info [access-token]
  (println (str "access-token: " access-token))
  (oauth2/get "https://www.googleapis.com/oauth2/v1/userinfo" {:oauth2 access-token}))
