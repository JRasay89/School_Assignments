(defparameter *player-health* nil)
(defparameter *player-agility* nil)
(defparameter *player-strength* nil)
(defparameter *monsters* nil)
(defparameter *monster-builders* nil)
(defparameter *monster-num* 1)
(defparameter *run* 0)

;;Start for fighting game
(defun orc-battle (location node)
	(init-monsters)
	(init-player)
	(setq *run* 100)
	(game-loop)
	(when (player-run)
	(princ "You successfully ran away."))
	(when (player-dead)
	(set-player-dead)
	(princ "You have been killed. Game Over."))
	(when (monsters-dead)
	(set-monster-dead location node)
	(princ "Congratulations! You have vanquished your foe.")))

;;loops the main part of the fighting game
;;gives the basic information on player health/strength/agility
;;and gives information on the monster
(defun game-loop ()
  (unless (or (player-dead) (monsters-dead) (player-run))
	(show-player)
	(show-monsters)
	(player-attack)
	(fresh-line)
	(map 'list
		 (lambda(m)
		   (or (monster-dead m) (monster-attack m)))
		 *monsters*)
	(game-loop)))



;;tries to run away by generation a new random number
(defun try-run ()
  (princ "You try to run away...")
  (setq *run* (random 100))
)

;;run succeeds if player agility is > the random number generated
(defun player-run ()
	(>= *player-agility* *run*))

;;initializes the player
(defun init-player ()
	(setf *player-health* 30)
	(setf *player-agility* 20)
	(setf *player-strength* 1))

;;player is dead when their health is less than or equal to 0
(defun player-dead ()
	(<= *player-health* 0))

;;Shows the information about he player
(defun show-player ()
	(fresh-line)
	(princ "Health: ")
	(princ *player-health*)
	(fresh-line)
	(princ "Agility: ")
	(princ *player-agility*)
	(fresh-line)
	(princ "Strength: ")
	(princ *player-strength*))


;;attack function
(defun player-attack ()
	(fresh-line)
	(princ "Fight or Run: [a]ttack [r]un:")
	(case (read)
	(a (monster-hit (pick-monster) ;;for attack
					(+ 2 (randval (ash *player-strength* -1)))))
	(r (try-run)) ;;for running
	(otherwise (princ "error not valid input.")))) ;; errors if its neither a or r

;;random number for player strength where n is the maximum value that
;;can be returned from this random number generator
(defun randval (n)
  (1+ (random (max 1 n))))

(defun random-monster ()
  (let ((m (aref *monsters* (random (length *monsters*)))))
	(if (monster-dead m)
		(random-monster)
	  m)))

