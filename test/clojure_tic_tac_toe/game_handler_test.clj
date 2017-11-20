(ns clojure-tic-tac-toe.game_handler_test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.board :as board]
            [clojure-tic-tac-toe.console_ui.console_ui_computer_move :as ui_comp_move]
            [clojure-tic-tac-toe.console_ui.console_ui_human_move :as ui_human_move]
            [clojure-tic-tac-toe.console_ui.input_output :as io]
            [clojure-tic-tac-toe.game_handler :refer :all]))

(def h-vs-h-strategies
  {:X ui_human_move/get-human-move, :O ui_human_move/get-human-move})

(def h-vs-c-strategies
  {:X ui_human_move/get-human-move, :O ui_comp_move/have-computer-move})

(def x-won-game-state
  {:board {:X #{:1 :5 :9}, :O #{:2 :3}},
   :player :O, :finished? true, :winner :X,
   :move-strategies h-vs-h-strategies})

(deftest create-game-state-test
  (testing "when creating a game state with move strategies for H vs. H"
    (is (= {:board board/empty-board, :player :X,
            :finished? false, :winner false, :move-strategies h-vs-h-strategies}
           (create-game-state h-vs-h-strategies))
        "it returns the correct game state")))

(deftest get-board-test
  (testing "when getting the board from a winning game state"
    (is (= {:X #{:1 :5 :9}, :O #{:2 :3}}
           (get-board x-won-game-state))
        "it returns the board")))

(deftest get-player-test
  (testing "when getting the player from a winning game state"
    (is (= :O
           (get-player x-won-game-state))
        "it returns the player")))

(deftest finished?-test
  (testing "when checking if an incomplete game is finished"
    (is (= false
           (finished? (create-game-state ui_human_move/get-human-move)))
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
    (is (= ui_human_move/get-human-move
           (get-move-strategy {:board board/empty-board,
                               :player :X, :finished? false, :winner false,
                               :move-strategies h-vs-c-strategies}))
        "it returns the move strategy")))

(deftest token-at-test
  (testing "when X has moved to position 2"
    (is (= :X
           (token-at {:board {:X #{:2}, :O #{}}} :2 ))
        "it returns the token key for X")))

(deftest get-available-moves-test
  (testing "when 5 moves have been made"
    (is (= #{:3 :6 :7 :8}
           (get-available-moves {:board {:X #{:1 :2 :9}, :O #{:4 :5}}}))
        "it returns the available moves")))

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

(deftest create-next-game-state-test
  (testing "when a player wins"
    (is (= {:board {:X #{:1 :2 :3}, :O #{:4 :5}},
            :player :O, :finished? true, :winner :X,
            :move-strategies h-vs-h-strategies}
           (create-next-game-state
             {:board {:X #{:1 :2}, :O #{:4 :5}},
              :player :X, :finished? false, :winner false,
              :move-strategies h-vs-h-strategies}
             :3))
        "it returns the properly updated game state")))

(deftest display-board-test
  (testing "when a game state is passed in"
    (is (= true
           (str/includes?
             (with-out-str
               (display-board x-won-game-state))
             " X | O | O "))
        "it displays the board")))

(deftest display-game-over-message-test
  (testing "when a player has won"
    (is (= true
           (str/includes?
             (with-out-str
               (display-game-over-message :X))
             "won"))
        "it displays the appropriate message")))

(deftest is-move-invalid?-test
  (testing "when an invalid move is passed in"
    (is (= true
           (is-move-invalid? :f))
        "it returns true")))

(deftest has-move-been-taken?-test
  (testing "when the move has been taken"
    (is (= true
           (has-move-been-taken? {:X #{:1}, :O #{}} :1))
        "it returns true")))

(deftest get-available-moves-test
  (testing "when 5 moves have been made"
    (is (= #{:3 :6 :7 :8}
           (get-available-moves {:X #{:1 :2 :9}, :O #{:4 :5}}))
        "it returns the available moves")))

