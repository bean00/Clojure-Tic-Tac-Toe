(ns clojure-tic-tac-toe.board
  (:require [clojure-tic-tac-toe.utilities :as utilities]))

(def valid-moves #{:1 :2 :3
                   :4 :5 :6
                   :7 :8 :9})

(def empty-board { :X #{} :O #{} })

(defn add-move
  [board move player]
  (update board player conj move))

(defn token-at
  [board position]
  (or
    (first (keys (utilities/get-map-with-value-in-set board position)))
    position))

(defn is-move-valid?
  [move]
  (contains? valid-moves move))

