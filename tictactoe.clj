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
  [board player x y]
    (assoc board y (assoc (get board y) x player)))

(defn get-move [board]
	(println "Pleaser enter an x and y: ")
	(let[x (read-string (read-line)) y (read-string (read-line))]
	(if (and (< x (count board)) (>= x 0 ) (< y (count board)) (>= y 0 ) (= (nth (nth board x) y) \space)) {:x x, :y y} (do (println "Please Enter a Valid Move: ") (get-move board)))
	))

(defn check-win
  [board]
    false)
        
(defn main-loop
  "The game loop."
  [board moves who]
  (cond
    (check-win board) (if (= who \X) \O \X)
    (>= moves (* (count board) (count board))) (do (print-board board) "Stalemate")
    :else (do (print-board board)
      (if (= who \X)
        (main-loop (make-move board \X (mod moves (count board)) (quot moves (count board))) (inc moves) \O)
        (main-loop (make-move board \O (mod moves (count board)) (quot moves (count board))) (inc moves) \X)))))

; general structure:
; 1. ask for board size
; 2. generate empty board
; 3. loop:
;   1. print board
;   2. player's turn
;   3. check for win
;   4. print board
;   5. computer's turn
;   6. check for win
; 4. print a message
