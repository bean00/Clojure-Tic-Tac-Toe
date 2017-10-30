(ns clojure-tic-tac-toe.input_output_helper_test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.input_output_helper :refer :all]))

(deftest get-single-move-test
  (testing "when initially getting the move from the player"
    (is (= true
           (str/includes?
             (with-out-str
               (with-in-str "a"
                 (get-single-move :X)))
             "Player X, please enter"))
        "it displays the prompt and gets the move"))
  (testing "when a valid move is entered"
    (with-out-str
      (is (= :2
             (with-in-str "2"
               (get-single-move :X)))
          "it returns the move as a keyword")))
  (testing "when an invalid move is entered"
    (is (= true
           (str/includes?
             (with-out-str
               (with-in-str "a"
                 (get-single-move :O)))
             "Error"))
        "it displays an error message")))

