(ns clojure-tic-tac-toe.core_test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.core :refer :all]
            [clojure.string :as str]))

(defn- play-round-once-output []
  (with-out-str
    (with-in-str "3"
      (play-round { :X #{} :O #{} } :X))))

(deftest play-round-test
  (testing "when 1 round is played"
    (is (= true
           (str/includes?
             (play-round-once-output)
             "Player X"))
        "it displays the prompt")
    (is (= true
           (str/includes?
             (play-round-once-output)
             " 1 | 2 | X "))
        "it displays the new board with the move")
    (with-out-str
      (is (= { :X #{:2} :O #{} }
             (with-in-str "2"
               (play-round { :X #{} :O #{} } :X))))
          "it returns the board with the move added")))

(defn- play-game-output []
  (with-out-str
    (with-in-str "4\n5"
      (play-game))))

(deftest play-game-test
  (testing "when the game is played"
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
             " X | O | 6 "))
        "it displays the board with the moves made")))

