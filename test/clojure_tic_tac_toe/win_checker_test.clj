(ns clojure-tic-tac-toe.win_checker_test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.board :as board]
            [clojure-tic-tac-toe.win_checker :refer :all]))

(deftest has-player-won?-test
  (testing "when the player won"
    (is (= true
           (has-player-won? {:X #{:1 :2 :3}, :O #{:4 :6}} :X))
        "it returns true"))
  (testing "when the player didn't win"
    (is (= false
           (has-player-won? board/empty-board :X))
        "it returns false")))

(deftest which-player-won?-test
  (testing "when the game just started"
    (is (= false
           (which-player-won? board/empty-board :X))
        "it returns false, since neither player won"))
  (testing "when Player X has won"
    (is (= :X
           (which-player-won? {:X #{:1 :4 :7}, :O #{:5 :6}} :X))
        "it returns X's keyword")))

