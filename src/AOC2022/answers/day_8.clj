(ns AOC2022.answers.Day8
  (:require
   [AOC2022.helper :refer [read-file str->int]]
   [clojure.string :as cs]))

(def input (->> (read-file 8)
                (cs/split-lines)
                (mapv #(cs/split % #""))
                (map-indexed (fn [c-idx col] (-> (map-indexed (fn [r-idx height] (hash-map :c-idx c-idx :r-idx r-idx :height (str->int height))) col) vec)))
                flatten
                vec))

(defn max-left? [c-idx r-idx]
  (->> (filter (fn [t] (and (= c-idx (:c-idx t)) (< r-idx (:r-idx t)))) input)
       (map :height)
       (apply max)))

(defn max-right? [c-idx r-idx]
  (->> (filter (fn [t] (and (= c-idx (:c-idx t)) (> r-idx (:r-idx t)))) input)
       (map :height)
       (apply max)))

(defn max-top? [c-idx r-idx]
  (->> (filter (fn [t] (and (< c-idx (:c-idx t)) (= r-idx (:r-idx t)))) input)
       (map :height)
       (apply max)))

(defn max-bottom? [c-idx r-idx]
  (->> (filter (fn [t] (and (> c-idx (:c-idx t)) (= r-idx (:r-idx t)))) input)
       (map :height)
       (apply max)))

(defn visible? [total {:keys [c-idx r-idx height]}]
  (if (or (= 0 c-idx) (= 98 c-idx) (= 0 r-idx) (= 98 r-idx))
    (inc total)
    (if (> (count (filter #(< % height) [(max-left? c-idx r-idx) (max-right? c-idx r-idx) (max-top? c-idx r-idx) (max-bottom? c-idx r-idx)])) 0)
      (inc total)
      total)))

(defn ans-1 [data]
  (reduce visible? 0 data))

(defn max-left-2? [c-idx r-idx height]
  (->> (filter (fn [t] (and (= c-idx (:c-idx t)) (< r-idx (:r-idx t)))) input)
       (reduce (fn [m v] (if (>= (v :height) height)
                          (if (= (v :height) height)
                           (reduced (inc m))
                           (reduced m))
                          (inc m))) 0)))

(defn max-right-2? [c-idx r-idx height]
  (->> (filter (fn [t] (and (= c-idx (:c-idx t)) (> r-idx (:r-idx t)))) input)
       reverse
       (reduce (fn [m v] (if (>= (v :height) height)
                          (if (= (v :height) height)
                           (reduced (inc m))
                           (reduced m))
                          (inc m))) 0)))

(defn max-top-2? [c-idx r-idx height]
  (->> (filter (fn [t] (and (< c-idx (:c-idx t)) (= r-idx (:r-idx t)))) input)
       (reduce (fn [m v] (if (>= (v :height) height)
                          (if (= (v :height) height)
                           (reduced (inc m))
                           (reduced m))
                          (inc m))) 0)))

(defn max-bottom-2? [c-idx r-idx height]
  (->> (filter (fn [t] (and (> c-idx (:c-idx t)) (= r-idx (:r-idx t)))) input)
       reverse
       (reduce (fn [m v] (if (>= (v :height) height)
                          (if (= (v :height) height)
                           (reduced (inc m))
                           (reduced m))
                          (inc m))) 0)))

(defn visible-2? [total {:keys [c-idx r-idx height]}]
  (if (or (= 0 c-idx) (= 98 c-idx) (= 0 r-idx) (= 98 r-idx))
    total
    (conj total (* (max-left-2? c-idx r-idx height) (max-right-2? c-idx r-idx height) (max-top-2? c-idx r-idx height) (max-bottom-2? c-idx r-idx height)))))

(defn ans-2 [data]
  (->> (reduce visible-2? [] data)
       (apply max)))

(prn (ans-1 input)) ; 1827
(prn (ans-2 input)) ; 335580

(comment
  (ans-1 input)
  (ans-2 input)
  (max-left-2? 20 35 99))
