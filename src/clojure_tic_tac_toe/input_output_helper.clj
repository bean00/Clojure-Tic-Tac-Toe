(ns clojure-tic-tac-toe.input_output_helper
  (:require [clojure.string :as str]
            [clojure-tic-tac-toe.board :as board]))

(defn- prompt-player-for-move
  [player]
  (printf "\nPlayer %s, please enter your move: ", (name player)))

(defn- display-invalid-move-message
  [move]
  (printf "\n<!> Error: Move \"%s\" is invalid. Must be from 1-9.", (name move)))

(defn- get-input []
  (keyword (str/trim (read-line))))

(defn get-single-move
  [player]
  (prompt-player-for-move player)
  (flush)
  (let [move (get-input)]
    (if (board/is-move-valid? move)
      move
      (display-invalid-move-message move))))

