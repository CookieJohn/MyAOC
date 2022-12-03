(ns AOC2022.helper)

(defn read-file
  "Return full file contents from `path`."
  [day]
  (slurp (str "/Users/john/myAOC/src/AOC2022/questions/day_" day ".txt")))

(comment
  (read-file 1))
