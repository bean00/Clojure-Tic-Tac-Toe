(ns clojure-tic-tac-toe.core
  (:gen-class))

(defn board-to-string []
  (clojure.string/join "\n" [" 1 | 2 | 3 "
                             "---+---+---"
                             " 4 | 5 | 6 "
                             "---+---+---"
                             " 7 | 8 | 9 "]))

(defn -main
  "Tic Tac Toe program."
  [& args]
  (println "This is a Tic Tac Toe program.\n")
  (println "Example:")
  (println (board-to-string)))
