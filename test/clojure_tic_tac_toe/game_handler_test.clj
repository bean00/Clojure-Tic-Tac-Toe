(ns clojure-tic-tac-toe.game_handler_test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.board :as board]
            [clojure-tic-tac-toe.game_handler :refer :all]))

(deftest get-board-test
  (testing "when getting the board from the starting game state"
    (is (= board/empty-board
           (get-board starting-game-state))
        "it returns the empty board")))

(deftest finished?-test
  (testing "when checking if an incomplete game is finished"
    (is (= false
           (finished? starting-game-state))
        "it returns false")))

(deftest create-next-game-state-test
  (testing "when a game state and a move are passed in"
    (is (= {:board {:X #{:1 :5 :6 :3 :8}, :O #{:2 :9 :4 :7}}, :player :O, :finished? true}
           (create-next-game-state
             {:board {:X #{:1 :5 :6 :3}, :O #{:2 :9 :4 :7}}, :player :X, :finished? false}
             :8))
        "it returns the properly updated game state")))

