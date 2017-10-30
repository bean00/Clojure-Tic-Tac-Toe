(ns clojure-tic-tac-toe.utilities
  (:require [clojure.string :as str]))

(defn get-map-with-value-in-set
  [map-to-check value]
  (into {} (filter (fn [[k v]]
                     (contains? v value))
                   map-to-check)))

(defn join-lines
  [lines]
  (str/join "\n" lines))

