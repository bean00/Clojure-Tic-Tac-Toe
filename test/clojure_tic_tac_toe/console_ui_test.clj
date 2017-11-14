(ns clojure-tic-tac-toe.console_ui_test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.console_ui :refer :all]))

(defn- play-game-output []
  (with-out-str
    (with-in-str "1\n2\n5\n9\n6\n4\n3\n7\n8\n"
      (play-game))))

(deftest play-game-test
  (testing "when a game is played that ends in a tie"
    (is (= true
           (str/includes?
             (play-game-output)
             "Tic Tac Toe"))
        "it displays the introduction")
    (is (= true
           (str/includes?
             (play-game-output)
             "type a number"))
        "it displays the instructions")
    (is (= true
           (str/includes?
             (play-game-output)
             " 4 | 5 | 6 "))
        "it displays the example board")
    (is (= true
           (str/includes?
             (play-game-output)
             " X | O | X "))
        "it displays the board with the moves made")
    (is (= true
           (str/includes?
             (play-game-output)
             "tie"))
        "it displays that the game ended in a tie"))
  (testing "when a player wins"
    (is (= true
           (str/includes?
             (with-out-str
               (with-in-str "1\n4\n2\n5\n3\n"
                 (play-game)))
             "won"))
        "it displays that the player won")))

