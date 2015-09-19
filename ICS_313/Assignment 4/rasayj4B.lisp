;This program creates a game where a user pick a number from 1 to 100
;and the computer has to guess it.

(defparameter *small* 1) ;defines the small variable and set it to 1
(defparameter *big* 100) ;defines the big variable and set it to 100
(defparameter *user-number* 0)
(defparameter *computer-guess* 0)
(defparameter *allowed-commands* '(guess-big guess-small))

(defconstant +ID+ "John Rasay")
;This prints the student name, course-number and assignment-number.
(defun id(course-number assignment-number)
	(format t "Name: ~a~%Course: ICS~d~%Assignment # ~d~%" +ID+ course-number assignment-number))


;this function uses the values of *big* and *small*
;to generate a guess of the player's number
(defun guess-my-number ()
     (ash (+ *small* *big*) -1))

;this function sets the new *big* variable a new value 
;which is the last guess -1 and call the function 
;guess-my-number again using the new value of *big*
(defun smaller ()
     (setf *big* (1- (guess-my-number)))
     (guess-my-number))

;this function sets the new *small* variable a new value 
;which is the last guess +1 and call the function 
;guess-my-number again using the new value of *small*
(defun bigger ()
     (setf *small* (1+ (guess-my-number)))
     (guess-my-number))

;this function resets back the *small* and *big* variable to their original
;value and starts a new guess
(defun start-over ()
   (defparameter *small* 1)
   (defparameter *big* 100)
   (guess-my-number))
  
  
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;											     ;
;				Assignment 4B				     ;
;						                         ;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;This function gets a number from the user for the computer to guess
(defun get-number()
	(print "Enter a number for the computer to guess:")
	(let ((input(read)))
			(if (or (< input 1) (> input 100))
				(progn
					(print "Please enter a number from 1 to 100")
					(terpri)
					(get-number))
				(setf *user-number* input))))
				
;This function calls the bigger function and store the result in a variable		
(defun guess-big()
	(setf *computer-guess* (bigger)))
	
;This function calls the smaller function and store the result in a variable	
(defun guess-small()
	(setf *computer-guess* (smaller)))
	
;This function resets the value of small, big and the computer-guess
(defun guess-restart()
	(setf *small* 1)
	(setf *big* 100)
	(setf *computer-guess* 0))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 					       Wizard Game Functions	 						  ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defun game-read ()
    (let ((cmd (read-from-string (concatenate 'string "(" (read-line) ")"))))
         (flet ((quote-it (x)
                    (list 'quote x)))
             (cons (car cmd) (mapcar #'quote-it (cdr cmd))))))
			 
(defun game-eval (sexp)
    (if (member (car sexp) *allowed-commands*)
        (eval sexp)
        '(The command is invalid.)))

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
	
(defun game-repl ()
	(print "Give Commands to the computer or X to exit:")
    (let ((cmd (game-read)))
        (unless (eq (car cmd) 'x)
			(game-print (game-eval cmd))
			(game-start))))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 		                    End of Wizard Game Functions                       ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;				Assignment4B Interface Functions							   ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
			
;This function ask if the user wants to play a new game	
(defun game-replay ()
	(print "Would you like to play a new game?  y/n: ")					   ;prints message with print
	(let ((choice (read)))												   ;gets user input
		(unless (eq choice 'n)											   ;until user types n
			(if (eq choice 'y)											   ;if user types y
				(progn
					  (guess-restart)									   ;call guess-restart function
					  (get-number)										   ;call get-number function
					  (game-start))										   ;call game-start function
				(progn
					(print "Invalid input, please enter y or n.")		   ;prints message with print
					(terpri)											   ;prints a new line
					(game-replay))))							           ;calls game-replay function
		(print "Thank you for playing.")								   ;prints a message with print
		nil))															   ;return nil

;This function starts the game 
(defun game-start()
		(progn
			(if (eq *user-number* *computer-guess*)							;computer guess user's number
				(progn
					(print "The computer guess your number!")				;prints message with print
					(game-replay)))											;calls the game-replay function
			(if (> *small* *big*)
				(progn
					(print "No solution has been found.")					;prints message with print
					(print "Restarting game.")								;prints message with print
					(guess-restart)											;calls the guess-restart function
					(game-start))))									        ;calls the game-repl function
			(unless (eq *user-number* *computer-guess*)
				(game-repl)))											    ;calls game-repl function

				
;The start menu of the game.
;Ask the user if he wants to play or not
(defun guess-game ()
	(print "Hello, welcome to the guessing game")							;prints message with print
	(print "Think of a number from 1 to 100 and")							;prints message with print
	(print "the computer will guess your number")							;prints message with print
	(print "Would you like to play? y/n:")									;prints message with print
	(let ((choice (read)))													;reads input from user							
		(unless (eq choice 'n)												;loop until user types n
			(if (eq choice 'y)												;if user types y
				(progn		
					(get-number)											;calls get-number function
					(game-start))											;calls the game-start function
				(progn
					(print "invalid input, please enter y or n.")			;prints message with print
					(terpri)												;prints a new line
					(guess-game))))											;calls guess-game function
		(print "Good Bye.")													;prints message with print
		 nil))																;return nil
			