(load "Game-Parameters")
(load "Game-Function")
(load "Monster-Battle")

(defun new-game ()
	(show-intro)
	(game-repl)
	(show-end)
)

(defun show-intro ()
(format t "Welcome to the Dungeon Game~%")
(format t "You awoke in a mysteriou dark room. Not remembering what happend to you.~%")
(format t "You decided to find a way out.~%")
)

(defun show-end ()
(format t "You have made it out alive, good job!~%")
)
