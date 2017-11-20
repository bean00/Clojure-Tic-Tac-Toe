(ns clojure-tic-tac-toe.console_ui.console_ui_game_mode
  (:require [clojure-tic-tac-toe.game_handler :as game_handler]
            [clojure-tic-tac-toe.console_ui.input_output :as io]))

(defn get-valid-game-mode []
  (loop [mode (io/get-initial-input)]
    (if (game_handler/is-game-mode-invalid? mode)
      (recur (io/get-game-mode mode))
      mode)))

