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

(deftest get-winner-test
  (testing "when getting the winner from a winning game state"
    (is (= :X
           (get-winner {:board {:X #{:1 :2 :3}, :O #{:4 :5}}, :player :O,
                        :finished? true, :winner :X}))
        "it returns the winner")))

(deftest create-next-game-state-test
  (testing "when a player wins"
    (is (= {:board {:X #{:1 :2 :3}, :O #{:4 :5}}, :player :O,
            :finished? true, :winner :X}
           (create-next-game-state
             {:board {:X #{:1 :2}, :O #{:4 :5}}, :player :X,
              :finished? false, :winner false}
             :3))
        "it returns the properly updated game state")))

