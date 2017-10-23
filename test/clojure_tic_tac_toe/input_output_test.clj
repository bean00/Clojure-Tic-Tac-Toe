(ns clojure-tic-tac-toe.input_output_test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.input_output :refer :all]))

(deftest prompt-player-for-move-test
  (testing "when the player is prompted for a move"
    (is (= "\nPlayer X, please enter a move from 1-9: "
           (with-out-str 
             (prompt-player-for-move :X)))
        "it prints out the prompt for Player X")
    (is (= "\nPlayer O, please enter a move from 1-9: "
           (with-out-str
             (prompt-player-for-move :O)))
        "it prints out the prompt for Player O")))

(deftest get-move-test
  (testing "when getting a move"
    (is (= 3
           (with-in-str "3"
             (get-move)))
        "it returns the move")))

(deftest create-board-view-test
  (testing "when the board is empty"
    (let [separate-rows [" 1 | 2 | 3 "
                         "---+---+---"
                         " 4 | 5 | 6 "
                         "---+---+---"
                         " 7 | 8 | 9 "]
          view (clojure.string/join "\n" separate-rows)]
         (is (= view
                (create-board-view { :X #{} :O #{} }))
             "it returns a board with all the positions numbered")))
  (testing "when X has moved to position 1"
    (let [separate-rows [" X | 2 | 3 "
                         "---+---+---"
                         " 4 | 5 | 6 "
                         "---+---+---"
                         " 7 | 8 | 9 "]
          view (clojure.string/join "\n" separate-rows)]
         (is (= view
                (create-board-view { :X #{:1} :O #{} }))
             "it returns a board with X in the top left corner")))
  (testing "when X and O have each made 1 move"
    (let [separate-rows [" X | 2 | 3 "
                         "---+---+---"
                         " 4 | O | 6 "
                         "---+---+---"
                         " 7 | 8 | 9 "]
          view (clojure.string/join "\n" separate-rows)]
         (is (= view
                (create-board-view { :X #{:1} :O #{:5} }))
             "it returns a board with both moves"))))

