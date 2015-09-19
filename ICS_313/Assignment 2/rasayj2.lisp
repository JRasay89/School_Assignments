(defconstant +ID+ "JOHN RASAY")
(defparameter *cons* nil)

;This function takes two parameters the course number and assignment number
(defun id(course-number assignment-number)
	(cond
		((not(eq course-number 313))																;check if course number is 313
			(princ "Incorrect course number"))														;print message with princ	
		((not(eq assignment-number 2))																;check if assignment-number 2
			(princ "Incorrect assignment number"))													;print message with princ
		(t  (princ "Name: ") 																		;print message with princ
			(prin1 +ID+)																			;print value of ID with prin1
			(terpri)																				;print newline
			(princ "Course: ICS313")																;print message with princ
			(terpri)																				;print newline
			(princ "Assignment # 2 ")))																;print message with princ
			nil)																					;return nil

;This function takes two parameters the item to be search and the list to be search in.
;It finds the item in the list and returns the item if found in the list.
(defun MY-FINDER(item alist)
	(cond
		((not (listp alist))           																;Checks if parameter is a list
			(princ "Error in MY-FINDER function. The second parameter must be a list, but was ")	;print message with princ
			(prin1 alist)																			;print value of the parameter
			(terpri)																				;print newline
			 nil)																					;return nil
		((null alist)																				;check if list is null
			nil)																					;return an empty list
		((eq item (car alist))																		;check if item is in the list
			(car alist))																			;print the first item in the list using car
		((not(eq item(car alist)))																	;check if item is not in the list
			(MY-FINDER item (cdr alist)))))															;call MY-FINDER
		
;This function returns a list that is the same as the input list, except
;the last item has been removed.
(defun EAT-LAST (alist)
	(cond
		((not (listp alist))           														;Checks if parameter is a list
			(princ "Error in EAT-LAST function. The parameter must be a list, but was ")	;print message with princ
			(prin1 alist)																	;print value of the parameter
			(terpri)																		;print newline
			 nil)																			;return nil
		((null alist)																		;check if list is null
			())																				;return an empty list
		((null (cdr alist))																	;check if list has only 1 element
			nil)																			;return nil
		(t (cons (car alist) (EAT-LAST (cdr alist))))))										;copy all elements of the list into a new list except the last item in the list

;This function takes a list as its parameter and returns a list
;containing only the symbols in the list, in the same order they appear.
(defun SYMBOLS-ONLY (alist)
	(cond
		((not (listp alist))           														;Checks if parameter is a list
			(princ "Error in SYMBOLS-ONLY function. The parameter must be a list, but was ")	;print message with princ
			(prin1 alist)																	;print value of the parameter with prin1
			(terpri)																		;print newline
			 nil)																			;return nil
		((null alist)																		;check if list is null
			())																				;returns an empty list
		((symbolp (car alist))																;check if the first item in the list is a symbol
			(cons (car alist) (SYMBOLS-ONLY (cdr alist))))									;copy the element if its a symbol to a new list and repeat
		((not(symbolp (car alist)))															;if the item is not a symbol call the function SYMBOLS-ONLY
			(SYMBOLS-ONLY (cdr alist)))))

		
;This function takes two parameters and returns their value if they are equivalent.
;This works on numbers, strings and lists.
;If they are not the same, it print "NO match" and returns nil		
(defun MATCHP (list1 list2)
	(cond
		((not(equal list1 list2))															;check if the two parameters are not equal
			(princ "No Match")																;print message with princ
			(terpri)																		;print newline
			 nil)																			;return nil
		((equal list1 list2)																;check if both parameters are equl
			(setf *cons* list1)																;set variable cons equal to the list
			*cons*)))																		;prints the value of the variable