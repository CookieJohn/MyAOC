(ns AOC2022.answers.Day4
  (:require
   [AOC2022.helper :refer [read-file str->int]]
   [clojure.string :as cs]
   [clojure.set :as cset]))

(def input (read-file 4))

(defn split-into-set [str-data]
  (->> (cs/split str-data #"-")
       (mapv #(str->int %))
       (apply (fn [s e] (range s (+ 1 e))))
       set))

(defn check-fully-contain [a-set b-set]
  (or (cset/subset? a-set b-set) (cset/superset? a-set b-set)))

(defn ans-1 [data]
  (->> (cs/split data #"\n")
       (mapv #(cs/split % #","))
       (mapv (fn [[a b]] [(split-into-set a) (split-into-set b)]))
       (mapv (fn [[a b]] (check-fully-contain a b)))
       (filter #(true? %))
       count))

(defn check-fully-contain-2 [a-set b-set]
  (cset/intersection a-set b-set))

(defn ans-2 [data]
  (->> (cs/split data #"\n")
       (mapv #(cs/split % #","))
       (mapv (fn [[a b]] [(split-into-set a) (split-into-set b)]))
       (mapv (fn [[a b]] (check-fully-contain-2 a b)))
       (filter #(not-empty %))
       count))

(prn (ans-1 input)) ; 494
(prn (ans-2 input)) ; 833

(comment
  (ans-1 input)
  (ans-2 input))
