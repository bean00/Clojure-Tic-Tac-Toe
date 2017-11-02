(ns clojure-tic-tac-toe.tic_tac_toe_rules_test
  (:require [clojure.set :as set]
            [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.tic_tac_toe_rules :refer :all]
            [clojure-tic-tac-toe.utilities :refer [contains-set?]]))

(defn- assert-that-it-contains-the-set
  [sets set-to-check entity]
  (is (= true
         (contains-set? sets set-to-check))
      (format "it includes the %s" entity)))

(deftest get-winning-sets-test
  (testing "when getting all winning sets"
    (let [winning-sets (get-winning-sets)]
      (assert-that-it-contains-the-set
        winning-sets #{:1 :2 :3} "top row")
      (assert-that-it-contains-the-set
        winning-sets #{:4 :5 :6} "middle row")
      (assert-that-it-contains-the-set
        winning-sets #{:7 :8 :9} "bottom row")

      (assert-that-it-contains-the-set
        winning-sets #{:1 :4 :7} "left column")
      (assert-that-it-contains-the-set
        winning-sets #{:2 :5 :8} "middle column")
      (assert-that-it-contains-the-set
        winning-sets #{:3 :6 :9} "right column")

      (assert-that-it-contains-the-set
        winning-sets #{:1 :5 :9} "upper left diagonal")
      (assert-that-it-contains-the-set
        winning-sets #{:3 :5 :7} "upper right diagonal"))))

