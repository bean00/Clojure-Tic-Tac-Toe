(ns clojure-tic-tac-toe.game_handler
  (:require [clojure-tic-tac-toe.utilities :as utilities]
            [clojure-tic-tac-toe.board :as board]
            [clojure-tic-tac-toe.win_checker :as win-checker]))

(def empty-board board/empty-board)

(defn create-game-state
  [move-strategies]
  { :board board/empty-board,
    :player :X,
    :finished? false,
    :winner false,
    :move-strategies move-strategies })

(def valid-game-modes #{:h :c})

(defn is-game-mode-invalid?
  [game-mode]
  (not (contains? valid-game-modes game-mode)))

(defn get-board
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
  (let [board (get-board game-state)]
    (board/get-available-moves board)))

(defn is-move-invalid?
  [move]
  (board/is-move-invalid? move))

(defn has-move-been-taken?
  [game-state move]
  (let [board (get-board game-state)]
    (board/has-move-been-taken? board move)))


(defn- switch-player
  [game-state]
  (let [board (get-board game-state)
        player (get-player game-state)
        tokens (keys board)]
    (first
      (remove #{player} tokens))))

(defn- is-game-finished?
  [updated-board player]
  (or (win-checker/has-player-won? updated-board player)
      (board/is-board-full? updated-board)))

(defn create-next-game-state
  [game-state move]
  (let [board (get-board game-state)
        player (get-player game-state)
        updated-board (board/add-move board move player)
        next-player (switch-player game-state)
        game-is-finished (is-game-finished? updated-board player)
        winner (win-checker/which-player-won? updated-board player)
        move-strategies (get-move-strategies game-state)]
    { :board updated-board
      :player next-player
      :finished? game-is-finished
      :winner winner
      :move-strategies move-strategies }))

