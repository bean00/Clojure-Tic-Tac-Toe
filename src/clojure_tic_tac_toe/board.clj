(ns clojure-tic-tac-toe.board)

(defn add-move
  [board move player]
  (update board player conj move))

(defn filter-out-player-moves
  [board position]
  (into {} (filter (fn [[k v]]
                     (contains? v position))
                   board)))

(defn token-at
  [board position]
  (or
    (first (keys (filter-out-player-moves board position)))
    position))

