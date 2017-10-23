(ns clojure-tic-tac-toe.input_output
  (:require [clojure-tic-tac-toe.board :refer [token-at]]
            [clojure.string :as str]))

(defn prompt-player-for-move
  [player]
  (printf "\nPlayer %s, please enter a move from 1-9: ", (name player)))

(defn get-move
  []
  (Integer/parseInt (read-line)))

(def tokens {:1 "1", :2 "2", :3 "3"
             :4 "4", :5 "5", :6 "6"
             :7 "7", :8 "8", :9 "9"
             :X "X", :O "O"})

(defn create-board-view
  [board]
  (str/join "\n" [(format " %s | %s | %s ",
                                     (tokens (token-at board :1)),
                                     (tokens (token-at board :2)),
                                     (tokens (token-at board :3)))
                  "---+---+---"
                  (format " %s | %s | %s ",
                                     (tokens (token-at board :4)),
                                     (tokens (token-at board :5)),
                                     (tokens (token-at board :6)))
                  "---+---+---"
                  (format " %s | %s | %s ",
                                     (tokens (token-at board :7)),
                                     (tokens (token-at board :8)),
                                     (tokens (token-at board :9)))]))
