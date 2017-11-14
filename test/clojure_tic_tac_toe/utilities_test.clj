(ns clojure-tic-tac-toe.utilities_test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.utilities :refer :all]))

(deftest get-map-with-value-in-set-test
  (testing "when a set contains the desired value"
    (is (= { :X #{:1} }
           (get-map-with-value-in-set { :X #{:1} :O #{} } :1 ))
        "it returns the map that contains that set"))
  (testing "when none of the sets contain the desired value"
    (is (= { }
           (get-map-with-value-in-set { :X #{:1} :O #{:2} } :4 ))
        "it returns an empty map")))

(deftest join-lines-test
  (testing "when multiple lines are passed in"
    (is (= "First line\nSecond line\nThird line"
           (join-lines ["First line"
                        "Second line"
                        "Third line"]))
        "it joins the lines together with newlines")))

(deftest contains-set?-test
  (testing "when a collection contains the set"
    (is (= true
           (contains-set? (list #{:1 :2} #{:3 :4}) #{:1 :2}))
        "it returns true"))
  (testing "when a collection doesn't contain the set"
    (is (= false
           (contains-set? (list #{:1 :2}) #{:3 :4}))
        "it returns false")))

(deftest set-to-list-or-nil-test
  (testing "when the set contains 4 keywords"
    (is (= '(:1 :2 :3 :4)
           (sort
             (set-to-list-or-nil #{:4 :3 :2 :1})))
        "it returns the keywords in a list (unsorted)"))
  (testing "when the set is empty"
    (is (= nil
           (set-to-list-or-nil #{}))
        "it returns nil")))

