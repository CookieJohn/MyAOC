(ns AOC2022.answers.Day7
  (:require
   [AOC2022.helper :refer [read-file str->int]]
   [clojure.string :as cs]
   [clojure.pprint :as pp]))

(def input (-> (read-file 7)
               (cs/split-lines)))

(defn new-path [cd-str path]
  (case cd-str
    "/"  (conj path :dir)
    ".." (pop path)
    (conj path cd-str)))

(defn update-path-sum [m path v]
  (if (= #{:dir} (set path))
    m
    (-> m
        (update-in (conj path :sum) #(+ % v))
        (recur (pop path) v)))) 
  
(defn reorganize [m v]
  (let [{:keys [path _dir]} m
        first-v (-> (cs/split v #" ") first)
        last-v  (-> (cs/split v #" ") last)]
   (if (or (cs/starts-with? v "$ cd") (cs/starts-with? v "dir"))
     (cond-> m
       (cs/starts-with? v "$ cd") (assoc :path (new-path last-v path)) 
       (cs/starts-with? v "dir")  (assoc-in (conj path last-v) {:sum 0}))
     (if (= "$ ls" v)
       m
       (let [int-v (str->int first-v)]
         (-> m
             (assoc-in (conj path last-v) int-v)
             (update-path-sum path int-v)))))))

;; ref: https://stackoverflow.com/a/28097404
(defn find-all-nested
  [m k]
  (->> (tree-seq map? vals m)
       (filterv map?)
       (keep k)))

(defn ans-1 [data]
  (let [new-data (-> (reduce (fn [m v] (reorganize m v)) {:path [] :dir {:sum 0}} data)
                     (dissoc :path)
                     :dir)]
    (->> (find-all-nested new-data :sum)
         (filter #(<= % 100000))
         (apply +))))

(defn ans-2 [data]
  (let [new-data (-> (reduce (fn [m v] (reorganize m v)) {:path [] :dir {:sum 0}} data)
                     (dissoc :path)
                     :dir)
        need-release-size (->> new-data
                               vec
                               vals
                               (mapv #(if (map? %) (:sum %) 0))
                               (apply +) ; 45390168
                               (- 70000000) ; 24609832
                               (- 30000000))] ; 5390168
    (->> (find-all-nested new-data :sum)
         (filter #(<= need-release-size %))
         (apply min))))

(pp/pprint (ans-1 input)) ; 1350966
(pp/pprint (ans-2 input)) ; 6296435

(comment
  (ans-1 input)
  (ans-2 input))
