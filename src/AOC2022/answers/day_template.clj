(ns AOC2022.answers.DayTemplate
  (:require
   [AOC2022.helper :refer [read-file str->int]]
   [clojure.string :as cs]))

(def input (read-file 1))

(defn ans-1 [data]
  (->> (cs/split data #"\n")))

(defn ans-2 [data]
  (->> (cs/split data #"\n")))

(prn (ans-1 input))
(prn (ans-2 input))

(comment
  (ans-1 input)
  (ans-2 input))
