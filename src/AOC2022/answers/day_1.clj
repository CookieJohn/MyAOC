(ns AOC2022.answers.Day1
  (:require
   [AOC2022.helper :as helper]
   [clojure.string :as cs]))

(def input (helper/read-file 1))

(defn ans-1 [data]
  (->> (cs/split data #"\n\n")
       (mapv (fn [n] 
               (->> (cs/split n #"\n")
                    (mapv #(Integer/parseInt %))
                    (reduce +))))
       (apply max)))

(defn ans-2 [data]
  (->> (cs/split data #"\n\n")
       (mapv (fn [n] 
               (->> (cs/split n #"\n")
                    (mapv #(Integer/parseInt %))
                    (reduce +))))
       sort
       (take-last 3)
       (apply +)))

(prn (ans-1 input))
(prn (ans-2 input))

(comment
  (ans-1 input)
  (ans-2 input))
