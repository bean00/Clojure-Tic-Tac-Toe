(ns clojure-tic-tac-toe.input_output_test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.input_output :refer :all]
            [clojure.string :as str]))

(deftest display-introduction-test
  (testing "when the player starts the program"
    (is (= "This is a Tic Tac Toe program.\n\n"
           (with-out-str
             (display-introduction)))
        "it displays an introduction")))

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
  (testing "when getting the move"
    (with-out-str
      (is (= :4
             (with-in-str "4"
               (get-move :O)))
          "it returns the move as a keyword"))))

(defn- create-view
  [separate-rows]
  (str/join "\n" separate-rows))

(deftest create-view-from-board-test
  (testing "when the board is empty"
    (let [view (create-view [" 1 | 2 | 3 "
                             "---+---+---"
                             " 4 | 5 | 6 "
                             "---+---+---"
                             " 7 | 8 | 9 "])]
      (is (= view
             (create-view-from-board { :X #{} :O #{} }))
          "it returns a board with all the positions numbered")))
  (testing "when X has moved to position 1"
    (let [view (create-view [" X | 2 | 3 "
                             "---+---+---"
                             " 4 | 5 | 6 "
                             "---+---+---"
                             " 7 | 8 | 9 "])]
      (is (= view
             (create-view-from-board { :X #{:1} :O #{} }))
          "it returns a board with X in the top left corner")))
  (testing "when X and O have each made 1 move"
    (let [view (create-view [" X | 2 | 3 "
                             "---+---+---"
                             " 4 | O | 6 "
                             "---+---+---"
                             " 7 | 8 | 9 "])]
      (is (= view
             (create-view-from-board { :X #{:1} :O #{:5} }))
          "it returns a board with both moves"))))

(deftest display-board-test
  (testing "when a board is passed in"
    (let [view (create-view [" X | 2 | X "
                             "---+---+---"
                             " 4 | O | 6 "
                             "---+---+---"
                             " 7 | 8 | 9 "
                             ""])]
      (is (= view
             (with-out-str
               (display-board { :X #{:1 :3} :O #{:5} })))
          "it displays the board correctly"))))

(deftest display-example-board-test
  (testing "when the player has seen the introduction"
    (let [view (create-view ["Example board:"
                             " 1 | 2 | 3 "
                             "---+---+---"
                             " 4 | 5 | 6 "
                             "---+---+---"
                             " 7 | 8 | 9 "
                             ""])]
      (is (= view
             (with-out-str
               (display-example-board)))
          "it displays the example board with a description"))))

