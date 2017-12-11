(ns clojure-tic-tac-toe.game_handler
  (:require [clojure.set :as set]
            [clojure-tic-tac-toe.board :as board]
            [clojure-tic-tac-toe.utilities :refer [set-to-list-or-nil]]
            [clojure-tic-tac-toe.win_checker :as win_checker]))

(def empty-board board/empty-board)

(def tokens (keys empty-board))

(defn create-game-state
  [board player finished? winner valid-moves winning-moves move-strategies]
  { :board board,
    :player player,
    :finished? finished?,
    :winner winner,
    :moves valid-moves,
    :winning-moves winning-moves
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

(defn get-valid-moves
  [game-state]
  (:moves game-state))

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


(defn- get-moves
  [game-state]
  (let [board (get-board game-state)
        valid-moves (get-valid-moves game-state)]
    (reduce set/difference valid-moves (vals board))))

(defn get-available-moves
  [game-state]
  (let [available-moves (get-moves game-state)
        list-of-moves (set-to-list-or-nil available-moves)]
    list-of-moves))


(defn is-move-invalid?
  [game-state move]
  (let [valid-moves (get-valid-moves game-state)]
    (not (contains? valid-moves move))))


(defn has-move-been-taken?
  [game-state move]
  (let [available-moves (get-moves game-state)]
    (not (contains? available-moves move))))


(defn calculate-score
  [game-state]
  (let [board (get-board game-state)]
    (win_checker/calculate-score board))) ; TODO: pass in game-state


(defn switch-player
  [player]
  (first
    (remove #{player} tokens)))


(defn- update-board
  [game-state board]
  (assoc game-state :board board))


(defn- is-board-full?
  [game-state]
  (empty? (get-moves game-state)))

(defn- is-game-finished?
  [game-state]
  (let [board (get-board game-state)]
    (or (win_checker/did-either-player-win? board) ; TODO: pass in game-state
        (is-board-full? game-state))))

(defn add-move
  [game-state move]
  (let [board (get-board game-state)
        player (get-player game-state)
        updated-board (board/add-move board move player)
        next-player (switch-player player)
        valid-moves (get-valid-moves game-state)
        move-strategies (get-move-strategies game-state)
        updated-game-state (update-board game-state updated-board)
        game-is-finished (is-game-finished? updated-game-state)
        winner (win_checker/which-player-won? updated-board)] ; TODO: pass in updated-game-state
    { :board updated-board
      :player next-player
      :finished? game-is-finished
      :winner winner
      :moves valid-moves
      ; :winning-moves winning-moves TODO
      :move-strategies move-strategies }))

