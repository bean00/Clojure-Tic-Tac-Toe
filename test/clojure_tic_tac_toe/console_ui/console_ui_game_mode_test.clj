(ns clojure-tic-tac-toe.console_ui.console_ui_game_mode_test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest testing is]]
            [clojure-tic-tac-toe.console_ui.console_ui_game_mode :refer :all]))

(deftest get-valid-game-mode-test
  (testing "when initially getting the game mode"
    (is (= true
           (str/includes?
             (with-out-str
               (with-in-str "h\n"
                 (get-valid-game-mode)))
             ">"))
        "it displays the angle bracket"))
  (testing "when a valid game mode is entered"
    (with-out-str
      (is (= :h
             (with-in-str "h\n"
               (get-valid-game-mode)))
          "it returns the game mode as a keyword")))
  (testing "when an invalid mode is entered 2 times, followed by a valid mode"
    (let [output (with-out-str
                   (with-in-str "X\nX\nh\n"
                     (get-valid-game-mode)))]
      (is (= true
             (str/includes? output "is invalid"))
          "it displays the correct error message")
      (is (= true
             (str/includes? output "enter the game mode"))
          "it displays the game mode prompt")
      (is (= 2
             (count (re-seq #"enter the game mode" output)))
          "it displays the game mode prompt twice"))))

