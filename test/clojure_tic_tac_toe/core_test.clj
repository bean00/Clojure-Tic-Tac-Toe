(ns clojure-tic-tac-toe.core-test
  (:require [clojure.test :refer :all]
            [clojure-tic-tac-toe.core :refer :all]))

(deftest board-to-string-test
  (let [separate-rows [" 1 | 2 | 3 "
                       "---+---+---"
                       " 4 | 5 | 6 "
                       "---+---+---"
                       " 7 | 8 | 9 "]
        view (clojure.string/join "\n" separate-rows)]
       (is (= view
              (board-to-string)))))
