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

(deftest display-game-mode-instructions-test
  (testing "when the player has seen the introduction"
    (is (= (join-lines ["Please enter one of the following:"
                        "- \"h\" to play another person\n\n"])
           (with-out-str
             (display-game-mode-instructions)))
        "it displays the instructions for choosing the game mode")))

(deftest display-angle-bracket-test
  (testing "when waiting for input from the user"
    (is (= "> "
           (with-out-str
             (display-angle-bracket)))
        "it displays the angle bracket")))

(deftest get-game-mode-test
  (testing "when a valid game mode is entered"
    (is (= :h
           (with-in-str "h\n"
             (get-game-mode)))
        "it returns the game mode as a keyword")))

(deftest display-result-of-game-mode-choice-test
  (testing "when the player chooses to play another person"
    (is (= true
           (str/includes?
             (with-out-str
               (display-result-of-game-mode-choice :h))
             "play another person"))
        "it displays the 'playing person' message")))

(deftest display-game-instructions-test
  (testing "when the player has seen the introduction"
    (is (= (join-lines ["\nTo enter a move, type a number from 1-9."
                        "It will be added to the board based on"
                        "the following positions:\n\n"])
           (with-out-str
             (display-game-instructions)))
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

(deftest display-game-over-message-test
  (testing "when the game ended in a tie"
    (is (= "\nGame over. Ended in a tie.\n"
           (with-out-str
             (display-game-over-message false)))
        "it displays the tie message"))
  (testing "when Player X won"
    (is (= "\nGame over. Player X won.\n"
           (with-out-str
             (display-game-over-message :X)))
        "it displays a message saying X won"))
  (testing "when Player O won"
    (is (= "\nGame over. Player O won.\n"
           (with-out-str
             (display-game-over-message :O)))
        "it displays a message saying O won")))

