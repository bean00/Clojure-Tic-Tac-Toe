(ns clojure-tic-tac-toe.console_ui.console_ui_human_move
  (:require [clojure-tic-tac-toe.board :as board]
            [clojure-tic-tac-toe.console_ui.input_output :as io]))

(defn get-human-move
  [board player]
  (loop [move (io/get-move player)]
    (cond
      (board/is-move-invalid? move)
        (recur (io/get-move-if-move-is-invalid move player))
      (board/has-move-been-taken? board move)
        (recur (io/get-move-if-move-was-taken move player))
      :else
        move)))

