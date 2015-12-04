(defn gen-board
  "Given a side length, it returns a square 2D vector filled with spaces."
  ([side-length]
    (vec (repeat side-length (vec (repeat side-length \space)))))
  ([] (gen-board 3)))
  
(defn print-board
  "Given a game board, it prints a graphical representation of the board."
  [board]
    (loop [i 0] (let [row (get board i)]
      (println (clojure.string/join " | " row))
      (println (clojure.string/join "-+-" (repeat (count row) \-)))
      (if (< i (- (count board) 2))
        (recur (inc i)))))
    (println (clojure.string/join " | " (last board))))

(defn make-move
  [board player x y]
    (assoc board y (assoc (get board y) x player)))

(defn get-move [board]
	(println "Pleaser enter an x and y: ")
	(let[x (read-string (read-line)) y (read-string (read-line))]
	(if (and (< x (count board)) (>= x 0 ) (< y (count board)) (>= y 0 ) (= (nth (nth board x) y) \space)) {:x x, :y y} (do (println "Please Enter a Valid Move: ") (get-move board)))
	))
 
(def board (gen-board 10))
(def board (make-move board \X 2 2))
(def board (make-move board \O 4 0))
(def board (make-move board \X 1 1))
(print-board board)





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
