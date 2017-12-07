(ns clojure-tic-tac-toe.console_ui.console_ui_human_move
  (:require [clojure-tic-tac-toe.console_ui.input_output :as io]
            [clojure-tic-tac-toe.game_handler :as game_handler]))

(defn get-human-move
  [game-state]
  (let [player (game_handler/get-player game-state)]
    (loop [move (io/get-move player)]
      (cond
        (game_handler/is-move-invalid? move)
          (recur (io/get-move-if-move-is-invalid move player))
        (game_handler/has-move-been-taken? game-state move)
          (recur (io/get-move-if-move-was-taken move player))
        :else
          move))))

