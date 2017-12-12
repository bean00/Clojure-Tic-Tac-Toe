(ns clojure-tic-tac-toe.game_handler_test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.board :as board]
            [clojure-tic-tac-toe.default_winning_moves :as default_winning_moves]
            [clojure-tic-tac-toe.game_handler :refer :all]))

(def valid-moves
  #{:1 :2 :3 :4 :5 :6 :7 :8 :9})

(def valid-moves-as-list
  '(:1 :2 :3 :4 :5 :6 :7 :8 :9))

(def winning-moves
  default_winning_moves/winning-moves)

(def h-vs-h-strategies
  {:X :1, :O :2})

(def x-won-game-state
  {:board {:X #{:1 :5 :9}, :O #{:2 :3}},
   :player :O, :finished? true, :winner :X,
   :moves valid-moves, :winning-moves winning-moves,
   :move-strategies h-vs-h-strategies})

(deftest create-game-state-test
  (testing "when creating a game state with move strategies for H vs. H (3x3)"
    (is (= {:board board/empty-board, :player :X,
            :finished? false, :winner false, :moves valid-moves,
            :winning-moves winning-moves, :move-strategies h-vs-h-strategies}
           (create-game-state
             board/empty-board :X false false valid-moves winning-moves
             h-vs-h-strategies))
        "it returns the correct game state")))

(deftest get-player-test
  (testing "when getting the player from a winning game state"
    (is (= :O
           (get-player x-won-game-state))
        "it returns the player")))

(deftest finished?-test
  (testing "when checking if an incomplete game is finished"
    (is (= false
           (finished? (create-game-state
                        board/empty-board :X false false valid-moves
                        winning-moves :1)))
        "it returns false")))

(deftest get-winner-test
  (testing "when getting the winner from a winning game state"
    (is (= :X
           (get-winner x-won-game-state))
        "it returns the winner")))

(deftest get-valid-moves-test
  (testing "when getting the valid moves from a winning game state"
    (is (= valid-moves
           (get-valid-moves x-won-game-state))
        "it returns the valid moves")))

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
  (testing "when no move has been made"
    (is (= valid-moves-as-list
           (sort
             (get-available-moves {:board empty-board, :moves valid-moves})))
        "it returns all the moves"))
  (testing "when moves have been made"
    (is (= '(:1 :2 :3 :4)
           (sort
             (get-available-moves {:board {:X #{:5 :7 :8}, :O #{:6 :9}},
                                   :moves valid-moves})))
        "it returns a list of available moves"))
  (testing "when the board is full"
    (is (= nil
           (get-available-moves {:board {:X #{:1 :2 :3 :4 :5},
                                         :O #{:6 :7 :8 :9}},
                                 :moves valid-moves}))
        "it returns nil")))

(deftest is-move-invalid?-test
  (testing "when an invalid move is passed in"
    (is (= true
           (is-move-invalid? x-won-game-state :e))
        "it returns true"))
  (testing "when a valid move is passed in"
    (is (= false
           (is-move-invalid? x-won-game-state :3))
        "it returns false")))

(deftest has-move-been-taken?-test
  (testing "when a move has been taken"
    (is (= true
           (has-move-been-taken? {:board {:X #{:4}, :O #{}},
                                  :moves valid-moves}
                                 :4))
        "it returns true"))
  (testing "when a move has not been taken"
    (is (= false
           (has-move-been-taken? {:board {:X #{:3}, :O #{:5}},
                                  :moves valid-moves}
                                 :9))
        "it returns false")))

(deftest calculate-score-test
  (testing "when Player X won"
    (is (= 1
           (calculate-score {:board {:X #{:1 :2 :3} :O #{}},
                             :winning-moves winning-moves}))
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
            :moves valid-moves, :winning-moves winning-moves,
            :move-strategies h-vs-h-strategies}
           (add-move
             {:board {:X #{:1 :2}, :O #{:4 :5}},
              :player :X, :finished? false, :winner false,
              :moves valid-moves, :winning-moves winning-moves,
              :move-strategies h-vs-h-strategies}
             :3))
        "it returns the properly updated game state")))

