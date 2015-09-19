% Author:John Rasay
% Date: 11/28/2012

%New Language-------------------------------------------------------------------
newLanguage(fortranI).
newLanguage(cobol).
newLanguage(algol58).
newLanguage(bcpl).
newLanguage(lisp).
newLanguage(shellLangauages).

%Extra Credit
newLanguage(erlang).
newLanguage(javaScript).
newLanguage(linda).
newLanguage(occam).
newLanguage(sisal).
newLanguage(postScript).

%Programming Languages----------------------------------------------------------
programmingLanguage(ada).
programmingLanguage(ada95).
programmingLanguage(ada2005).
programmingLanguage(algol58).
programmingLanguage(algol60).
programmingLanguage(algolW).
programmingLanguage(algol68).
programmingLanguage(basic).
programmingLanguage(bcpl).
programmingLanguage(caml).
programmingLanguage(cedar).
programmingLanguage(clu).
programmingLanguage(c).
programmingLanguage(c90).
programmingLanguage(c99).
programmingLanguage(cpp).
programmingLanguage(cSharp).
programmingLanguage(cSharp2).
programmingLanguage(cSharp3).
programmingLanguage(clos).
programmingLanguage(cobol).
programmingLanguage(commonLisp).
programmingLanguage(eiffel).
programmingLanguage(euclid).
programmingLanguage(fortranI).
programmingLanguage(fortranII).
programmingLanguage(fortranIV).
programmingLanguage(fortran77).
programmingLanguage(fortran90).
programmingLanguage(fortran95).
programmingLanguage(fortran2003).
programmingLanguage(fortran2008).
programmingLanguage(fSharp).
programmingLanguage(haskell).
programmingLanguage(hpf).
programmingLanguage(java).
programmingLanguage(java5).
programmingLanguage(lisp).
programmingLanguage(mesa).
programmingLanguage(miranda).
programmingLanguage(ml).
programmingLanguage(modula2).
programmingLanguage(modula3).
programmingLanguage(oberon).
programmingLanguage(oCaml).
programmingLanguage(pascal).
programmingLanguage(perl).
programmingLanguage(php).
programmingLanguage(pli).
programmingLanguage(prolog).
programmingLanguage(python).
programmingLanguage(ruby).
programmingLanguage(scheme).
programmingLanguage(shellLanguages).
programmingLanguage(simula).
programmingLanguage(simula67).
programmingLanguage(snobol).
programmingLanguage(smalltalk80).
programmingLanguage(visualBasic).

%Extra Credit
programmingLanguage(erlang).
programmingLanguage(icon).
programmingLanguage(javaScript).
programmingLanguage(linda).
programmingLanguage(logo).
programmingLanguage(objectiveC).
programmingLanguage(occam).
programmingLanguage(postScript).
programmingLanguage(sisal).
programmingLanguage(vbScript).

%Language Type------------------------------------------------------------------
languageType(ada, objectOriented).   /* ada is an objectOriented languageType */
languageType(ada95, objectOriented).   /* " " */
languageType(ada2005, objectOriented).
languageType(algol58, imperative).
languageType(algol60, imperative).
languageType(algolW, imperative).
languageType(algol68, imperative).
languageType(basic, imperative).
languageType(bcpl, imperative).
languageType(caml, functional).
languageType(cedar, imperative).
languageType(clu, imperative).
languageType(c, imperative).
languageType(c90, imperative).
languageType(c99, imperative).
languageType(cpp, objectOriented).
languageType(cSharp, objectOriented).
languageType(cSharp2, objectOriented).
languageType(cSharp3, objectOriented).
languageType(clos, objectOriented).
languageType(cobol, imperative).
languageType(commonLisp, functional).
languageType(eiffel, objectOriented).
languageType(euclid, imperative).
languageType(fortranI, imperative).
languageType(fortranII, imperative).
languageType(fortranIV, imperative).
languageType(fortran77, imperative).
languageType(fortran90, imperative).
languageType(fortran95, imperative).
languageType(fortran2003, imperative).
languageType(fortran2008, imperative).
languageType(fSharp, objectOriented).
languageType(haskell, functional).
languageType(hpf, imperative).
languageType(java, objectOriented).
languageType(java5, objectOriented).
languageType(lisp, functional).
languageType(mesa, imperative).
languageType(miranda, functional).
languageType(ml, functional).
languageType(modula2, imperative).
languageType(modula3, objectOriented).
languageType(oberon, objectOriented).
languageType(oCaml, objectOriented).
languageType(pascal, imperative).
languageType(perl, scripting).
languageType(php, scripting).
languageType(pli, imperative).
languageType(prolog, logical).
languageType(python, objectOriented).
languageType(ruby, objectOriented).
languageType(scheme, functional).
languageType(shellLanguages, scripting).
languageType(simula, objectOriented).
languageType(simula67, objectOriented).
languageType(snobol, database).
languageType(smalltalk80, objectOriented).
languageType(visualBasic, imperative).

%Extra Credit
languageType(erlang, functional).
languageType(icon, database).
languageType(javaScript, scripting).
languageType(linda, ??).
languageType(logo, functional).
languageType(objectiveC, objectOriented).
languageType(occam, concurrent).
languageType(postScript, scripting).
languageType(sisal, functional).
languageType(vbScript, scripting).

