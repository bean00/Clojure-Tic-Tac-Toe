(ns clojure-tic-tac-toe.input_output_test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.board :as board]
            [clojure-tic-tac-toe.utilities :refer [join-lines]]
            [clojure-tic-tac-toe.input_output :refer :all]))

(deftest display-introduction-test
  (testing "when the player starts the program"
    (is (= "This is a Tic Tac Toe program.\n\n"
           (with-out-str
             (display-introduction)))
        "it displays an introduction")))

(deftest display-instructions-test
  (testing "when the player has seen the introduction"
    (is (= (join-lines ["To enter a move, type a number from 1-9."
                        "It will be added to the board based on"
                        "the following positions:\n\n"])
           (with-out-str
             (display-instructions)))
        "it displays the instructions")))

(deftest get-move-test
  (testing "when initially getting the move from the player"
    (is (= true
           (str/includes?
             (with-out-str
               (with-in-str "1"
                 (get-move board/empty-board :X)))
             "Player X, please enter"))
        "it displays the prompt"))
  (testing "when a valid move is entered"
    (with-out-str
      (is (= :2
             (with-in-str "2"
               (get-move board/empty-board :X)))
          "it returns the move as a keyword")))
  (testing "when an invalid move is entered, followed by a valid move"
    (let [output (with-out-str
                   (with-in-str "x\n1"
                     (get-move board/empty-board :O)))]
      (is (= true
             (str/includes? output "is invalid"))
          "it displays the correct error message")
      (is (= 2
             (count (re-seq #"enter your move" output))))
          "it displays the prompt twice"))
  (testing "when a move is entered that has already been taken"
    (is (= true
           (str/includes?
             (with-out-str
               (with-in-str "3\n1"
                 (get-move { :X #{:3} :O #{} } :O)))
             "already taken"))
        "it displays the correct error message")))

(deftest display-board-test
  (testing "when a board is passed in"
    (let [view (join-lines [" X | 2 | X "
                            "---+---+---"
                            " 4 | O | 6 "
                            "---+---+---"
                            " 7 | 8 | 9 "
                            ""])]
      (is (= view
             (with-out-str
               (display-board { :X #{:1 :3} :O #{:5} })))
          "it displays the board correctly"))))

