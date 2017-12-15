(ns clojure-tic-tac-toe.computer_move_test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.computer_move :refer :all]
            [clojure-tic-tac-toe.test_helper :as test_helper]))

(def valid-moves
  test_helper/valid-moves)

(def initial-data
  {:valid-moves valid-moves})

(deftest make-random-move-test
  (testing "when the computer has 1 move left"
    (is (= :9
           (make-random-move {:board {:X #{:1 :2 :3 :4}, :O #{:5 :6 :7 :8}}}
                             valid-moves))
        "it returns that move"))
  (testing "when the computer has 5 moves left"
    (let [move (make-random-move {:board {:X #{:6 :7}, :O #{:8 :9}}}
                                 valid-moves)]
      (is (= true
             (contains? #{:1 :2 :3 :4 :5} move))
          "it returns one of those moves")))
  (testing "when there are no moves left"
    (is (= nil
           (make-random-move {:board {:X #{:1 :2 :3 :4 :5}, :O #{:6 :7 :8 :9}}}
                             valid-moves))
        "it returns nil")))

(deftest make-minimax-move-test
  (testing "when the computer can win now (2 moves left)"
    (is (= :6
           (make-minimax-move
             {:board {:X #{:1 :2 :7 :9}, :O #{:4 :5 :8}}, :player :O,
              :finished? false}
             initial-data))
        "it returns the move to win"))
  (testing "when there are no moves left"
    (is (= :invalid-move
           (make-minimax-move
             {:board {:X #{:1 :5 :6 :3 :8}, :O #{:2 :9 :4 :7}}, :player :O,
              :finished? true}
             initial-data)))))

