(ns clojure-tic-tac-toe.minimax_test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.test_helper :as test_helper]
            [clojure-tic-tac-toe.minimax :refer :all]))

(def valid-moves
  test_helper/valid-moves)

(def winning-moves
  test_helper/winning-moves)

(def initial-data
  {:valid-moves valid-moves
   :winning-moves winning-moves})

(deftest create-score-info-list-test
  (testing "when the scores, moves, and total moves are passed in"
    (is (= '({:score 1, :move :2, :total-moves 3}
             {:score 1, :move :3, :total-moves 3}
             {:score 2, :move :4, :total-moves 3})
           (create-score-info-list '(1 1 2) '(:2 :3 :4) '(3 3 3)))
        "it returns a list of maps")))

(deftest get-maps-with-target-score-test
  (testing "when 3 maps are passed in"
    (is (= '({:score 1, :move :2, :total-moves 3}
             {:score 1, :move :3, :total-moves 3})
           (get-maps-with-target-score '({:score 1, :move :2, :total-moves 3}
                                         {:score 1, :move :3, :total-moves 3}
                                         {:score 2, :move :4, :total-moves 3})
                                       1))
        "it returns a list with the maps that have the target score")))

(deftest sort-maps-by-total-moves-test
  (testing "when 3 maps are passed in"
    (is (= '({:score 1, :move :2, :total-moves 3}
             {:score 1, :move :3, :total-moves 4}
             {:score 1, :move :4, :total-moves 5})
           (sort-maps-by-total-moves '({:score 1, :move :2, :total-moves 3}
                                       {:score 1, :move :4, :total-moves 5}
                                       {:score 1, :move :3, :total-moves 4})))
        "it sorts them by total moves")))

(deftest get-optimal-move-test
  (testing "when the score info list only contains 1 map"
    (is (= :2
           (get-optimal-move '({:score 1, :move :2, :total-moves 3}) 1))
        "it returns the only move"))
  (testing "when the score info list contains 2 maps with different scores"
    (is (= :4
           (get-optimal-move '({:score 1, :move :3, :total-moves 3}
                               {:score 2, :move :4, :total-moves 3})
                               2))
        "it returns the move corresponding to the target score")))

(deftest minimax-test
  (testing "when Player X already won"
    (let [score-info (minimax
                           {:board {:X #{:1 :2 :3}, :O #{:4 :5}},
                            :player :O, :finished? true}
                           initial-data)]
      (is (= 1
             (:score score-info))
          "it returns the score for X winning")
      (is (= :invalid-move
             (:move score-info))
          "it returns an invalid move")
      (is (= 5
             (:total-moves score-info))
          "it returns the total number of moves made")))
  (testing "when Player O won"
    (is (= -1
           (:score (minimax
                     {:board {:X #{:4 :7 :8}, :O #{:1 :2 :3}},
                      :player :X, :finished? true}
                     initial-data)))
        "it returns the score for O winning"))
  (testing "when the game resulted in a draw"
    (is (= 0
           (:score (minimax
                     {:board {:X #{:1 :5 :6 :3 :8}, :O #{:2 :9 :4 :7}},
                      :player :O, :finished? true}
                     initial-data)))
        "it returns the draw score"))
  (testing "when Player X can win now (1 move left)"
    (let [score-info (minimax
                           {:board {:X #{:1 :2 :8 :6}, :O #{:4 :5 :7 :9}},
                            :player :X, :finished? false}
                           initial-data)]
      (is (= 1
             (:score score-info))
          "it returns the score for X winning")
      (is (= :3
             (:move score-info))
          "it returns the move to win")
      (is (= 9
             (:total-moves score-info))
          "it returns 8 for the total number of moves")))
  (testing "when Player O can win now (2 moves left)"
    (let [score-info (minimax
                           {:board {:X #{:1 :2 :7 :9}, :O #{:4 :5 :8}},
                            :player :O, :finished? false}
                           initial-data)]
      (is (= -1
             (:score score-info))
          "it returns the score for O winning")
      (is (= :6
             (:move score-info))
          "it returns the move to win")))
  (testing "when Player O cannot win (2 moves left)"
    (is (= :9
           (:move (minimax
                    {:board {:X #{:1 :7 :6 :8}, :O #{:4 :5 :2}},
                     :player :O, :finished? false}
                    initial-data)))
        "it returns the move to force a draw"))
  (testing "when Player O determines that it cannot win"
    (is (= :4
           (:move (minimax
                    {:board {:X #{:1 :7}, :O #{:5}},
                     :player :O, :finished? false}
                    initial-data)))
        "it returns the move to eventually draw"))
  (testing "when Player O can win now (3 moves left)"
    (is (= :7
           (:move (minimax
                    {:board {:X #{:1 :9 :2}, :O #{:6 :3 :5}},
                     :player :O, :finished? false}
                    initial-data)))
        "it returns the move to win")))

(deftest minimax-move-test
  (testing "when Player O can win now (2 moves left)"
    (is (= :6
           (minimax-move
             {:board {:X #{:1 :2 :7 :9}, :O #{:4 :5 :8}},
              :player :O, :finished? false}
             initial-data))
        "it returns the move to win")))

