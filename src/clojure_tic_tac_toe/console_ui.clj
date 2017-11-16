(ns clojure-tic-tac-toe.console_ui
  (:require [clojure-tic-tac-toe.game_handler :as game_handler]
            [clojure-tic-tac-toe.game_setup :as game_setup]
            [clojure-tic-tac-toe.input_output :as io]))

(defn- play-round
  [{:keys [board player] :as game-state}]
  (let [move (io/get-move board player)
        next-game-state (game_handler/create-next-game-state game-state move)]
    (io/display-board (game_handler/get-board next-game-state))
    next-game-state))

(defn- play-all-rounds []
  (loop [updated-game-state (play-round game_handler/starting-game-state)]
    (if (game_handler/finished? updated-game-state)
      updated-game-state
      (recur (play-round updated-game-state)))))

(defn play-game []
  (game_setup/set-up-game)
  (let [final-game-state (play-all-rounds)
        winner (game_handler/get-winner final-game-state)]
   (io/display-game-over-message winner)))

