(ns clojure-tic-tac-toe.input_output
  (:require [clojure.string :as str]
            [clojure-tic-tac-toe.board :as board]
            [clojure-tic-tac-toe.utilities :refer [join-lines]]))

(defn display-introduction []
  (println "This is a Tic Tac Toe program.\n"))

(defn display-game-mode-instructions []
  (println (join-lines ["Please enter one of the following:"
                        "- \"h\" to play another person\n"])))

(defn display-angle-bracket []
  (print "> ")
  (flush))

(defn- get-input []
  (keyword (str/trim (read-line))))

(defn get-game-mode []
  (get-input))

(defn- display-playing-person-message []
  (println (join-lines ["\nOk, you chose to play another person."
                        ""
                        "You are Player X, and you will go first."])))

(defn display-result-of-game-mode-choice
  [game-mode]
  (display-playing-person-message))

(defn display-game-instructions []
  (println (join-lines ["\nTo enter a move, type a number from 1-9."
                        "It will be added to the board based on"
                        "the following positions:\n"])))


(defn- prompt-player-for-move
  [player]
  (printf "\nPlayer %s, please enter your move: ", (name player)))

(defn- display-invalid-move-message
  [move]
  (printf "\n<!> Error: Move \"%s\" is invalid. Must be from 1-9.", (name move)))

(defn- display-move-taken-message
  [move]
  (printf
    "\n<!> Error: Move \"%s\" was already taken. Must move to an open position.",
    (name move)))

(defn get-move
  [board player]
  (loop []
    (prompt-player-for-move player)
    (flush)
    (let [move (get-input)]
      (cond
        (board/is-move-invalid? move)
          (do
            (display-invalid-move-message move)
            (recur))
        (board/has-move-been-taken? board move)
          (do
            (display-move-taken-message move)
            (recur))
        :else move))))


(def tokens {:1 "1", :2 "2", :3 "3"
             :4 "4", :5 "5", :6 "6"
             :7 "7", :8 "8", :9 "9"
             :X "X", :O "O"})

(defn- create-row
  [board token-key-1 token-key-2 token-key-3]
  (format " %s | %s | %s ",
          (tokens (board/token-at board token-key-1)),
          (tokens (board/token-at board token-key-2)),
          (tokens (board/token-at board token-key-3))))

(defn- create-view-from-board
  [board]
  (join-lines [(create-row board :1 :2 :3)
               "---+---+---"
               (create-row board :4 :5 :6)
               "---+---+---"
               (create-row board :7 :8 :9)]))

(defn display-board
  [board]
  (println (create-view-from-board board)))

(defn display-game-over-message
  [winner]
  (if (contains? #{:X :O} winner)
    (println (format "\nGame over. Player %s won." (name winner)))
    (println "\nGame over. Ended in a tie.")))

