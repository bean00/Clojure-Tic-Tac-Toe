(ns clojure-tic-tac-toe.minimax
  (:require [clojure-tic-tac-toe.game_handler :as game_handler]
            [clojure-tic-tac-toe.win_checker :as win_checker]))

(defn- create-new-game-states
  [game-state moves]
  (map #(game_handler/add-move game-state %) moves))

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
  (let [player (game_handler/get-player game-state)]
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
  [game-state]
  (if (game_handler/finished? game-state)
    (let [score (game_handler/calculate-score game-state)]
      {:move :invalid-move, :score score})
    (let [moves (game_handler/get-available-moves game-state)
          new-game-states (create-new-game-states game-state moves)
          moves-and-scores (map #(minimax-move-and-score %)
                                new-game-states)
          scores (get-scores moves-and-scores)
          score (get-score-based-on-player scores game-state)
          move (get-move-based-on-score moves scores score)]
      {:move move, :score score})))

(defn minimax-move
  [game-state]
  (:move (minimax-move-and-score game-state)))
