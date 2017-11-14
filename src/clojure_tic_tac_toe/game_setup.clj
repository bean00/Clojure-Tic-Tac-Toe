(ns clojure-tic-tac-toe.game_setup
  (:require [clojure-tic-tac-toe.board :as board]
            [clojure-tic-tac-toe.input_output :as io]))

(defn set-up-game []
  (io/display-introduction)
  (io/display-game-mode-instructions)
  (io/display-angle-bracket)
  (let [game-mode (io/get-game-mode)]
    (io/display-result-of-game-mode-choice game-mode)
    (io/display-game-instructions)
    (io/display-board board/empty-board)))

