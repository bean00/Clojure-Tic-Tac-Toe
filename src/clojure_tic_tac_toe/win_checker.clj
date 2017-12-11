(ns clojure-tic-tac-toe.win_checker
  (:require [clojure.set :as set]
            [clojure-tic-tac-toe.utilities :refer [contains-set?]]))

(defn did-player-win?
  [board player winning-moves]
  (let [player-moves (player board)]
    (contains-set? winning-moves player-moves)))

(defn which-player-won?
  [board winning-moves]
  (cond
    (did-player-win? board :X winning-moves) :X
    (did-player-win? board :O winning-moves) :O
    :else false))

(defn did-either-player-win?
  [board winning-moves]
  (boolean (which-player-won? board winning-moves)))

(defn calculate-score
  [board winning-moves]
  (let [winner (which-player-won? board winning-moves)]
    (cond
      (= winner :X) 1
      (= winner :O) -1
      :else 0)))

