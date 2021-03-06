(ns clojure-tic-tac-toe.board
  (:require [clojure.set :as set]
            [clojure-tic-tac-toe.utilities :as utilities]))

(def empty-board {:X #{}, :O #{}})

(defn token-at
  [board position]
  (or
    (first (keys (utilities/get-map-with-value-in-set board position)))
    position))

(defn add-move
  [board move player]
  (update board player conj move))

(defn get-total-moves
  [board]
  (let [players-moves (vals board)
        all-moves (apply set/union players-moves)
        total-moves (count all-moves)]
    total-moves))

