;This program creates a game where a user pick a number from 1 to 100
;and the computer has to guess it.

(defparameter *ID* (string "John Rasay")) ;defines a variable containing a string
(defparameter *small* 1) ;defines the small variable and set it to 1
(defparameter *big* 100) ;defines the big variable and set it to 100

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