(ns clojure-tic-tac-toe.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.core :refer :all]))

(deftest get-move-test
  (testing "when getting a move"
    (with-out-str
      (is (= 4
             (with-in-str "4"
               (get-move :O)))
          "it returns the move"))))

(deftest int-to-keyword-test
  (testing "with an integer"
    (is (= :3
           (int-to-keyword 3))
        "it returns the corresponding keyword"))
  (testing "with a different integer"
    (is (= :5
           (int-to-keyword 5))
        "it returns the corresponding keyword")))
