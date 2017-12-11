(ns clojure-tic-tac-toe.win_checker
  (:require [clojure.set :as set]
            [clojure-tic-tac-toe.tic_tac_toe_rules :as rules]
            [clojure-tic-tac-toe.utilities :refer [contains-set?]]))

; TODO: refactor functions to take in game-state, the extract board and winning-moves from game-state

(defn did-player-win?
  [board player]
  (let [player-moves (player board)]
    (contains-set? rules/winning-sets player-moves)))

(defn which-player-won?
  [board]
  (cond
    (did-player-win? board :X) :X
    (did-player-win? board :O) :O
    :else false))

(defn did-either-player-win?
  [board]
  (boolean (which-player-won? board)))

(defn calculate-score
  [board]
  (let [winner (which-player-won? board)]
    (cond
      (= winner :X) 1
      (= winner :O) -1
      :else 0)))

