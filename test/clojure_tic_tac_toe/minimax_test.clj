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

(deftest minimax-move-and-score-test
  (testing "when Player X already won"
    (let [score-and-move (minimax-move-and-score
                           {:board {:X #{:1 :2 :3}, :O #{:4 :5}},
                            :player :O, :finished? true}
                           initial-data)]
      (is (= 1
             (:score score-and-move))
          "it returns the score for X winning")
      (is (= :invalid-move
             (:move score-and-move))
          "it returns an invalid move")))
  (testing "when Player O won"
    (is (= -1
           (:score (minimax-move-and-score
                     {:board {:X #{:4 :7 :8}, :O #{:1 :2 :3}},
                      :player :X, :finished? true}
                     initial-data)))
        "it returns the score for O winning"))
  (testing "when the game resulted in a draw"
    (is (= 0
           (:score (minimax-move-and-score
                     {:board {:X #{:1 :5 :6 :3 :8}, :O #{:2 :9 :4 :7}},
                      :player :O, :finished? true}
                     initial-data)))
        "it returns the draw score"))
  (testing "when Player X can win now (1 move left)"
    (let [score-and-move (minimax-move-and-score
                           {:board {:X #{:1 :2 :8 :6}, :O #{:4 :5 :7 :9}},
                            :player :X, :finished? false}
                           initial-data)]
      (is (= 1
             (:score score-and-move))
          "it returns the score for X winning")
      (is (= :3
             (:move score-and-move))
          "it returns the move to win")))
  (testing "when Player O can win now (2 moves left)"
    (let [score-and-move (minimax-move-and-score
                           {:board {:X #{:1 :2 :7 :9}, :O #{:4 :5 :8}},
                            :player :O, :finished? false}
                           initial-data)]
      (is (= -1
             (:score score-and-move))
          "it returns the score for O winning")
      (is (= :6
             (:move score-and-move))
          "it returns the move to win")))
  (testing "when Player O cannot win (2 moves left)"
    (is (= :9
           (:move (minimax-move-and-score
                    {:board {:X #{:1 :7 :6 :8}, :O #{:4 :5 :2}},
                     :player :O, :finished? false}
                    initial-data)))
        "it returns the move to force a draw"))
  (testing "when Player O determines that it cannot win"
    (is (= :4
           (:move (minimax-move-and-score
                    {:board {:X #{:1 :7}, :O #{:5}},
                     :player :O, :finished? false}
                    initial-data)))
        "it returns the move to eventually draw")))

(deftest minimax-move-test
  (testing "when Player O can win now (2 moves left)"
    (is (= :6
           (minimax-move
             {:board {:X #{:1 :2 :7 :9}, :O #{:4 :5 :8}},
              :player :O, :finished? false}
             initial-data))
        "it returns the move to win")))

