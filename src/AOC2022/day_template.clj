(ns AOC2022.DayTemplate
  (:require
   [AOC2022.helper :as helper]
   [clojure.string :as cs]))

(def input (helper/read-file 1))

(defn ans-1 [data]
  (->> (cs/split data #"\n")))

(defn ans-2 [data]
  (->> (cs/split data #"\n")))

(prn (ans-1 input))
(prn (ans-2 input))

(comment
  (ans-1 input)
  (ans-2 input))
