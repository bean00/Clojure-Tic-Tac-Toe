(ns clojure-tic-tac-toe.game_handler
  (:require [clojure.set :as set]
            [clojure-tic-tac-toe.board :as board]
            [clojure-tic-tac-toe.utilities :refer [set-to-list-or-nil]]
            [clojure-tic-tac-toe.win_checker :as win_checker]))

(def empty-board board/empty-board)

(def tokens (keys empty-board))

(defn create-game-state
  [board player finished?]
  { :board board
    :player player
    :finished? finished? })


(defn create-initial-data
  [valid-moves winning-moves move-strategies create-view]
  { :moves valid-moves
    :winning-moves winning-moves
    :move-strategies move-strategies
    :create-view create-view })

(defn get-move-strategy
  [game-state initial-data]
  (let [player (:player game-state)]
    (player (:move-strategies initial-data))))


(defn token-at
  [game-state position]
  (let [board (:board game-state)]
    (board/token-at board position)))


(defn- get-moves
  [game-state initial-data]
  (let [board (:board game-state)
        valid-moves (:moves initial-data)]
    (reduce set/difference valid-moves (vals board))))

(defn get-available-moves
  [game-state initial-data]
  (let [available-moves (get-moves game-state initial-data)
        list-of-moves (set-to-list-or-nil available-moves)]
    list-of-moves))


(defn is-move-invalid?
  [initial-data move]
  (let [valid-moves (:moves initial-data)]
    (not (contains? valid-moves move))))


(defn has-move-been-taken?
  [game-state initial-data move]
  (let [available-moves (get-moves game-state initial-data)]
    (not (contains? available-moves move))))


(defn calculate-score
  [game-state initial-data]
  (let [board (:board game-state)
        winning-moves (:winning-moves initial-data)]
    (win_checker/calculate-score board winning-moves)))


(defn get-winner
  [game-state initial-data]
  (let [board (:board game-state)
        winning-moves (:winning-moves initial-data)
        winner (win_checker/which-player-won? board winning-moves)]
    winner))


(defn- switch-player
  [player]
  (first
    (remove #{player} tokens)))

(defn- update-board
  [game-state board]
  (assoc game-state :board board))

(defn- is-board-full?
  [game-state initial-data]
  (empty? (get-moves game-state initial-data)))

(defn- is-game-finished?
  [game-state initial-data]
  (let [board (:board game-state)
        winning-moves (:winning-moves initial-data)]
    (or (win_checker/did-either-player-win? board winning-moves)
        (is-board-full? game-state initial-data))))

(defn add-move
  [game-state initial-data move]
  (let [board (:board game-state)
        player (:player game-state)
        updated-board (board/add-move board move player)
        next-player (switch-player player)
        updated-game-state (update-board game-state updated-board)
        game-is-finished (is-game-finished? updated-game-state initial-data)]
    { :board updated-board
      :player next-player
      :finished? game-is-finished }))