%Parents------------------------------------------------------------------------
parent(fortranI, fortranII).  /* fortranI is the parent of fortranII */
parent(fortranII, fortranIV).
parent(fortranII, algol60).
parent(fortranIV, fortran77).
parent(fortranIV, basic).
parent(fortranIV, pli).
parent(fortran77, fortran90).
parent(fortran90, hpf).
parent(fortran90, fortran95).
parent(hpf, fortran95).
parent(fortran95, fortran2003).
parent(fortran2003, fortran2008).
parent(basic, visualBasic).
parent(cobol, pli).
parent(euclid, mesa).
parent(mesa, cedar).
parent(algol58, algol60).
parent(algol60, algolW).
parent(algol60, pli).
parent(algol60, algol68).
parent(algol60, simula).
parent(algolW, pascal).
parent(pascal, modula2).
parent(pascal, euclid).
parent(pascal, ada).
parent(pascal, clu).
parent(pascal, ml).
parent(modula2, oberon).
parent(modula2, modula3).
parent(algol68, c).
parent(ada, ada95).
parent(ada, cpp).
parent(ada95, ada2005).
parent(bcpl, c).
parent(c, c90).
parent(c, cpp).
parent(c, perl).
parent(c90, c99).
parent(cpp, java).
parent(cpp, cSharp).
parent(java, java5).
parent(java, cSharp).
parent(java5, java6).
parent(simula, simula67).
parent(simula67, smalltalk80).
parent(simula67, modula2).
parent(simula67, euclid).
parent(simula67, ada).
parent(simula67, clu).
parent(smalltalk80, cpp).
parent(smalltalk80, eiffel).
parent(smalltalk80, clos).
parent(eiffel, ruby).
parent(lisp, scheme).
parent(lisp, ml).
parent(scheme, commonLisp).
parent(scheme, python).
parent(commonLisp, clos).
parent(ml, miranda).
parent(ml, caml).
parent(miranda, haskell).
parent(caml, oCaml).
parent(oCaml, fSharp).
parent(shellLanguages, perl).
parent(perl, php).

%Extra Credit
parent(snobol, icon).
parent(lisp, logo).
parent(c, objectiveC).
parent(visualBasic, vbScript).

%EXTRA CREDIT
%Designers and full names-------------------------------------------------------
designer(ada, ichbiah).    /*ada's designer is ichbiah*/
designer(basic, kemeny).
designer(c, ritchie).
designer(cpp, stroustrup).
designer(cSharp, hejlsberg).
designer(caml, leroy).
designer(cobol, hopper).
designer(fortran, backus).
designer(haskell, jones).
designer(icon, griswold).
designer(java, gosling).
designer(javaScript, eich).
designer(lisp, mccarthy).
designer(ml, milner).
designer(miranda, turner).
designer(modula, wirth).
designer(pascal, wirth).
designer(perl, wall).
designer(php, lerdorf).
designer(python, rossum).
designer(ruby, matsumoto).
designer(sisal, mcgraw).
designer(scheme, steele).
designer(visualBasic, cooper).

fullname(ichbiah, 'Jean Ichbiah'). /*ichbiah's fullname is Jean Ichbiah*/
fullname(kemeny, 'John George Kemeny').
fullname(ritchie, 'Dennis M. Ritchie').
fullname(cpp, 'Bjarne Stroustrup').
fullname(hejlsberg, 'Anders Hejlsberg').
fullname(leroy, 'Xavier Leroy').
fullname(hopper, 'Grace Hopper').
fullname(backus, 'John Backus').
fullname(jones, 'Simon Peyton Jones').
fullname(griswold, 'Ralph Griswold').
fullname(gosling, 'James Gosling').
fullname(eich, 'Brendan Eich').
fullname(mccarthy, 'John McCarthy').
fullname(milner, 'Robin Milner').
fullname(turner, 'David Turner').
fullname(wirth, 'Niklaus Wirth').
fullname(wirth, 'Niklaus Wirth').
fullname(wall, 'Larry Wall').
fullname(lerdorf, 'Rasmus Lerdorf').
fullname(rossum, 'Guido Van Russum').
fullname(matsumoto, 'Yukihiro Matsumoto').
fullname(mcgraw, 'James McGraw').
fullname(steele, 'Guy L. Steele Jr.').
fullname(cooper, 'Alan Cooper').


%Ancestors
/* If X is a parent of Y, then X is an ancestor of Y*/
ancestor(X, Y) :- parent(X, Y).
/* If Z is an ancestor of Y and X is a parent of Z, then X is an ancestor of Y*/
ancestor(X, Y) :- parent(X, Z), ancestor(Z, Y).

%Descendants
/*If Y is a parent of X, then X is a descendant of Y*/
descendant(X, Y) :- parent(Y, X).
/* If X is a descendant of Z and Y is a parent of Z, then X is a descendant of Y */
descendant(X, Y) :- parent(Y, Z), descendant(X, Z).

%Family
/* If Z is a parent of X and Z is a parent of Y and X is not Y, then X is a family of Y */
family(X, Y) :- parent(Z, Y), parent(Z, X), X \== Y.