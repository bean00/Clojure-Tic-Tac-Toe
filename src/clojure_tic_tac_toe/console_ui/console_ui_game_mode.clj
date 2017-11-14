(ns clojure-tic-tac-toe.console_ui.console_ui_game_mode
  (:require [clojure-tic-tac-toe.console_ui.input_output :as io]))

(def valid-game-modes #{:h :c})

(defn- is-game-mode-invalid?
  [game-mode]
  (not (contains? valid-game-modes game-mode)))

(defn get-valid-game-mode []
  (loop [mode (io/get-initial-input)]
    (if (is-game-mode-invalid? mode)
      (recur (io/get-game-mode mode))
      mode)))

