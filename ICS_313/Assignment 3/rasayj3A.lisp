(defconstant +ID+ "JOHN RASAY")
;This prints the student name, course-number and assignment-number.
(defun id(course-number assignment-number)
	(format t "Name: ~a~%Course: ICS~d~%Assignment # ~d~%" +ID+ course-number assignment-number))

;This is a recursive version of the greatest common denominator function.
(defun gc_d(a b)
  (cond
	((not(numberp a))											;check if a is a number
		(princ "Please enter two integer numbers")				;print error with princ
		(terpri)												;new line
		nil)													;return nil
	((not(numberp b))											;check if b is a number
		(princ "Please enter two integer numbers")				;print error with princ
		(terpri)												;new line
		nil)													;return nil
	((<= a -1)													;check if a is a non-negative number
	   (princ "Please enter two non-negative number")			;print error with princ
		nil)													;return nil
	((<= b -1)													;check if b is a non-negative number
	   (princ "Please enter two non-negative number")			;print error with princ
		nil)													;return nil
	(t (if (= b 0) 												;otherwise if b = 0
		  a 													;return a
		  (gc_d b (mod a b))))))								;else do a recursive call of gc_d

;This is a non-recursive version of the greatest common denominator function.
(defun gc_d2(a b)
	(cond
		((not(numberp a))										;check if a is a number
			(princ "Please enter two integer numbers")			;print error with princ
			(terpri)											;new line
			nil)												;return nil
		((not(numberp b))										;check if a is a number
			(princ "Please enter two integer numbers")			;print error with princ
			(terpri)											;new line
	   		 nil)												;return nil
		((<= a -1)												;check if a is a non-negative number
			(princ "Please enter two non-negative number")		;print error with princ
			(terpri)											;new line
		     nil)												;return nil
		((<= b -1)												;check if b is a non-negative number
			(princ "Please enter two non-negative number")		;print error with princ
			(terpri)											;new line
		     nil)												;return nil
		(t(if (= b 0)											;otherwise if b = 0
			a													;return a
			(let ((x a))										;else let x = a
				(loop while (> b 0)								;loop while b is greater than 0
					do											;calculate the gcd
						(if (> x b)
							(setf x (- x b))
							(setf b (- b x))))
				(format t "Answer: ~d" x))))))					;print the answer using format

;This is the recursive version of the factorial function.
(defun factorial(n)
	(cond
		((not(numberp n))													;check if n is a number
			(princ "Please enter an integer number")					    ;print error with princ
			(terpri)														;new line
			 nil)															;return nil
		((<= n -1)															;check if n is a non-negative number
			(princ "Please enter a non-negative number")					;print error with princ
			(terpri)														;new line
			nil)															;return nil
		((eq n 0)															;check if n is 0
			1)																;return 1
		(t (* n (factorial (- n 1))))))										;otherwise multiply n by n-1

;This is the non-recursive version of the factorial function.
(defun factorial2(n)
	(cond
		((not(numberp n))													;check if n is a number
			(princ "Please enter an integer number")					    ;print error with princ
			(terpri)														;new line
			 nil)															;return nil
		((<= n -1)															;check if n is a non-negative number
			(princ "Please enter a non-negative number")					;print error with princ
			(terpri)														;new line
			nil)															;return nil
		((eq n 0)															;check if n is 0
			1)																;return 1
		(t(let ( (x n) (i n))												;otherwise let x = n and i = n
			(loop while (> i 1)												;loop while i is > than 1
				do															;calculate the factorial of the integer input
					(setf n (- n 1))
					(setf x (* x n))
					(setf i (- i 1)))
			(format t "Answer: ~d" x)))))									;print the answer using format
		
		

		