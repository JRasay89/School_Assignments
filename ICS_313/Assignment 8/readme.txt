**Ancestors**
*This is read as *If X is a parent of Y, then X is an ancestor of Y*
ancestor(X, Y) :- parent(X, Y).

*This is read as *If Z is an ancestor of Y and X is a parent of Z, then X is an ancestor of Y*
ancestor(X, Y) :- parent(X, Z), ancestor(Z, Y).


Descendant is pretty much the opposite of Ancestors.
**Descendants**
This is read as *If Y is a parent of X, then X is a descendant of Y*
descendant(X, Y) :- parent(Y, X).

This is read as *If X is a descendant of Z and Y is a parent of Z, then X is a descendant of Y*
descendant(X, Y) :- parent(Y, Z), descendant(X, Z).



**Family**
This is read as * If Z is a parent of X and Z is a parent of Y and X is not Y, then X is a family of Y*
family(X, Y) :- parent(Z, Y), parent(Z, X), X \== Y.



***Results when running***
From the results of this program some queries will print out duplicates because i did not write it
to remove the duplicate answers.

For example when
family(X, modula2) is ran, it will print out some of its siblings twice because
modula2 has two parents and those two parents share some of the same child.