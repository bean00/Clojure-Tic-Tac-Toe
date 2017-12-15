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
  { :valid-moves valid-moves
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


(defn get-total-moves
  [game-state]
  (let [board (:board game-state)]
    (board/get-total-moves board)))


(defn- get-moves
  [game-state valid-moves]
  (let [board (:board game-state)]
    (reduce set/difference valid-moves (vals board))))

(defn get-available-moves
  [game-state valid-moves]
  (let [available-moves (get-moves game-state valid-moves)
        list-of-moves (set-to-list-or-nil available-moves)]
    list-of-moves))


(defn is-move-invalid?
  [valid-moves move]
  (not (contains? valid-moves move)))


(defn has-move-been-taken?
  [game-state valid-moves move]
  (let [available-moves (get-moves game-state valid-moves)]
    (not (contains? available-moves move))))


(defn calculate-score
  [game-state winning-moves]
  (let [board (:board game-state)]
    (win_checker/calculate-score board winning-moves)))


(defn get-winner
  [game-state winning-moves]
  (let [board (:board game-state)
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
  [game-state valid-moves]
  (empty? (get-moves game-state valid-moves)))

(defn- is-game-finished?
  [{:keys [board] :as game-state}
   {:keys [winning-moves valid-moves]}]
  (or (win_checker/did-either-player-win? board winning-moves)
      (is-board-full? game-state valid-moves)))

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

