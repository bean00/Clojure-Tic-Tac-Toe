(ns clojure-tic-tac-toe.core
  (:require [clojure-tic-tac-toe.console_ui.console_ui :as console_ui])
  (:gen-class))

(defn -main
  "Tic Tac Toe program."
  [& args]
  (console_ui/play-game))

