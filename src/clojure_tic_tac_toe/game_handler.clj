(ns clojure-tic-tac-toe.game_handler
  (:require [clojure-tic-tac-toe.board :as board]
            [clojure-tic-tac-toe.win_checker :as win-checker]))

(def starting-game-state
  {:board board/empty-board, :player :X, :finished? false, :winner false})

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
  [board player]
  (or (win-checker/has-player-won? board player)
      (board/is-board-full? board)))

(defn create-next-game-state
  [{:keys [board player]} move]
  (let [updated-board (board/add-move board move player)
        next-player (switch-player board player)
        game-is-finished (is-game-finished? updated-board)
        winner (win-checker/which-player-won? updated-board player)]
    { :board updated-board
      :player next-player
      :finished? game-is-finished
      :winner winner }))

