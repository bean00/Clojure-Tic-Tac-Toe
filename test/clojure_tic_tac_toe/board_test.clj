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

(deftest get-available-moves-test
  (testing "when no move has been made"
    (is (= valid-moves
           (get-available-moves empty-board))
        "it will return all the moves"))
  (testing "when a move has been made"
    (is (= #{:2 :3 :4 :5 :6 :7 :8 :9}
           (get-available-moves { :X #{:1} :O #{} }))
        "it should not be in the set of available moves"))
  (testing "when each player has made 1 move"
    (is (= #{:3 :4 :5 :6 :7 :8 :9}
           (get-available-moves { :X #{:1} :O #{:2} }))
        "it should return all moves except for those 2"))
  (testing "when the board is full"
    (is (= #{}
           (get-available-moves { :X #{:1 :2 :3 :4 :5} :O #{:6 :7 :8 :9} })))))

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

