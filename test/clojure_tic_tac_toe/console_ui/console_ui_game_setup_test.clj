(ns clojure-tic-tac-toe.console_ui.console_ui_game_setup_test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.console_ui.console_ui_computer_move :as ui_comp_move]
            [clojure-tic-tac-toe.console_ui.console_ui_human_move :as ui_human_move]
            [clojure-tic-tac-toe.console_ui.console_ui_game_setup :refer :all]))

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
        "it displays the example board")
    (with-out-str
      (is (= :human
             (with-in-str "h\n"
               (set-up-game)))
          "it returns the internal keyword for playing a person"))))

(deftest decide-strategies-test
  (testing "when the game is Human vs. Human"
    (is (= {:X ui_human_move/get-human-move, :O ui_human_move/get-human-move}
           (decide-strategies :human))
        "it returns the strategies for 2 human players"))
  (testing "when the game is Human vs. Computer"
    (is (= {:X ui_human_move/get-human-move, :O ui_comp_move/have-computer-move}
           (decide-strategies :computer))
        "it returns strategies for a human and a computer")))

