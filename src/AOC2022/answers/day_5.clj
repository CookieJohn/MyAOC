(ns AOC2022.answers.Day5
  (:require
   [AOC2022.helper :refer [read-file str->int]]
   [clojure.string :as cs]))

(def input (->> (read-file 5)
                (cs/split-lines)
                (map #(cs/split % #" "))))

(def init-map {"1" ["Z" "P" "M" "H" "R"]
               "2" ["P" "C" "J" "B"]
               "3" ["S" "N" "H" "G" "L" "C" "D"]
               "4" ["F" "T" "M" "D" "Q" "S" "R" "L"]
               "5" ["F" "S" "P" "Q" "B" "T" "Z" "M"]
               "6" ["T" "F" "S" "Z" "B" "G"]
               "7" ["N" "R" "V"]
               "8" ["P" "G" "L" "T" "D" "V" "C" "M"]
               "9" ["W" "Q" "N" "J" "F" "M" "L"]})

(defn move [current-map times from to]
  (if (> times 0)
    (let [from-coll     (current-map from)
          to-coll       (current-map to)
          move-str      (peek from-coll)
          new-from-coll (pop from-coll)
          new-to-coll   (conj to-coll move-str)]
      (move (-> current-map
               (assoc to new-to-coll)
               (assoc from new-from-coll)) (dec times) from to))
    current-map))

(defn ans-1 [data]
  (->> data
       (reduce (fn [current-map [_ times _ from _ to]]
                 (move current-map (str->int times) from to)) (into (sorted-map) init-map))
       vals
       (map #(peek %))
       cs/join))

(defn move-2 [current-map times from to]
  (let [from-coll     (current-map from)
        to-coll       (current-map to)
        new-from-coll (vec (drop-last times from-coll))
        new-to-coll   (into to-coll (-> (take-last times from-coll) vec))]
    (-> current-map
        (assoc to new-to-coll)
        (assoc from new-from-coll))))

(defn ans-2 [data]
  (->> data
       (reduce (fn [current-map [_ times _ from _ to]]
                 (move-2 current-map (str->int times) from to))  (into (sorted-map) init-map))
       vals
       (map #(peek %))
       cs/join))

(prn (= "VQZNJMWTR" (ans-1 input)))
(prn (= "NLCDCLVMQ" (ans-2 input)))

(comment
  (ans-1 input)
  (ans-2 input))
