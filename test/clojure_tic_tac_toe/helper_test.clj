(ns clojure-tic-tac-toe.helper-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.helper :refer :all]))

(deftest get-map-with-value-in-set-test
  (testing "when a set contains the desired value"
    (is (= { :X #{:1} }
           (get-map-with-value-in-set { :X #{:1} :O #{} } :1 ))
        "it returns the map that contains that set"))
  (testing "when none of the sets contain the desired value"
    (is (= { }
           (get-map-with-value-in-set { :X #{:1} :O #{:2} } :4 ))
        "it returns an empty map")))

