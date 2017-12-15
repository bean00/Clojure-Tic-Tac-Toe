(ns clojure-tic-tac-toe.game_handler_test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.board :as board]
            [clojure-tic-tac-toe.test_helper :as test_helper]
            [clojure-tic-tac-toe.game_handler :refer :all]))

(def valid-moves
  test_helper/valid-moves)

(def valid-moves-as-sorted-list
  (sort (into (list) valid-moves)))

(def winning-moves
  test_helper/winning-moves)

(def h-vs-h-strategies
  {:X :1, :O :2})

(def x-won-game-state
  {:board {:X #{:1 :5 :9}, :O #{:2 :3}},
   :player :O,
   :finished? true})

(defn create-view-dummy []
  "")

(def initial-data
  {:valid-moves valid-moves,
   :winning-moves winning-moves,
   :move-strategies h-vs-h-strategies,
   :create-view create-view-dummy})

(deftest create-game-state-test
  (testing "when creating a game state with move strategies for H vs. H (3x3)"
    (is (= {:board board/empty-board, :player :X, :finished? false}
           (create-game-state board/empty-board :X false))
        "it returns the correct game state")))

(deftest create-initial-data-test
  (testing "when creating the initial data needed for the game"
    (is (= {:valid-moves valid-moves, :winning-moves winning-moves,
            :move-strategies h-vs-h-strategies, :create-view create-view-dummy}
           (create-initial-data
             valid-moves winning-moves h-vs-h-strategies create-view-dummy))
        "it returns the correct data")))

(deftest get-move-strategy-test
  (testing "when getting a single move strategy"
    (is (= :1
           (get-move-strategy {:board board/empty-board,
                               :player :X, :finished? false}
                              initial-data))
        "it returns the move strategy")))

(deftest token-at-test
  (testing "when X has moved to position 2"
    (is (= :X
           (token-at {:board {:X #{:2}, :O #{}}} :2 ))
        "it returns the token key for X")))

(deftest get-available-moves-test
  (testing "when no move has been made"
    (is (= valid-moves-as-sorted-list
           (sort
             (get-available-moves {:board empty-board} valid-moves)))
        "it returns all the moves"))
  (testing "when moves have been made"
    (is (= '(:1 :2 :3 :4)
           (sort
             (get-available-moves {:board {:X #{:5 :7 :8}, :O #{:6 :9}}}
                                  valid-moves)))
        "it returns a list of available moves"))
  (testing "when the board is full"
    (is (= nil
           (get-available-moves {:board {:X #{:1 :2 :3 :4 :5},
                                         :O #{:6 :7 :8 :9}}}
                                 valid-moves))
        "it returns nil")))

(deftest is-move-invalid?-test
  (testing "when an invalid move is passed in"
    (is (= true
           (is-move-invalid? valid-moves :e))
        "it returns true"))
  (testing "when a valid move is passed in"
    (is (= false
           (is-move-invalid? valid-moves :3))
        "it returns false")))

(deftest has-move-been-taken?-test
  (testing "when a move has been taken"
    (is (= true
           (has-move-been-taken? {:board {:X #{:4}, :O #{}}} valid-moves
                                 :4))
        "it returns true"))
  (testing "when a move has not been taken"
    (is (= false
           (has-move-been-taken? {:board {:X #{:3}, :O #{:5}}} valid-moves
                                 :9))
        "it returns false")))

(deftest calculate-score-test
  (testing "when Player X won"
    (is (= 1
           (calculate-score {:board {:X #{:1 :2 :3} :O #{}}} winning-moves))
        "it returns the score for X winning")))

(deftest get-winner-test
  (testing "when getting the winner from a winning game state"
    (is (= :X
           (get-winner x-won-game-state winning-moves))
        "it returns the winner")))

(deftest add-move-test
  (testing "when a player wins"
    (is (= {:board {:X #{:1 :2 :3}, :O #{:4 :5}},
            :player :O, :finished? true}
           (add-move
             {:board {:X #{:1 :2}, :O #{:4 :5}},
              :player :X, :finished? false}
             initial-data
             :3))
        "it returns the properly updated game state")))

