(ns clojure-tic-tac-toe.board_test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.board :refer :all]))

(deftest token-at-test
  (testing "when neither player has moved to the position"
    (is (= :1
           (token-at empty-board :1))
        "it returns the token key for that position"))
  (testing "when X has moved to position 1"
    (is (= :X
           (token-at {:X #{:1}, :O #{}} :1))
        "it returns the token key for X")))

(deftest add-move-test
  (testing "when adding the first move"
    (is (= {:X #{:3}, :O #{}}
           (add-move empty-board :3 :X))
        "it adds X's move to the board"))
  (testing "when adding the second move"
    (is (= {:X #{:3}, :O #{:5}}
           (add-move {:X #{:3}, :O #{}} :5 :O))
        "it adds O's move to the board"))
  (testing "when adding the third move"
    (is (= {:X #{:3 :6}, :O #{:5}}
           (add-move {:X #{:3}, :O #{:5}} :6 :X))
        "it adds X's next move to the board")))

(deftest get-total-moves-test
  (testing "when the game has just started"
    (is (= 0
           (get-total-moves {:X #{}, :O #{}}))
        "it returns 0"))
  (testing "when 3 moves have been made"
    (is (= 3
           (get-total-moves {:X #{:1 :4}, :O #{:5}}))
        "it returns 3")))

