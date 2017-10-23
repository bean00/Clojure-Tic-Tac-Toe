(ns clojure-tic-tac-toe.core
  (:require [clojure-tic-tac-toe.input_output :as io]
            [clojure-tic-tac-toe.board :as board]))

(defn get-move
  [player]
  (io/prompt-player-for-move player)
  (flush)
  (io/get-move))

(defn int-to-keyword
  [integer]
  (keyword (str integer)))

(defn play-round
  [board player]
  (def move (get-move player))
  (def move-as-keyword (int-to-keyword move))
  (def board-with-move (board/add-move board move-as-keyword player))
  (println (io/create-board-view board-with-move)))

(defn -main
  "Tic Tac Toe program."
  [& args]
  (println "This is a Tic Tac Toe program.\n")

  (println "Example board:")
  (def empty-board { :X #{} :O #{} })
  (println (io/create-board-view empty-board))

  (play-round empty-board :X))
