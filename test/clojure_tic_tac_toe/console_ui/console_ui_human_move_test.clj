(ns clojure-tic-tac-toe.console_ui.console_ui_human_move_test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.board :as board]
            [clojure-tic-tac-toe.console_ui.console_ui_human_move :refer :all]))

(def x-on-empty-game-state
  {:board board/empty-board, :player :X})

(deftest get-human-move-test
  (testing "when initially getting the move from the player"
    (is (= true
           (str/includes?
             (with-out-str
               (with-in-str "1"
                 (get-human-move x-on-empty-game-state)))
             "Player X, please enter"))
        "it displays the prompt"))
  (testing "when a valid move is entered"
    (with-out-str
      (is (= :2
             (with-in-str "2"
               (get-human-move x-on-empty-game-state)))
          "it returns the move as a keyword")))
  (testing "when an invalid move is entered, followed by a valid move"
    (let [output (with-out-str
                   (with-in-str "x\n1"
                     (get-human-move x-on-empty-game-state)))]
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
                 (get-human-move {:board {:X #{:3}, :O #{}}, :player :O})))
             "already taken"))
        "it displays the correct error message")))

