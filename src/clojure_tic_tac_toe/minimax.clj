(ns clojure-tic-tac-toe.minimax
  (:require [clojure-tic-tac-toe.game_handler :as game_handler]
            [clojure-tic-tac-toe.win_checker :as win_checker]))

(defn- create-new-game-states
  [game-state initial-data moves]
  (map #(game_handler/add-move game-state initial-data %) moves))

(defn- get-scores
  [scores-and-moves]
  (map #(:score %) scores-and-moves))


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

(defn minimax-move-and-score
  [game-state {:keys [winning-moves] :as initial-data}]
  (if (:finished? game-state)
    (let [score (game_handler/calculate-score game-state winning-moves)]
      {:move :invalid-move, :score score})
    (let [valid-moves (:moves initial-data)
          moves (game_handler/get-available-moves game-state valid-moves)
          new-game-states (create-new-game-states game-state initial-data moves)
          moves-and-scores (map #(minimax-move-and-score % initial-data)
                                new-game-states)
          scores (get-scores moves-and-scores)
          score (get-score-based-on-player scores game-state)
          move (get-move-based-on-score moves scores score)]
      {:move move, :score score})))

(defn minimax-move
  [game-state initial-data]
  (:move (minimax-move-and-score game-state initial-data)))

