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

(defn mainloop
  "The game loop."
  [board]
  (loop [moves 0]
    (print-board board)
    ; Get player's move
    ; Check win condition
    (print-board board)
    ; Get computer move
    ; Check win condition
    (if (< moves (* (count board) (count board)))
      (recur (inc moves))))
  (println "Someone won"))

(mainloop (gen-board 4))

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
