(ns clojure-tic-tac-toe.win_checker
  (:require [clojure.set :as set]
            [clojure-tic-tac-toe.tic_tac_toe_rules :as rules]
            [clojure-tic-tac-toe.utilities :refer [contains-set?]]))

(defn has-player-won?
  [board player]
  (let [player-moves (player board)]
    (contains-set? rules/winning-sets player-moves)))

(defn which-player-won?
  [board player]
  (and (has-player-won? board player) player))

