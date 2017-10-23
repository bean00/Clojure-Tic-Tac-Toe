(ns clojure-tic-tac-toe.board-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.board :refer :all]))

(deftest add-move-test
  (testing "when adding the first move"
    (is (= { :X #{:3} :O #{} }
           (add-move { :X #{} :O #{} }
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

(deftest filter-out-player-moves-test
  (testing "when player X has moved to a position"
    (is (= { :X #{:1} }
           (filter-out-player-moves { :X #{:1} :O #{} } :1 ))
        "it returns the moves for X"))
  (testing "when player O has moved to a position"
    (is (= { :O #{:2} }
           (filter-out-player-moves { :X #{} :O #{:2} } :2 ))
        "it returns the moves for O")))

(deftest token-at-test
  (testing "when neither player has moved to the position"
    (is (= :1
           (token-at { :X #{} :O #{} } :1 ))
        "it returns the token key for that position"))
  (testing "when X has moved to position 1"
    (is (= :X
           (token-at { :X #{:1} :O #{} } :1 ))
        "it returns the token key for X")))

