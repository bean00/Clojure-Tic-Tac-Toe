(ns clojure-tic-tac-toe.win_checker_test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.board :as board]
            [clojure-tic-tac-toe.win_checker :refer :all]))

(deftest has-player-won?-test
  (testing "when the player won by completing the top row"
    (is (= true
           (has-player-won? {:X #{:1 :2 :3}, :O #{:4 :6}} :X))
        "it returns true"))
  (testing "when the player didn't win"
    (is (= false
           (has-player-won? board/empty-board :X))
        "it returns false"))
  (testing "when the player completed the middle row"
    (is (= true
           (has-player-won? {:X #{:1 :2 :8}, :O #{:4 :5 :6}} :O))
        "it returns true"))
  (testing "when the player completed the bottom row"
    (is (= true
           (has-player-won? {:X #{:7 :8 :9}, :O #{:4 :5}} :X))
        "it returns true"))
  (testing "when the player completed the left column"
    (is (= true
           (has-player-won? {:X #{:1 :4 :7}, :O #{:2 :3}} :X))
        "it returns true"))
  (testing "when the player completed the middle column"
    (is (= true
           (has-player-won? {:X #{:2 :5 :8}, :O #{:1 :3}} :X))
        "it returns true"))
  (testing "when the player completed the right column"
    (is (= true
           (has-player-won? {:X #{:3 :6 :9}, :O #{:1 :2}} :X))
        "it returns true"))
  (testing "when the player completed the upper left diagonal"
    (is (= true
           (has-player-won? {:X #{:1 :5 :9}, :O #{:2 :3}} :X))
        "it returns true"))
  (testing "when the player completed the upper right diagonal"
    (is (= true
           (has-player-won? {:X #{:3 :5 :7}, :O #{:1 :2}} :X))
        "it returns true")))

(deftest which-player-won?-test
  (testing "when the game just started"
    (is (= false
           (which-player-won? board/empty-board :X))
        "it returns false, since neither player won"))
  (testing "when Player X won"
    (is (= :X
           (which-player-won? {:X #{:1 :4 :7}, :O #{:5 :6}} :X))
        "it returns X's keyword")))

