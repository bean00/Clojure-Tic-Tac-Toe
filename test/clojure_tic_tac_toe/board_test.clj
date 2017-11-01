(ns clojure-tic-tac-toe.board_test
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

(deftest is-move-invalid?-test
  (testing "when a valid move is passed in"
    (is (= true
           (is-move-invalid? :e))
        "it returns true"))
  (testing "when an invalid move is passed in"
    (is (= false
           (is-move-invalid? :3))
        "it returns false")))

(deftest has-move-been-taken?-test
  (testing "when a move has already been taken"
    (is (= true
           (has-move-been-taken? { :X #{:4} :O #{} } :4))
        "it returns true"))
  (testing "when a move has not been taken"
    (is (= false
           (has-move-been-taken? { :X #{:3} :O #{:5} } :9))
        "it returns false")))

