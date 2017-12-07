(ns clojure-tic-tac-toe.valid_moves_test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.valid_moves :refer :all]))

;(deftest token-at-test
;  (testing "when neither player has moved to the position"
;    (is (= :1
;           (token-at empty-board :1))
;        "it returns the token key for that position"))
