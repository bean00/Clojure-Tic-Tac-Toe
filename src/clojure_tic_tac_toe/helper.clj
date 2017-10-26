(ns clojure-tic-tac-toe.helper)

(defn get-map-with-value-in-set
  [map-to-check value]
  (into {} (filter (fn [[k v]]
                     (contains? v value))
                   map-to-check)))

