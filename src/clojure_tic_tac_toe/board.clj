(ns clojure-tic-tac-toe.board
  (:require [clojure-tic-tac-toe.helper :as helper]))

(def empty-board { :X #{} :O #{} })

(defn add-move
  [board move player]
  (update board player conj move))

(defn token-at
  [board position]
  (or
    (first (keys (helper/get-map-with-value-in-set board position)))
    position))