(defun pick-monster ()
	(fresh-line)
	;(princ "Monster #:")
	;;chanred ((x (read))) -> ((x '1))
	(let ((x '1))
	(if (not (and (integerp x) (>= x 1) (<= x *monster-num*)))
		(progn (princ "That is not a valid monster number.")
			 (pick-monster))
	  (let ((m (aref *monsters* (1- x))))
		(if (monster-dead m)
			(progn (princ "That monster is alread dead.")
				(pick-monster))
		  m)))))


;; 1 is slime
;; 2 is hydra
;; 3 is skeleton-warrior
;; 4 is orc
(defun init-monsters ()
  (setf *monsters*
		(map 'vector
			(lambda (x)
			(funcall (nth 3
							 *monster-builders*)))
			 (make-array *monster-num*))))


(defun monster-dead (m)
  (<= (monster-health m) 0))

(defun monsters-dead ()
  (every #'monster-dead *monsters*))

(defun show-monsters ()
  (fresh-line)
  (princ "Your foe:")
  (let ((x 0))
	(map 'list
		 (lambda (m)
		   (fresh-line)
		   (if (monster-dead m)
			   (princ "**dead**")
			 (progn (princ "(Health = ")
					(princ (monster-health m))
					(princ ") ")
					(monster-show m))))
		 *monsters*)))

(defstruct monster (health (randval 20)))

(defmethod monster-hit (m x)
  (decf (monster-health m) x)
  (if (monster-dead m)
	  (progn (princ "You killed the ")
			 (princ (type-of m))
			 (princ "! "))
	(progn (princ "You hit the ")
		   (princ (type-of m))
		   (princ ", knocking off ")
		   (princ x)
		   (princ " health points! "))))

(defmethod monster-show (m)
  (princ "A fierce ")
  (princ (type-of m)))

(defmethod monster-attack (m))

;MONSTER ORC!
(defstruct (orc (:include monster)) (club-level (randval 8)))

(push #'make-orc *monster-builders*)

(defmethod monster-show ((m orc))
  (princ "A wicked orc with a level ")
  (princ (orc-club-level m))
  (princ " club"))

(defmethod monster-attack ((m orc))
  (let ((x (randval (orc-club-level m))))
	(princ "An orc swings his club at you and knocks off ")
	(princ x)
	(princ " of your health points. ")
	(decf *player-health* x)))

;NEW Monster SKELETON WARRIOR!!!!!!!!!
(defstruct (skeleton-warrior (:include monster)) (sword-level (randval 8)))

(push #'make-skeleton-warrior *monster-builders*)

(defmethod monster-show ((m skeleton-warrior))
  (princ "A skeleton warrior with a level ")
  (princ (skeleton-warrior-sword-level m))
  (princ " sword"))

(defmethod monster-attack ((m skeleton-warrior))
  (let ((x (randval (skeleton-warrior-sword-level m))))
	(princ "An skeleton warrior swings his sword at you and knocks off ")
	(princ x)
	(princ " of your health points. ")
	(decf *player-health* x)))
;MONSTER HYDRA
(defstruct (hydra (:include monster)))

(push #'make-hydra *monster-builders*)

(defmethod monster-show ((m hydra))
  (princ "A malicious hydra with ")
  (princ (monster-health m))
  (princ " heads."))

(defmethod monster-hit ((m hydra) x)
  (decf (monster-health m) x)
  (if (monster-dead m)
	  (princ "The corpse of the fully decapitated and decapacitated hydra
falls to the floor!")
	(progn (princ "You lop off ")
		   (princ x)
		   (princ " of the hydra's heads! "))))

(defmethod monster-attack ((m hydra))
  (let ((x (randval (ash (monster-health m) -1))))
	(princ "A hydra attacks you with ")
	(princ x)
	(princ " of its heads! It also grows back one more head! ")
	(incf (monster-health m))
	(decf *player-health* x)))

(defstruct (slime-mold (:include monster)) (sliminess (randval 5)))

(push #'make-slime-mold *monster-builders*)

(defmethod monster-show ((m slime-mold))
  (princ "A slime mold with a sliminess of ")
  (princ (slime-mold-sliminess m)))

(defmethod monster-attack ((m slime-mold))
  (let ((x (randval (slime-mold-sliminess m))))
	(princ "A slime mold wraps around your legs and decreases your agility
by ")
	(princ x)
	(princ "! ")
	(decf *player-agility* x)
	(when (zerop (random 2))
	  (princ "It also squirts in your face, taking away a health point! ")
	  (decf *player-health*))))

(defstruct (brigand (:include monster)))

(push #'make-brigand *monster-builders*)

(defmethod monster-attack ((m brigand))
  (let ((x (max *player-health* *player-agility* *player-strength*)))
	(cond ((= x *player-health*)
		   (princ "A brigand hits you with his slingshot, taking off 2 health points! ")
		   (decf *player-health* 2))
		  ((= x *player-agility*)
		   (princ "A brigand catches your leg with his whip, taking off 2
agility points! ")
		   (decf *player-agility* 2))
		  ((= x *player-strength*)
		   (princ "A brigand cuts your arm with his whip, taking off 2 strength points! ")
		   (decf *player-strength* 2)))))

