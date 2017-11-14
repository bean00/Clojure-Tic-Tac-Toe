(ns clojure-tic-tac-toe.console_ui_test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.console_ui :refer :all]))

(defn- play-game-output []
  (with-out-str
    (with-in-str "h\n1\n2\n5\n9\n6\n4\n3\n7\n8\n"
      (play-game))))

(deftest play-game-test
  (testing "when a game is played that ends in a tie (H vs. H)"
    (is (= true
           (str/includes?
             (play-game-output)
             "chose to play another person"))
        "it sets up the game")
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
  (testing "when a player wins (H vs. H)"
    (is (= true
           (str/includes?
             (with-out-str
               (with-in-str "h\n1\n4\n2\n5\n3\n"
                 (play-game)))
             "won"))
        "it displays that the player won")))

