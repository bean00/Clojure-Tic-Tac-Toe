(ns clojure-tic-tac-toe.game_handler
  (:require [clojure-tic-tac-toe.board :as board]))

(def starting-game-state
  {:board board/empty-board, :player :X, :finished? false})

(defn get-board
  [game-state]
  (:board game-state))

(defn finished?
  [game-state]
  (:finished? game-state))

(defn- switch-player
  [board player]
  (let [tokens (keys board)]
    (first
      (remove #{player} tokens))))

(defn- is-game-finished?
  [board]
  (empty? (board/get-available-moves board)))

(defn create-next-game-state
  [{:keys [board player]} move]
  (let [updated-board (board/add-move board move player)
        next-player (switch-player board player)
        game-is-finished (is-game-finished? updated-board)]
    { :board updated-board
      :player next-player
      :finished? game-is-finished }))

