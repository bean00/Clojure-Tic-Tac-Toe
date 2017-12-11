(ns clojure-tic-tac-toe.win_checker_test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.board :as board]
            [clojure-tic-tac-toe.win_checker :refer :all]))

(def winning-moves
  '(#{:1 :2 :3} #{:4 :5 :6} #{:7 :8 :9}
    #{:1 :4 :7} #{:2 :5 :8} #{:3 :6 :9}
    #{:1 :5 :9} #{:3 :5 :7}))

(deftest did-player-win?-test
  (testing "when the player won by completing the top row"
    (is (= true
           (did-player-win? {:X #{:1 :2 :3}, :O #{:4 :6}} :X winning-moves))
        "it returns true"))
  (testing "when the player didn't win"
    (is (= false
           (did-player-win? board/empty-board :X winning-moves))
        "it returns false"))
  (testing "when the player completed the middle row"
    (is (= true
           (did-player-win? {:X #{:1 :2 :8}, :O #{:4 :5 :6}} :O winning-moves))
        "it returns true"))
  (testing "when the player completed the bottom row"
    (is (= true
           (did-player-win? {:X #{:7 :8 :9}, :O #{:4 :5}} :X winning-moves))
        "it returns true"))
  (testing "when the player completed the left column"
    (is (= true
           (did-player-win? {:X #{:1 :4 :7}, :O #{:2 :3}} :X winning-moves))
        "it returns true"))
  (testing "when the player completed the middle column"
    (is (= true
           (did-player-win? {:X #{:2 :5 :8}, :O #{:1 :3}} :X winning-moves))
        "it returns true"))
  (testing "when the player completed the right column"
    (is (= true
           (did-player-win? {:X #{:3 :6 :9}, :O #{:1 :2}} :X winning-moves))
        "it returns true"))
  (testing "when the player completed the upper left diagonal"
    (is (= true
           (did-player-win? {:X #{:1 :5 :9}, :O #{:2 :3}} :X winning-moves))
        "it returns true"))
  (testing "when the player completed the upper right diagonal"
    (is (= true
           (did-player-win? {:X #{:3 :5 :7}, :O #{:1 :2}} :X winning-moves))
        "it returns true")))

(deftest which-player-won?-test
  (testing "when the game just started"
    (is (= false
           (which-player-won? board/empty-board winning-moves))
        "it returns false, since neither player won"))
  (testing "when Player X won"
    (is (= :X
           (which-player-won? {:X #{:1 :4 :7}, :O #{:5 :6}} winning-moves))
        "it returns X's keyword")))

(deftest did-either-player-win?-test
  (testing "when a player won"
    (is (= true
           (did-either-player-win? {:X #{:1 :2 :3}, :O #{}} winning-moves))
        "it returns true"))
  (testing "when neither player won"
    (is (= false
           (did-either-player-win? board/empty-board winning-moves))
        "it returns false")))

(deftest calculate-score-test
  (testing "when Player X won"
    (is (= 1
           (calculate-score {:X #{:1 :2 :3} :O #{}} winning-moves))
        "it returns the score for X winning"))
  (testing "when Player O won"
    (is (= -1
           (calculate-score {:X #{} :O #{:1 :2 :3}} winning-moves))
        "it returns the score for O winning"))
  (testing "when the game resulted in a draw"
    (is (= 0
           (calculate-score {:X #{:1 :3 :5 :6 :8}, :O #{:2 :4 :7 :9}} winning-moves))
        "it returns the draw score")))

