(ns clojure-tic-tac-toe.view
  (:require [clojure-tic-tac-toe.game_handler :as game_handler]
            [clojure-tic-tac-toe.utilities :refer [join-lines]]))

(def empty-board game_handler/empty-board)

(def tokens {:1 "1", :2 "2", :3 "3"
             :4 "4", :5 "5", :6 "6"
             :7 "7", :8 "8", :9 "9"
             :X "X", :O "O"})

(defn- create-row
  [game-state token-key-1 token-key-2 token-key-3]
  (format " %s | %s | %s ",
          ((game_handler/token-at game-state token-key-1) tokens),
          ((game_handler/token-at game-state token-key-2) tokens),
          ((game_handler/token-at game-state token-key-3) tokens)))

(defn create-view
  [game-state]
  (join-lines [(create-row game-state :1 :2 :3)
               "---+---+---"
               (create-row game-state :4 :5 :6)
               "---+---+---"
               (create-row game-state :7 :8 :9)]))

