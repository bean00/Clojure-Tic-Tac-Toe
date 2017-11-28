(ns clojure-tic-tac-toe.console_ui.input_output_test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.board :as board]
            [clojure-tic-tac-toe.utilities :refer [join-lines]]
            [clojure-tic-tac-toe.console_ui.input_output :refer :all]))

(deftest display-introduction-test
  (testing "when the player starts the program"
    (is (= "This is a Tic Tac Toe program.\n\n"
           (with-out-str
             (display-introduction)))
        "it displays an introduction")))

(deftest display-game-mode-instructions-test
  (testing "when the player has seen the introduction"
    (is (= (join-lines ["Please enter one of the following:"
                        "- \"h\" to play another person"
                        "- \"c\" to play against a computer\n\n"])
           (with-out-str
             (display-game-mode-instructions)))
        "it displays the instructions for choosing the game mode")))

(deftest get-initial-input-test
  (testing "when getting the initial input from the user"
    (is (= "> "
           (with-out-str
             (with-in-str "h\n"
               (get-initial-input))))
        "it displays a simple prompt")
    (with-out-str
      (is (= :h
             (with-in-str "h\n"
               (get-initial-input)))
          "it returns the input as a keyword"))))

(deftest get-game-mode-test
  (testing "when getting the game mode"
    (is (= true
           (str/includes?
             (with-out-str
               (with-in-str "h\n"
                 (get-game-mode :h)))
             "is invalid"))
        "it displays the invalid game mode message")
    (is (= true
           (str/includes?
             (with-out-str
               (with-in-str "h\n"
                 (get-game-mode :h)))
             "enter the game mode"))
        "it prompts the player for the game mode")
    (with-out-str
      (is (= :c
             (with-in-str "c\n"
               (get-game-mode :h)))
          "it returns the input as a keyword"))))

(deftest display-result-of-game-mode-choice-test
  (testing "when the player chooses to play another person"
    (is (= true
           (str/includes?
             (with-out-str
               (display-result-of-game-mode-choice :human))
             "play another person"))
        "it displays the 'playing person' message"))
  (testing "when the player chooses to play a computer"
    (is (= true
           (str/includes?
             (with-out-str
               (display-result-of-game-mode-choice :computer))
             "play a computer"))
        "it displays the 'playing computer' message")))

(deftest display-game-instructions-test
  (testing "when the player has seen the introduction"
    (is (= (join-lines ["\nTo enter a move, type a number from 1-9."
                        "It will be added to the board based on"
                        "the following positions:\n\n"])
           (with-out-str
             (display-game-instructions)))
        "it displays the instructions")))

(deftest get-move-test
  (testing "when getting the move from the user"
    (is (= true
           (str/includes?
             (with-out-str
               (with-in-str "3\n"
                 (get-move :X)))
             "enter your move"))
        "it displays the move prompt")
    (with-out-str
      (is (= :3
             (with-in-str "3\n"
               (get-move :X)))
          "it returns the input as a keyword"))))

(deftest get-move-if-move-is-invalid-test
  (testing "when getting a move after an invalid move was entered"
    (is (= true
           (str/includes?
             (with-out-str
               (with-in-str "5\n"
                 (get-move-if-move-is-invalid :3 :X)))
             "is invalid. Must be from"))
        "it displays the 'invalid move' message")
    (is (= true
           (str/includes?
             (with-out-str
               (with-in-str "5\n"
                 (get-move-if-move-is-invalid :3 :X)))
             "enter your move"))
        "it calls 'get-move'")))

(deftest get-move-if-move-was-taken-test
  (testing "when getting a move after the previous move was taken"
    (is (= true
           (str/includes?
             (with-out-str
               (with-in-str "5\n"
                 (get-move-if-move-was-taken :3 :X)))
             "was already taken. Must move"))
        "it displays the 'move taken' message")
    (is (= true
           (str/includes?
             (with-out-str
               (with-in-str "5\n"
                 (get-move-if-move-was-taken :3 :X)))
             "enter your move"))
        "it calls 'get-move'")))

(deftest display-computer-move-message-test
  (testing "when the computer moves"
    (is (= "\nThe computer moved.\n"
           (with-out-str
             (display-computer-move-message)))
        "it displays a message indicating the computer moved")))

(deftest display-board-test
  (testing "when a game state is passed in"
    (let [view (join-lines [" X | 2 | X "
                            "---+---+---"
                            " 4 | O | 6 "
                            "---+---+---"
                            " 7 | 8 | 9 "
                            ""])]
      (is (= view
             (with-out-str
               (display-board {:board {:X #{:1 :3}, :O #{:5}}})))
          "it displays the board correctly"))))

(deftest display-example-board-test
  (testing "when displaying an example board"
    (let [view (join-lines [" 1 | 2 | 3 "
                            "---+---+---"
                            " 4 | 5 | 6 "
                            "---+---+---"
                            " 7 | 8 | 9 "
                            ""])]
      (is (= view
             (with-out-str
               (display-example-board)))
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

