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

(def minimax
  (memoize
    (fn [game-state {:keys [winning-moves valid-moves] :as initial-data}]
      (if (:finished? game-state)
        (let [score (game_handler/calculate-score game-state winning-moves)]
          {:score score, :move :invalid-move, :total-moves 5})
        (let [moves (game_handler/get-available-moves game-state valid-moves)
              new-game-states (create-new-game-states game-state initial-data moves)
              score-info-list (map #(minimax % initial-data)
                                   new-game-states)
              scores (get-scores score-info-list)
              score (get-score-based-on-player scores game-state)
              move (get-move-based-on-score moves scores score)]
          {:score score, :move move})))))

(defn minimax-move
  [game-state initial-data]
  (:move (minimax game-state initial-data)))

