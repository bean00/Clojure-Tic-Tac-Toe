(ns clojure-tic-tac-toe.core-test
  (:require [clojure.test :refer :all]
            [clojure-tic-tac-toe.core :refer :all]))

(deftest print-board-test
  (testing "Print a board"
    (is (= "Example:\n"
           (with-out-str (print-board))))))
