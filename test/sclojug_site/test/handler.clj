(ns sclojug-site.test.handler
  (:use midje.sweet
        ring.mock.request  
        sclojug-site.handler))

(fact "ett test"
      1 => 1)

(fact "ett annat"
      (second [1 2]) => 2
      (first [1 2]) => 1)

(defn eval-body-of [response]
  (read-string (:body response)))

(def food-request
  {:request-method :get
   :uri "/food/available"})

(fact "food route is a vector"
      (eval-body-of
       (app food-request)) => vector?
      (eval-body-of
       (app food-request)) => (has every? :name))