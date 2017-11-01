(ns clojure-tic-tac-toe.core
  (:require [clojure-tic-tac-toe.input_output :as io]
            [clojure-tic-tac-toe.board :as board]))

(defn play-round
  [board player]
  (let [move (io/get-move board player)
        board-with-move (board/add-move board move player)]
    (io/display-board board-with-move)
    board-with-move))

(defn play-game []
  (io/display-introduction)
  (io/display-instructions)
  (io/display-board board/empty-board)
  (let [updated-board (play-round board/empty-board :X)]
    (play-round updated-board :O)))

(defn -main
  "Tic Tac Toe program."
  [& args]
  (play-game))

