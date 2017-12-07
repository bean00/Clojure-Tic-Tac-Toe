(ns clojure-tic-tac-toe.game_handler_test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.board :as board]
            [clojure-tic-tac-toe.game_handler :refer :all]))

(def valid-moves
  #{:1 :2 :3 :4 :5 :6 :7 :8 :9})

(def h-vs-h-strategies
  {:X :1, :O :2})

(def x-won-game-state
  {:board {:X #{:1 :5 :9}, :O #{:2 :3}},
   :player :O, :finished? true, :winner :X,
   :move-strategies h-vs-h-strategies})

(deftest create-game-state-test
  (testing "when creating a game state with move strategies for H vs. H (3x3)"
    (is (= {:board board/empty-board, :player :X,
            :finished? false, :winner false, :move-strategies h-vs-h-strategies
            :moves valid-moves}
           (create-game-state valid-moves h-vs-h-strategies))
        "it returns the correct game state")))

(deftest get-player-test
  (testing "when getting the player from a winning game state"
    (is (= :O
           (get-player x-won-game-state))
        "it returns the player")))

(deftest finished?-test
  (testing "when checking if an incomplete game is finished"
    (is (= false
           (finished? (create-game-state valid-moves :1)))
        "it returns false")))

(deftest get-winner-test
  (testing "when getting the winner from a winning game state"
    (is (= :X
           (get-winner x-won-game-state))
        "it returns the winner")))

(deftest get-move-strategies-test
  (testing "when getting the move strategies a winning game state"
    (is (= h-vs-h-strategies
           (get-move-strategies x-won-game-state))
        "it returns the move strategies")))

(deftest get-move-strategy-test
  (testing "when getting a move strategy from the game state"
    (is (= :1
           (get-move-strategy {:board board/empty-board,
                               :player :X, :finished? false, :winner false,
                               :move-strategies h-vs-h-strategies}))
        "it returns the move strategy")))

(deftest token-at-test
  (testing "when X has moved to position 2"
    (is (= :X
           (token-at {:board {:X #{:2}, :O #{}}} :2 ))
        "it returns the token key for X")))

(deftest get-available-moves-test
  (testing "when moves have been made"
    (is (= '(:1 :2 :3 :4)
           (sort
             (get-available-moves {:board {:X #{:5 :7 :8}, :O #{:6 :9}}})))
        "it returns a list of available moves")))

(deftest is-move-invalid?-test
  (testing "when an invalid move is passed in"
    (is (= true
           (is-move-invalid? :f))
        "it returns true")))

(deftest has-move-been-taken?-test
  (testing "when the move has been taken"
    (is (= true
           (has-move-been-taken? {:board {:X #{:1}, :O #{}}} :1))
        "it returns true")))

(deftest calculate-score-test
  (testing "when Player X won"
    (is (= 1
           (calculate-score {:board {:X #{:1 :2 :3} :O #{}}}))
        "it returns the score for X winning")))

(deftest switch-player-test
  (testing "when starting with one player"
    (is (= :O
           (switch-player :X))
        "it returns the other player")))

(deftest add-move-test
  (testing "when a player wins"
    (is (= {:board {:X #{:1 :2 :3}, :O #{:4 :5}},
            :player :O, :finished? true, :winner :X,
            :move-strategies h-vs-h-strategies}
           (add-move
             {:board {:X #{:1 :2}, :O #{:4 :5}},
              :player :X, :finished? false, :winner false,
              :move-strategies h-vs-h-strategies}
             :3))
        "it returns the properly updated game state")))

