(ns clojure-tic-tac-toe.game_handler
  (:require [clojure-tic-tac-toe.board :as board]
            [clojure-tic-tac-toe.utilities :refer [set-to-list-or-nil]]
            [clojure-tic-tac-toe.win_checker :as win_checker]))

(def empty-board board/empty-board)

(def tokens (keys empty-board))

(defn create-game-state
  [move-strategies]
  { :board empty-board,
    :player :X,
    :finished? false,
    :winner false,
    :move-strategies move-strategies })


(defn- get-board
  [game-state]
  (:board game-state))

(defn get-player
  [game-state]
  (:player game-state))

(defn finished?
  [game-state]
  (:finished? game-state))

(defn get-winner
  [game-state]
  (:winner game-state))

(defn get-move-strategies
  [game-state]
  (:move-strategies game-state))

(defn get-move-strategy
  [game-state]
  (let [player (get-player game-state)]
    (player (:move-strategies game-state))))


(defn token-at
  [game-state position]
  (let [board (get-board game-state)]
    (board/token-at board position)))

(defn get-available-moves
  [game-state]
  (let [board (get-board game-state)
        available-moves (board/get-available-moves board)
        list-of-moves (set-to-list-or-nil available-moves)]
    list-of-moves))

(defn is-move-invalid?
  [move]
  (board/is-move-invalid? move))

(defn has-move-been-taken?
  [game-state move]
  (let [board (get-board game-state)]
    (board/has-move-been-taken? board move)))


(defn calculate-score
  [game-state]
  (let [board (get-board game-state)]
    (win_checker/calculate-score board)))


(defn switch-player
  [player]
  (first
    (remove #{player} tokens)))

(defn- is-game-finished?
  [board]
  (or (win_checker/did-either-player-win? board)
      (board/is-board-full? board)))

(defn add-move
  [game-state move]
  (let [board (get-board game-state)
        player (get-player game-state)
        updated-board (board/add-move board move player)
        next-player (switch-player player)
        game-is-finished (is-game-finished? updated-board)
        winner (win_checker/which-player-won? updated-board)
        move-strategies (get-move-strategies game-state)]
    { :board updated-board
      :player next-player
      :finished? game-is-finished
      :winner winner
      :move-strategies move-strategies }))

