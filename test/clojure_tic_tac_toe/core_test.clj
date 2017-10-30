(ns clojure-tic-tac-toe.core_test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.core :refer :all]
            [clojure.string :as str]))

(defn- what-play-round-displays []
  (with-out-str
    (with-in-str "3"
      (play-round { :X #{} :O #{} } :X))))

(deftest play-round-test
  (testing "when 1 round is played"
    (is (= true
           (str/includes?
             (what-play-round-displays)
             "Player X"))
        "it displays the player prompt")
    (is (= true
           (str/includes?
             (what-play-round-displays)
             " 1 | 2 | X "))
        "it displays a row of the board")))

(defn- what-play-game-displays []
  (with-out-str
    (with-in-str "3"
      (play-game))))

(deftest play-game-test
  (testing "when the game is played"
    (is (= true
           (str/includes?
             (what-play-game-displays)
             "Tic Tac Toe"))
        "it displays the introduction")
    (is (= true
           (str/includes?
             (what-play-game-displays)
             "type a number"))
        "it displays the instructions")
    (is (= true
           (str/includes?
             (what-play-game-displays)
             " 4 | 5 | 6 "))
        "it display a row of the example board")
    (is (= true
           (str/includes?
             (what-play-game-displays)
             "enter a move"))
        "it displays output for playing a round")))

