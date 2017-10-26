(ns clojure-tic-tac-toe.input_output
  (:require [clojure-tic-tac-toe.board :as board]
            [clojure.string :as str]))

(defn display-introduction []
  (println "This is a Tic Tac Toe program.\n"))

(defn prompt-player-for-move
  [player]
  (printf "\nPlayer %s, please enter a move from 1-9: ", (name player)))

(defn get-move
  [player]
  (prompt-player-for-move player)
  (flush)
  (keyword (str (Integer/parseInt (read-line)))))

(def tokens {:1 "1", :2 "2", :3 "3"
             :4 "4", :5 "5", :6 "6"
             :7 "7", :8 "8", :9 "9"
             :X "X", :O "O"})

(defn- create-row
  [board token-key-1 token-key-2 token-key-3]
  (format " %s | %s | %s ",
          (tokens (board/token-at board token-key-1)),
          (tokens (board/token-at board token-key-2)),
          (tokens (board/token-at board token-key-3))))

(defn create-view-from-board
  [board]
  (str/join "\n" [(create-row board :1 :2 :3)
                  "---+---+---"
                  (create-row board :4 :5 :6)
                  "---+---+---"
                  (create-row board :7 :8 :9)]))

(defn display-board
  [board]
  (println (create-view-from-board board)))

(defn display-example-board []
  (println "Example board:")
  (display-board board/empty-board))

