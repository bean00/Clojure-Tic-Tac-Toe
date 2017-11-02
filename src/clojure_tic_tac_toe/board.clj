(ns clojure-tic-tac-toe.board
  (:require [clojure.set :as set]
            [clojure-tic-tac-toe.utilities :as utilities]))

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

(defn get-available-moves
  [board]
  (reduce set/difference valid-moves (vals board)))

(defn is-move-invalid?
  [move]
  (not (contains? valid-moves move)))

(defn has-move-been-taken?
  [board move]
  (let [available-moves (get-available-moves board)]
    (not (contains? available-moves move))))

