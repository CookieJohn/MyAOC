(ns AOC2022.answers.Day6
  (:require
   [AOC2022.helper :refer [read-file]]))

(def input (read-file 6))
               
(defn find-marker-idx [data start size]
  (if (= size (-> (subs data start (+ start size)) set count))
    (+ start size)
    (find-marker-idx data (inc start) size)))

(defn ans-1 [data]
  (find-marker-idx data 0 4))

(defn ans-2 [data]
  (find-marker-idx data 0 14))

(prn (ans-1 input)) ; 1480
(prn (ans-2 input)) ; 2746

(comment
  (ans-1 input)
  (ans-2 input))
