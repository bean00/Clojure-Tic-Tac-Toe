(ns clojure-tic-tac-toe.computer_move
  (:require [clojure-tic-tac-toe.game_handler :as game_handler]
            [clojure-tic-tac-toe.utilities :refer [set-to-list-or-nil]]))

(defn make-random-move
  [game-state]
  (let [available-moves (game_handler/get-available-moves game-state)
        unsorted-list (set-to-list-or-nil available-moves)]
    (rand-nth unsorted-list)))

