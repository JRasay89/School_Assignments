;******************************************************************************
;									Assignment 5							  *
;Group Memebers																  *
;	Bryan Shito-Leong														  *
;	John Rasay																  *
;	Duane Leong																  *
;																			  *
;Given Task																	  *
;Task 1: Bryan Shito-Leong(new game action and others if needed				  *
;Task 2: John Rasay (helper action in game-repl and other task if needed)	  *
;Task 3: Duane Leong(macros and others if needed)							  *
;******************************************************************************

						;Wizzard Game Additions
;Added a hallway a kitchen a knife and the vial

;~Bryan
;Added new actions to connect together all parts 
;of the multi-part object from previous assignments 

;~John
;Edited the game-repl to ask the users if they need any help
;by typing help, h, ?. This prints available commands for the user to use.

;~Duane
;Added new-object new-location macros
;Tested macro functions added some comments to macro functions


; wizards_game part 1

;the nodes which are the rooms of the house that you can visit
;i added the kitchen and hall
(defparameter *nodes* '((living-room (you are in the living-room.
				a wizard is snoring loudly on the couch.))
			(kitchen (you are in the kitchen.
				you see the wizard's cauldron cooking on the stove.))
			(hall (you are in a hallway with pictures all over the wall.
				they appear to be of the wizard and various other people.))
			(garden (you are in a beautiful garden.
				there is a well in front of you.))
			(attic (you are in the attic.
				there is a giant welding torch in the corner.
				there is an open spellbook on a pedestal.))))

;describes the location you are in by using car/cdr combination on the list of nodes above
(defun describe-location (location nodes)
	(cadr (assoc location nodes)))

;the edges are the ways that the rooms are connected to other rooms of the house
;i added the kitchen/hall connections
(defparameter *edges* '((living-room	(garden west door)  
					(attic upstairs ladder)
					(hall east doorway))
			(hall		(living-room west doorway)
					(kitchen south doorway))
			(kitchen	(hall north doorway))
			(garden		(living-room east door))
			(attic		(living-room downstairs ladder))))

