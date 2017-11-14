(ns clojure-tic-tac-toe.console_ui.console_ui_computer_move_test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.console_ui.console_ui_computer_move :refer :all]))

(deftest have-computer-move-test
  (testing "when having the computer move"
    (with-out-str
      (is (= :1
             (have-computer-move {:board {:X #{:2 :3 :4 :5}, :O #{:6 :7 :8 :9}}}))
          "it returns a move"))
    (is (= true
           (str/includes?
             (with-out-str
               (have-computer-move {:X #{}, :O #{}}))
             "computer moved"))
        "it displays that the computer moved")))

