(ns clojure-tic-tac-toe.minimax
  (:require [clojure-tic-tac-toe.game_handler :as game_handler]
            [clojure-tic-tac-toe.win_checker :as win_checker]))

(defn- create-new-game-states
  [game-state initial-data moves]
  (map #(game_handler/add-move game-state initial-data %) moves))

(defn- get-scores
  [score-info-list]
  (map #(:score %) score-info-list))


(defn- max-or-min-based-on-player
  [player]
  (cond
    (= player :X) max
    (= player :O) min))

(defn- get-score-based-on-player
  [scores game-state]
  (let [player (:player game-state)]
    (apply (max-or-min-based-on-player player) scores)))


(defn- create-map-of-moves-and-scores
  [moves scores]
  (zipmap moves scores))

(defn- get-pairs-with-target-score
  [moves-and-scores target-score]
  (filter #(-> % val (= target-score)) moves-and-scores))

(defn- get-move-from-pairs
  [pairs]
  (first (first pairs)))

(defn- get-move-based-on-score
  [moves scores target-score]
  ((comp get-move-from-pairs
         #(get-pairs-with-target-score % target-score)
         create-map-of-moves-and-scores)
   moves scores))


(defn create-score-info-list
  [scores moves total-moves]
  (map #(assoc {} :score %1 :move %2 :total-moves %3)
       scores moves total-moves))

(defn get-maps-with-target-score
  [score-info-list score]
  (filter #(= score (:score %)) score-info-list))

(defn sort-maps-by-total-moves
  [maps]
  (sort-by :total-moves (vec maps)))

(defn get-optimal-move
  [score-info-list score]
  (let [maps-with-target-score (get-maps-with-target-score score-info-list score)
        sorted-maps (sort-maps-by-total-moves maps-with-target-score)
        move (:move (first sorted-maps))] ; make a helper function for this
    move))

(def minimax
  (memoize
    (fn [game-state {:keys [winning-moves valid-moves] :as initial-data}]
      (if (:finished? game-state)
        (let [score (game_handler/calculate-score game-state winning-moves)
              total-moves (game_handler/get-total-moves game-state)]
          {:score score, :move :invalid-move, :total-moves total-moves})
        (let [moves (game_handler/get-available-moves game-state valid-moves)
              new-game-states (create-new-game-states game-state initial-data moves)
              raw-score-info-list (map #(minimax % initial-data)
                                       new-game-states)
              scores (get-scores raw-score-info-list)
              totals (map #(:total-moves %) raw-score-info-list)
              score (get-score-based-on-player scores game-state)
              score-info-list (create-score-info-list scores moves totals)
              move (get-optimal-move score-info-list score)
              ;move (get-move-based-on-score moves scores score)
              total-moves (first totals)] ; use total for selected move?
          {:score score, :move move, :total-moves total-moves})))))

(defn minimax-move
  [game-state initial-data]
  (:move (minimax game-state initial-data)))

