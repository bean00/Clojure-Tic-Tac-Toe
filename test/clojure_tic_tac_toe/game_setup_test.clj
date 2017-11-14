(ns clojure-tic-tac-toe.game_setup_test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.game_setup :refer :all]))

(defn- set-up-game-output []
  (with-out-str
    (with-in-str "h\n"
      (set-up-game))))

(deftest set-up-game-test
  (testing "when the player chooses to play another person"
    (is (= true
           (str/includes?
             (set-up-game-output)
             "Tic Tac Toe"))
        "it displays the introduction")
    (is (= true
           (str/includes?
             (set-up-game-output)
             "\"h\" to play"))
        "it displays the game mode instructions")
    (is (= true
           (str/includes?
             (set-up-game-output)
             ">"))
        "it displays the angle bracket")
    (is (= true
           (str/includes?
             (set-up-game-output)
             "chose to play another person"))
        "it displays the 'playing person' message")
    (is (= true
           (str/includes?
             (set-up-game-output)
             "type a number"))
        "it displays the instructions")
    (is (= true
           (str/includes?
             (set-up-game-output)
             " 4 | 5 | 6 "))
        "it displays the example board")))

