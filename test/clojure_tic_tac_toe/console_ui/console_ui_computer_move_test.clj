(ns clojure-tic-tac-toe.console_ui.console_ui_computer_move_test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.console_ui.console_ui_computer_move :refer :all]))

(deftest have-computer-move-test
  (testing "when having the computer move"
    (with-out-str
      (is (= :3
             (have-computer-move
               {:board {:X #{:1 :2 :8 :6}, :O #{:4 :5 :7 :9}}, :player :X}))
          "it returns a move"))
    (is (= true
           (str/includes?
             (with-out-str
               (have-computer-move
                 {:board {:X #{:1 :2 :3 :4}, :O #{:5 :6 :7 :8}}, :player :X}))
             "computer moved"))
        "it displays that the computer moved")))

