(ns AOC2022.Day2
  (:require
   [AOC2022.questions.Day2 :refer [question]]
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

(defn call-1 [q]
  (->> (cs/split q #"\n")
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
(defn call-2 [q]
  (->> (cs/split q #"\n")
       (mapv #(cs/split % #" "))
       (mapv (fn [[a b]]
               (let [win-lose-score (score-map-2 b)
                     win-lose-map   (if (= b "Y") a (win-or-lose-map-2 (str a b)))
                     score          (score-map win-lose-map)] 
                 (+ score win-lose-score))))
       (apply +)))

(comment
  (prn question)
  (score-map "X")
  (call-1 question) ; 12586
  (call-2 question)) ; 13193
