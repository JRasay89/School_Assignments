;***************************************************************
;* 			        Additions						           *
;*Added new location Kitchen, located east of living-room      *
;*Added new object knife, found in the kitchen                 *
;***************************************************************

(defconstant +ID+ "JOHN RASAY")
;This prints the student name, course-number and assignment-number.
(defun id(course-number assignment-number)
	(format t "Name: ~a~%Course: ICS~d~%Assignment # ~d~%" +ID+ course-number assignment-number))

(defparameter *nodes* '((living-room (you are in the living-room.
                            a wizard is snoring loudly on the couch.))
                        (garden (you are in a beautiful garden.
                            there is a well in front of you.))
                        (attic (you are in the attic.
                            there is a giant welding torch in the corner.))
						(kitchen(you are in the kitchen.
							there is an ogre cooking dinner.))))

(defun describe-location (location nodes)
   (cadr (assoc location nodes)))

(defparameter *edges* '((living-room (garden west door)  
                                     (attic upstairs ladder)
									 (kitchen east door))
                        (garden (living-room east door))
                        (attic (living-room downstairs ladder))
						(kitchen (living-room west door))))

(defun describe-path (edge)
  `(there is a ,(caddr edge) going ,(cadr edge) from here.))

(defun describe-paths (location edges)
  (apply #'append (mapcar #'describe-path (cdr (assoc location edges)))))

(defparameter *objects* '(whiskey bucket frog chain knife))

(defparameter *object-locations* '((whiskey living-room)
                                   (bucket living-room)
                                   (chain garden)
                                   (frog garden)
								   (knife kitchen)))

(defun objects-at (loc objs obj-loc)
   (labels ((is-at (obj)
              (eq (cadr (assoc obj obj-loc)) loc)))
       (remove-if-not #'is-at objs)))

(defun describe-objects (loc objs obj-loc)
   (labels ((describe-obj (obj)
                `(you see a ,obj on the floor.)))
      (apply #'append (mapcar #'describe-obj (objects-at loc objs obj-loc)))))

(defparameter *location* 'living-room)

(defun look ()
  (append (describe-location *location* *nodes*)
          (describe-paths *location* *edges*)
          (describe-objects *location* *objects* *object-locations*)))


(defun walk (direction)
  (labels ((correct-way (edge)
             (eq (cadr edge) direction)))
    (let ((next (find-if #'correct-way (cdr (assoc *location* *edges*)))))
      (if next 
          (progn (setf *location* (car next)) 
                 (look))
          '(you cannot go that way.)))))

(defun pickup (object)
  (cond ((member object (objects-at *location* *objects* *object-locations*))
         (push (list object 'body) *object-locations*)
         `(you are now carrying the ,object))
	  (t '(you cannot get that.))))


(defun inventory ()
  (cons 'items- (objects-at 'body *objects* *object-locations*)))

(defun have (object) 
    (member object (cdr (inventory))))