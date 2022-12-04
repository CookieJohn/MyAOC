(ns AOC2022.helper)

(defn read-file
  "Return full file contents from `path`."
  [day]
  (slurp (str "/Users/john/myAOC/src/AOC2022/questions/day_" day ".txt")))

(defn str->int [s]
  (Integer/parseInt s))

(comment
  (str->int "1")
  (read-file 1))
