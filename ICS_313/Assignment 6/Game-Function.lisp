;Checks if the monster is dead or alive in a give room
(defun check-monster-state (location nodes)
	(caadr (assoc location nodes)))
	
;Sets the monster dead
(defun set-monster-dead (location nodes)
	(setf (caadr (assoc location nodes)) nil))
	
;Sets the player dead
(defun set-player-dead ()
	(setf *player-state-dead* t))
	
;Sets the reach exit status to true	
(defun set-reach-exit ()
	(setf *reach-exit* t))
	
(defun describe-location (location nodes)
   (cadr (assoc location nodes)))


(defun describe-path (edge)
  `(there is a ,(caddr edge) going ,(cadr edge) from here.))

(defun describe-paths (location edges)
  (apply #'append (mapcar #'describe-path (cdr (assoc location edges)))))

(defun objects-at (loc objs obj-loc)
   (labels ((is-at (obj)
              (eq (cadr (assoc obj obj-loc)) loc)))
       (remove-if-not #'is-at objs)))

(defun describe-objects (loc objs obj-loc)
   (labels ((describe-obj (obj)
                `(you see a ,obj on the floor.)))
      (apply #'append (mapcar #'describe-obj (objects-at loc objs obj-loc)))))


(defun look-around ()
  (append (describe-location *current-location* *Area*)
          (describe-paths *current-location* *Paths*)
          (describe-objects *current-location* *items* *items-location*)))

;New Function Look at next area
(defun look (direction)
	  (labels ((correct-way (edge)
             (eq (cadr edge) direction)))
    (let ((next (find-if #'correct-way (cdr (assoc *current-location* *Paths*)))))
      (if next
		   (progn
				(if (member (car next) *Visited-Area*)      ;Check if area has never been visited yet
					(describe-location (car next) *Paths*)
					'(Area has not been visited.)))
			'(You cannot look that way.)))))

;Lets you traverse through the game
(defun walk (direction)
  (labels ((correct-way (edge)
             (eq (cadr edge) direction)))
    (let ((next (find-if #'correct-way (cdr (assoc *current-location* *Paths*)))))
      (if next 
          (progn (setf *current-location* (car next))
				 (push (car next) *Visited-Area*)	;Updates the Visited-Area parameter
				 (if (eq (check-monster-state *current-location* *Monster-location*) t)
					(progn
					 (format t "~%-------A Monster Suddenly Attack You-------~%~%")
					 (orc-battle *current-location* *Monster-location*)));Monster fight
				(if (eq *current-location* 'exit)
					(set-reach-exit))
                 (look-around)) 					
          '(you cannot go that way.)))))

(defun pickup (object)
  (cond ((member object (objects-at *current-location* *items* *items-location*))
         (push (list object 'body) *items-location*)
         `(you are now carrying the ,object))
	  (t '(you cannot get that.))))

(defun inventory ()
  (cons 'items- (objects-at 'body *items* *items-location*)))

(defun have (object) 
    (member object (cdr (inventory))))
	
;;;;;;;;;;;;;;;;;;;;;;
;Start Game Functions;
;;;;;;;;;;;;;;;;;;;;;;
(defun game-repl ()
    (let ((cmd (game-read)))
        (unless (or (eq (car cmd) 'quit) (eq *player-state-dead* t) (eq *reach-exit* t))
            (game-print (game-eval cmd))
            (game-repl))))

(defun game-read ()
    (let ((cmd (read-from-string (concatenate 'string "(" (read-line) ")"))))
         (flet ((quote-it (x)
                    (list 'quote x)))
             (cons (car cmd) (mapcar #'quote-it (cdr cmd))))))

(defparameter *allowed-commands* '(help look look-around walk pickup inventory))

(defun game-eval (sexp)
    (if (member (car sexp) *allowed-commands*)
        (eval sexp)
        '(i do not know that command. You should try typing help.)))

(defun tweak-text (lst caps lit)
  (when lst
    (let ((item (car lst))
          (rest (cdr lst)))
      (cond ((eql item #\space) (cons item (tweak-text rest caps lit)))
            ((member item '(#\! #\? #\.)) (cons item (tweak-text rest t lit)))
            ((eql item #\") (tweak-text rest caps (not lit)))
            (lit (cons item (tweak-text rest nil lit)))
            (caps (cons (char-upcase item) (tweak-text rest nil lit)))
            (t (cons (char-downcase item) (tweak-text rest nil nil)))))))

(defun game-print (lst)
    (princ (coerce (tweak-text (coerce (string-trim "() " (prin1-to-string lst)) 'list) t nil) 'string))
    (fresh-line))

;This is the help function
(defun help ()
 "Shows help for the user"
  (format t "COMMAND LIST:~%")
  (format t "---DIRECTIONS---~%")
  (format t "1. Head North     -  walk north ~%")
  (format t "2. Head North West -  walk north west~%")
  (format t "3. Head North East -  walk north east~%")
  (format t "4. Head South 	   - walk south~%")
  (format t "5. Head South West -  walk south west~%")
  (format t "6. Head South East -  walk south east~%")
  (format t "7. Head East 	   -  walk east~%")
  (format t "8. Head West 	   -  walk west~%")
  (format t "8. Miscellaneous  -  walk (path name)~%")
  (format t "~%---ACTIONS---~%")
  (format t "1. Look at next room - look (path name)~%")
  (format t "2. Look at current room - look-around~%")
  (format t "3. Pikcup item - pickup (item name)~%")
  (format t "4. Look at what is in your inventory - inventory~%")
  (format t "4. Quit the game - quit~%")
  )