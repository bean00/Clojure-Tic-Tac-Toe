(ns clojure-tic-tac-toe.computer_move_test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.computer_move :refer :all]))

(deftest make-random-move-test
  (testing "when the computer has 1 move left"
    (is (= :9
           (make-random-move {:board {:X #{:1 :2 :3 :4}, :O #{:5 :6 :7 :8}}}))
        "it returns that move"))
  (testing "when the computer has 5 moves left"
    (let [move (make-random-move {:board {:X #{:6 :7}, :O #{:8 :9}}})]
      (is (= true
             (contains? #{:1 :2 :3 :4 :5} move))
          "it returns one of those moves")))
  (testing "when there are no moves left"
    (is (= nil
           (make-random-move {:board {:X #{:1 :2 :3 :4 :5}, :O #{:6 :7 :8 :9}}}))
        "it returns nil")))

