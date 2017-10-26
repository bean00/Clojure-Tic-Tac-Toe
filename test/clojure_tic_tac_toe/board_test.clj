(ns clojure-tic-tac-toe.board-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.board :refer :all]))

(deftest add-move-test
  (testing "when adding the first move"
    (is (= { :X #{:3} :O #{} }
           (add-move empty-board
                     :3
                     :X))
        "it adds X's move to the board"))
  (testing "when adding the second move"
    (is (= { :X #{:3} :O #{:5} }
           (add-move { :X #{:3} :O #{} }
                     :5
                     :O))
        "it adds O's move to the board"))
  (testing "when adding the third move"
    (is (= { :X #{:3 :6} :O #{:5} }
           (add-move { :X #{:3} :O #{:5} }
                     :6
                     :X))
        "it adds X's next move to the board")))

(deftest token-at-test
  (testing "when neither player has moved to the position"
    (is (= :1
           (token-at empty-board :1 ))
        "it returns the token key for that position"))
  (testing "when X has moved to position 1"
    (is (= :X
           (token-at { :X #{:1} :O #{} } :1 ))
        "it returns the token key for X")))

