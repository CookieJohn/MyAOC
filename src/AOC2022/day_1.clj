(ns AOC2022.Day1
  (:require
   [AOC2022.questions.Day1 :refer [question]]
   [clojure.string :as cs]))

(defn call-1 [q]
  (->> (cs/split q #"\n\n")
       (mapv (fn [n] 
               (->> (cs/split n #"\n")
                    (mapv #(Integer/parseInt %))
                    (reduce +))))
       (apply max)))

(defn call-2 [q]
  (->> (cs/split q #"\n\n")
       (mapv (fn [n] 
               (->> (cs/split n #"\n")
                    (mapv #(Integer/parseInt %))
                    (reduce +))))
       sort
       (take-last 3)
       (apply +)))

(comment
  (call-1 question)
  (call-2 question))
