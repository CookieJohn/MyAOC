(ns AOC2022.answers.Day2
  (:require
   [AOC2022.helper :as helper]
   [clojure.string :as cs]))

;; A for Rock, B for Paper, and C for Scissors
;; X for Rock, Y for Paper, and Z for Scissors
;; 1 for Rock, 2 for Paper, and 3 for Scissors
(def score-map {"A" 1 "B" 2 "C" 3})

(def transform-map {"X" "A" "Y" "B" "Z" "C"})

(def win-or-lose-map {"AB" 0 
                      "AC" 6
                      "BC" 0 
                      "BA" 6
                      "CA" 0 
                      "CB" 6})

(def input (helper/read-file 2))

(defn ans-1 [data]
  (->> (cs/split data #"\n")
       (mapv #(cs/split % #" "))
       (mapv (fn [[a b]]
               (let [reverse-map    (transform-map b)
                     score          (score-map reverse-map)
                     win-lose-score (if (= a reverse-map) 3 (win-or-lose-map (str reverse-map a)))]
                 (+ score win-lose-score))))
       (apply +)))

(def score-map-2 {"X" 0 "Y" 3 "Z" 6})

(def win-or-lose-map-2 {"AX" "C" 
                        "AZ" "B"
                        "BX" "A" 
                        "BZ" "C"
                        "CX" "B" 
                        "CZ" "A"})

;; X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win
(defn ans-2 [data]
  (->> (cs/split data #"\n")
       (mapv #(cs/split % #" "))
       (mapv (fn [[a b]]
               (let [win-lose-score (score-map-2 b)
                     win-lose-map   (if (= b "Y") a (win-or-lose-map-2 (str a b)))
                     score          (score-map win-lose-map)] 
                 (+ score win-lose-score))))
       (apply +)))

(prn (ans-1 input))
(prn (ans-2 input))

(comment
  (ans-1 input)  ; 12586
  (ans-2 input)) ; 13193
