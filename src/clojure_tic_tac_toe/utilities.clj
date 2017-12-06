(ns clojure-tic-tac-toe.utilities
  (:require [clojure.set :as set]
            [clojure.string :as str]))

(defn get-map-with-value-in-set
  [map-to-check value]
  (into {} (filter (fn [[k v]]
                     (contains? v value))
                   map-to-check)))

(defn join-lines
  [lines]
  (str/join "\n" lines))

(defn contains-set?
  [coll set-to-check]
  (true?
    (some #(set/subset? % set-to-check) coll)))

(defn- set-to-sorted-list
  [set-arg]
  (into () set-arg))

(defn set-to-list-or-nil
  [set-arg]
  (let [sorted-list (set-to-sorted-list set-arg)]
    (if (empty? sorted-list)
      nil
      sorted-list)))

