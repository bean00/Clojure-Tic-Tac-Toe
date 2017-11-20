(ns clojure-tic-tac-toe.console_ui.console_ui
  (:require [clojure-tic-tac-toe.game_handler :as game_handler]
            [clojure-tic-tac-toe.console_ui.console_ui_game_setup :as ui_game_setup]))

(defn- play-round
  [game-state]
  (let [get-move (game_handler/get-move-strategy game-state)
        move (get-move game-state)
        next-game-state (game_handler/create-next-game-state game-state move)]
    (game_handler/display-board next-game-state)
    next-game-state))

(defn- play-all-rounds
  [starting-game-state]
  (loop [updated-game-state (play-round starting-game-state)]
    (if (game_handler/finished? updated-game-state)
      updated-game-state
      (recur (play-round updated-game-state)))))

(defn play-game []
  (let [game-mode (ui_game_setup/set-up-game)
        move-strategies (ui_game_setup/decide-strategies game-mode)
        starting-game-state (game_handler/create-game-state move-strategies)
        final-game-state (play-all-rounds starting-game-state)
        winner (game_handler/get-winner final-game-state)]
   (game_handler/display-game-over-message winner)))

