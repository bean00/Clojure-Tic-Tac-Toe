(ns clojure-tic-tac-toe.console_ui_test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.console_ui :refer :all]))

(defn- play-game-output []
  (with-out-str
    (with-in-str "1\n2\n3\n4\n5\n6\n7\n8\n9\n"
      (play-game))))

(deftest play-game-test
  (testing "when a full game is played"
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
             "over"))
        "it displays a final message")))

