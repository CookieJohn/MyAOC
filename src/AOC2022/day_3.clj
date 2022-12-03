(ns AOC2022.Day3
  (:require
   [AOC2022.helper :as helper]
   [clojure.string :as cs]))

(def input (helper/read-file 3))

(def score-map {"a" 1
                "b" 2
                "c" 3
                "d" 4
                "e" 5
                "f" 6
                "g" 7
                "h" 8
                "i" 9
                "j" 10
                "k" 11
                "l" 12
                "m" 13
                "n" 14
                "o" 15
                "p" 16
                "q" 17
                "r" 18
                "s" 19
                "t" 20
                "u" 21
                "v" 22
                "w" 23
                "x" 24
                "y" 25
                "z" 26})

(defn count-score [char]
  (if (= (cs/upper-case char) char) (+ (score-map (cs/lower-case char)) 26) (score-map char)))

(defn find-char [data]
  (let [length         (count data)
        partition-size (/ length 2)
        [a b]          (mapv (partial apply str) (partition-all partition-size data))]
    (first (filter #(cs/includes? a %) (cs/split b #"")))))

(defn ans-1 [data]
  (->> (cs/split data #"\n")
       (mapv #(find-char %))
       (mapv #(count-score %))
       (apply +)))

(defn find-char-2 [[a b c]]
  (first (filter #(and (cs/includes? a %) (cs/includes? b %)) (cs/split c #""))))

(defn ans-2 [data]
  (->> (cs/split data #"\n")
       (partition 3)
       (map #(find-char-2 %))
       (mapv #(count-score %))
       (apply +)))

(prn (ans-1 input))
(prn (ans-2 input))

(comment
  (ans-1 input)  ; 7793
  (ans-2 input)) ; 2499
