(defn gen-board
  "Given a side length, it returns a square 2D vector filled with spaces."
  ([side-length]
    (vec (repeat side-length (vec (repeat side-length \space)))))
  ([] (gen-board 3)))
  
(defn print-board
  "Given a game board, it prints a graphical representation of the board."
  [board]
    (println (str "   " (clojure.string/join "   " (range 0 (count board)))))
    (loop [i 0] (let [row (get board i)]
      (println (str (format "%2d" i) \space (clojure.string/join " | " row)))
      (println (str "   " (clojure.string/join "-+-" (repeat (count row) \-))))
      (if (< i (- (count board) 2))
        (recur (inc i)))))
    (println (str (format "%2d" (dec (count board))) \space (clojure.string/join " | " (last board)))))

(defn make-move
  "Return an altered board state with a move made at the given coordinate."
  [board player {x :x y :y}]
    (assoc board y (assoc (get board y) x player)))

(defn get-move [board]
	(println "Please enter an x and y: ")
	(let[x (read-string (read-line)) y (read-string (read-line))]
	(if (and (< x (count board)) (>= x 0 ) (< y (count board)) (>= y 0 ) (= (nth (nth board y) x) \space)) {:x x, :y y} (do (println "Please Enter a Valid Move: ") (get-move board)))
	))
  
(defn check-row
  [board r]
  (let [row (nth board r)]
    (and
      (= (count (filter #(not= % \space) row)) 4)
      (apply = row))))
  
(defn check-win
  [board]
    (let [g #(nth (nth board %2) %1)]
      (and (= (g 0 0) \X) (= (g 1 0) \X) (= (g 2 0) \X) (= (g 3 0) \X))))
  
(defn possible-moves
  [board]
  (for [x (range 0 (count board)) y (range 0 (count board))
    :when (= (nth (nth board y) x) \space)] {:x x :y y}))
  
(defn comp-move 
  [board]
    (let [poss (possible-moves board)
      wins (filter (comp check-win (partial make-move board \O)) poss)
      loss (filter (comp check-win (partial make-move board \X)) poss)]
      (cond
        (not (empty? wins)) (rand-nth wins)
        (not (empty? loss)) (rand-nth loss)
        :else (rand-nth poss))))
        
(defn main-loop
  "The game loop."
  ([board moves who]
    (cond
      (check-win board) (do (print-board board) (if (= who \X) "Computer wins." "Player wins!"))
      (>= moves (* (count board) (count board))) (do (print-board board) "Stalemate.")
      :else (do (print-board board) (println)
        (if (= who \X)
          (main-loop (make-move board \X (get-move board)) (inc moves) \O)
          (main-loop (do (println "Computer makes a move:") (make-move board \O (comp-move board))) (inc moves) \X)))))
  ([board] (main-loop board 0 \X))
  ([] (main-loop (gen-board) 0 \X)))

(def test-board [[\X \space \X \X] [\space \X \O \space] [\space \space \O \space] [\space \O \O \space]])

(main-loop (gen-board 4))
