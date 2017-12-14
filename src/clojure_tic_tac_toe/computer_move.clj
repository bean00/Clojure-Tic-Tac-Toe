(ns clojure-tic-tac-toe.computer_move
  (:require [clojure-tic-tac-toe.game_handler :as game_handler]
            [clojure-tic-tac-toe.minimax :as minimax]))

(defn make-random-move
  [game-state valid-moves]
  (let [available-moves (game_handler/get-available-moves
                          game-state valid-moves)]
    (rand-nth available-moves)))

(defn make-minimax-move
  [game-state initial-data]
  (minimax/minimax-move game-state initial-data))