;describes a path
(defun describe-path (edge)
  `(there is a ,(caddr edge) going ,(cadr edge) from here.))

;describes all paths in a room
(defun describe-paths (location edges)
  (apply #'append (mapcar #'describe-path (cdr (assoc location edges)))))

;a list of all the objects that exist in the house
;i added the knife and vial in assignment 3
;for assignment 4 i added the wand and the herbs
(defparameter *objects* '(whiskey bucket frog chain knife vial wand herbs))

;the location of each object that is in the house
;i added the knife and vial
;for assignment 4 i added the wand and the herbs
(defparameter *object-locations* '(	(whiskey living-room)
					(bucket living-room)
					(wand living-room)
					(chain garden)
					(frog garden)
					(herbs garden)
					(knife kitchen)
					(vial kitchen)))

;gives a list of objects in a location. called by other functions
(defun objects-at (loc objs obj-loc)
   (labels ((is-at (obj)
              (eq (cadr (assoc obj obj-loc)) loc)))
       (remove-if-not #'is-at objs)))

;describes objects. called by other functions
(defun describe-objects (loc objs obj-loc)
   (labels ((describe-obj (obj)
                `(you see a ,obj on the floor.)))
      (apply #'append (mapcar #'describe-obj (objects-at loc objs obj-loc)))))

;starting position i think and changes as you call (walk)
(defparameter *location* 'living-room)

;gives you the description of where you are, what ways you have to go
;and what items are in your location
(defun look ()
  (append (describe-location *location* *nodes*)
          (describe-paths *location* *edges*)
          (describe-objects *location* *objects* *object-locations*)))

;lets you walk in a direction unless you try to go a way that doesnt exist
(defun walk (direction)
  (labels ((correct-way (edge)
             (eq (cadr edge) direction)))
    (let ((next (find-if #'correct-way (cdr (assoc *location* *edges*)))))
      (if next 
          (progn (setf *location* (car next)) 
                 (look))
          '(you cannot go that way.)))))

;picks up an object in the room you're in if it exists and if you dont
;already have it
(defun pickup (object)
  (cond ((member object (objects-at *location* *objects* *object-locations*))
         (push (list object 'body) *object-locations*)
         `(you are now carrying the ,object))
	  (t '(you cannot get that.))))

;displays whats in your inventory
(defun inventory ()
  (cons 'items- (objects-at 'body *objects* *object-locations*)))

;check to see if you have an object already
(defun have (object) 
    (member object (cdr (inventory))))



;  wizards_game part 2

;custom repl to allow you to interact without typing () around
;functions. also has a text formatter that prints things nicely



;Updated October 24, 2012 *added a help action which prints out available commands to the user if they choose to get help.
;This takes the input of the user.
;The input are the commands for the program to do.	
(defun game-repl ()
	(format t "~%:Type help, h, or ? for help:~%")
	(format t "> ")
	(let ((cmd (game-read)))
        (unless (eq (car cmd) 'quit)
			(if (or (eq (car cmd) '?) (eq (car cmd) 'help) (eq (car cmd) 'h))		;If user type help, h or ?  it will print the available commands.
				(progn
					(format t "~%:The following commands are available:~%")
					(format t "~%~23a Describes your loaction." "look")
					(format t "~%~23a Walk to a new location." "walk-value")
					(format t "~%~16a	Pickup an item in your current loaction." "pickup-value")
					(format t "~%~23a Open up your inventory." "inventory")
					(format t "~%~23a Quit the game.~%~%" "quit")
					(game-repl))
				(progn
					(game-print (game-eval cmd))
					(game-repl))))))


(defun game-read ()
	(let ((cmd (read-from-string (concatenate 'string "(" (read-line) ")"))))
		(flet ((quote-it (x)
			(list 'quote x)))
		(cons (car cmd) (mapcar #'quote-it (cdr cmd))))))

;list of allowed commands
(defparameter *allowed-commands* '(look walk pickup inventory))

;evaluates commands
(defun game-eval (sexp)
	(if (member (car sexp) *allowed-commands*)
		(eval sexp)
		'(i do not know that command.)))


;prints text nicely
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

;function to print things that need to be printed
(defun game-print (lst)
	(princ (coerce (tweak-text (coerce (string-trim "() " (prin1-to-string lst)) 'list) t nil) 'string))
	(fresh-line)
	(fresh-line))




;wizards_game part 3

;added on October 23, 2012
;copied from assignment page


;macro to create new game actions that have a function name and
;parameters
(defmacro game-action (command subj obj place &body body)
  `(progn (defun ,command (subject object)
            (if (and (eq *location* ',place)
                     (eq subject ',subj)
                     (eq object ',obj)
                     (have ',subj))
                ,@body
            '(i cant ,command like that.)))
          (pushnew ',command *allowed-commands*)))

;new parameter to keep track of the state of the chain/bucket
(defparameter *chain-welded* nil)

;function to weld the chain the the bucket using the 
;welding torch in the attic
(game-action weld chain bucket attic
             (if (and (have 'bucket) (not *chain-welded*))
                 (progn (setf *chain-welded* 't)
                        '(the chain is now securely welded to the bucket.))
               '(you do not have a bucket.)))

;parameter to keep track of the state of the bucket
(defparameter *bucket-filled* nil)

;function to fill the bucket with water if you are in the
;garden with the welded bucket
(game-action dunk bucket well garden
             (if *chain-welded* 
                 (progn (setf *bucket-filled* 't)
                        '(the bucket is now full of water))
               '(the water level is too low to reach.)))

;the action to finish the chain/bucket/wizard quest thing
(game-action splash bucket wizard living-room
             (cond ((not *bucket-filled*) '(the bucket has nothing in it.))
                   ((have 'frog) '(the wizard awakens and sees that you stole his frog. 
                                   he is so upset he banishes you to the 
                                   netherworlds- you lose! the end.))
                   (t '(the wizard awakens from his slumber and greets you warmly. 
                        he hands you the magic low-carb donut- you win! the end.))))


;these are the new functions for combining the items i added
;-herbs	(garden)
;-wand	(living-room)
;-knife	(kitchen)
;-vial	(kitchen)


;used the weld action as a template for my next actions
;basically copied the format for my new actions


;parameters to keep track of completed actions by user
(defparameter *herbs-cauldron* nil)	;added herbs to the cauldron
(defparameter *finished-potion* nil)	;stir potion with wand
(defparameter *filled-vial* nil)	;fill vial with potion
(defparameter *magic-potion* nil)	;use spell book on potion
(defparameter *magic-sword* nil)	;used potion on knife


;1st action in the steps for combining the new items
;add herbs from garden to the wizards cauldron must be done before stirring
(game-action cook herbs cauldron kitchen
		(if (and (have 'herbs) (not *herbs-cauldron*))
			(progn (setf *herbs-cauldron* 't)
				'(you put the herbs in the cauldron))
			'(that doesn't seem wise.)))

;2nd action
;stir the cauldron with the wand from the living room
(game-action stir wand cauldron kitchen
		(if (and *herbs-cauldron* (not *finished-potion*))
			(progn (setf *finished-potion* 't)
				'(you mix the cauldron with the wand and see the potion turn silver))
			'(looks like some ingredients are missing.)))

;3rd action
;puts some of the potion in the vial from the kitchen
;must have already added herbs and stirred
(game-action take-potion vial cauldron kitchen
		(if (and (have 'vial) (and *finished-potion* (not *filled-vial*)))
			(progn (setf *filled-vial* 't)
				'(you fill the vial with the potion))
			'(the potion doesn't seem done yet.)))

;4th action
;must go to attic after finishing the potion to do this
(game-action say-spell vial spellbook attic
		(if (and *filled-vial* (not *magic-potion*))
			(progn (setf *magic-potion* 't)
				'(the potion turns gold and emits a faint light))
			'(you say the words but nothing happens.)))

;5th action to finish my item
;must have knife from kitchen and used the spellbook
;on the finished potion in the vial to be able to do this
(game-action pour vial knife attic
		(if (and (and *magic-potion* (not *magic-sword*)) (have 'knife))
			(progn (setf *magic-sword* 't)
				'(theres a bright flash of light and you find a large gold and silver sword in your hands.
					now you can accomplish what you came here for "-" killing the wizard and stealing his powers.
					you win.))
			'(nothing happens.)))


;; Creates a new object in the location
;; Both paramemters need to be symbols and the location needs to exist before adding the object
;; obj object to be added
;; loc location the object is placed in
;; Ex. (new-obect 'bear 'room) would add BEAR to the location ROOM
(defmacro new-object (obj loc)
  `(if (and (symbolp ,obj) (symbolp ,loc))
		(if (not (member ,obj *objects*))
		    (if (assoc ,loc *nodes*)
		      (progn
		        (push ,obj *objects*)
		        (push (list ,obj ,loc) *object-locations*)
		      )
		      (cons ,loc '(is not a valid location))
		    )
		(cons ,obj '(already exists))
		)
	'(invalid parameters)
	)
)

;; Creates a new location and adds a description about the location
;; The loc needs to be a symbol while descr is a list
;; loc location to be created
;; descr description of the created location
;; Ex. (new-location 'room '(Empty room.)) creates a ROOM with the description EMPTY ROOM
(defmacro new-location (loc descr)
  `(if (and (symbolp ,loc) (listp ,descr))
    (if (not (assoc ,loc *nodes*))
      (progn
        (push (list ,loc ,descr) *nodes*)
        (push (list ,loc) *edges*)
      )
      (cons ,loc '(already exists))
    )
  )
)




;; Creates a path between the two locations
;; All parameters need to be lists
;; loc1 location to link
;; dir1 direction to loc2
;; type1 the object that links to loc2
;; loc2 location to link
;; dir2 direction to loc1
;; type2 the object that links to loc1
;; Ex. (new-path '(living-room) '(north) '(door) '(room) '(south) '(door))
;; Creates a north door in the living room which links to room which has a south door linking back to living room
;; Ex. (new-path '(living-room) '(north) '(door) '(room))
;; Creates a north door in the living room which links to a room you can't get out of
(defmacro new-path (loc1 dir1 type1 loc2 &optional (dir2 nil) (type2 nil))					;parameters loc = location, dir = direction, type = type of way ex: door, stairs.... ;made dir2 nil and type2 nil optional if making only one way path
	`(if (and (listp ,loc1) (listp ,dir1) (listp ,type1)									;checks if all arguments are a list
			  (listp ,loc2) (listp ,dir2) (listp ,type2))
		(if (and (assoc (car ,loc1) *nodes*) (assoc (car ,loc2) *nodes*))					 ;checks if the two locations exist
			(progn
				(if (member (car ,dir1) (mapcar #'cadr (cdr (assoc (car ,loc1) *edges*))))	;checks if the path given already exist
					(progn
						(princ "Path Already Exist")
						nil)
					(if (member (car ,dir2) (mapcar #'cadr (cdr (assoc (car ,loc2) *edges*))))	;checks if the path given already exist for location 2
						(progn
							(princ "Path Already Exist")
							nil)
						(progn
							(push  (append (,@loc2) (,@dir1) (,@type1)) (cdr (assoc (car ,loc1) *edges*))) 			;pushes a path in the first location to the second location
							(if (and(not (eq (car ,dir2) nil)) (not (eq (car ,type2) nil))) 						;check if its only a one way path or a two way path
								(push  (append (,@loc1) (,@dir2) (,@type2)) (cdr (assoc (car ,loc2) *edges*)))))							;pushes a path in the second location to the first location 
					)
				)
			)
			(princ "One or more of the given locations does not exist")	;prints a message if the given locations does not exist
		)
		(princ "One or more invalid arguments")	;prints a message if the parameters are not a list
	 )
)

;; adds a bedroom
(new-location 'bedroom '(This is a bedroom. You see a bed and an empty closet.))

;; adds enchanted-land
(new-location 'enchanted-land '(This is an enchanted-land. You are stuck here forever.))

;; creates a path from living-room to portal but not back
;; one way path example
(new-path '(living-room) '(north) '(portal) '(enchanted-land))

;; creates a path between the hall and bedroom
;; the door is north in the hall which leads to the bedroom
(new-path '(hall) '(north) '(door) '(bedroom) '(south) '(door))

;; adds a unicorn to the enchanted land
(new-object 'unicorn 'enchanted-land)

;; adds a teddy bear to the bedroom
(new-object 'teddy-bear 'bedroom)



