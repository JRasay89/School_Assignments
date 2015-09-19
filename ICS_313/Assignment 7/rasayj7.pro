% Author: John Rasay  ICS313
% Date: 11/18/2012

%to run ,Type
%go1 for situation#1
%go2 for situation#2
%go3 for situation#3

% If the element is the head of the list, then it is in the list, and the tail is left
selectE(Element, [Element|Tail], Tail).

% If the two lists have the same head, check for more elements in the rest of the lists
selectE(Element, [Head|Tail1], [Head|Tail2]) :-
        selectE(Element, Tail1, Tail2).
        
% The empty list is a permutation of itself
permutationQ([],[]).

% List1 is a permutation of List2 if each element occurs in both lists
%    the same number of times
permutationQ(List, [Head|Tail]) :- selectE(Head, List, Rest),
                                  permutationQ(Rest, Tail).



%Situation#1
%You have five colored balls: 2 green, 2 blue, and 1 yellow
%All balls of the same color must be adjacent to each other.

go1 :-  permutationQ([green,green,blue,blue,yellow],[A,B,C,D,E]),
        ((A==green -> B==green);  %Balls of same color must be adjacent to each other.
         (A==blue -> B==blue);    %
         
         (B==blue -> C==blue);    %
         (B==green -> C==green);  %
         
         (C==blue -> D==blue);    %
         (C==green -> D==green);  %
         
         (D==blue -> E==blue);    %
         (D==green -> E==green)), %

         B\==yellow,              %Yellow can't be on the second position
         D\==yellow,              %Yellow can't be on the the fourth position
         printout1([A,B,C,D,E]).  %Print any solutions thats found.
         
%Prints the solutions.
printout1([A,B,C,D,E]) :-
          nl,
          write('The order of balls from left to right is: '), nl,
          write('A is '), write(A),nl,
          write('B is '), write(B),nl,
          write('C is '), write(C),nl,
          write('D is '), write(D),nl,
          write('E is '), write(E),nl.
          
%The number of solutions is 24.



%Situation#2
%You have six colored balls: 5 black and 1 white.
%There are no more than 3 black balls in a row.

go2 :- permutationQ([black,black,black,black,black,white],[A,B,C,D,E,F]),
       ((A==black -> B==black), (C==black), (E==black -> F==black); %You can only have three blakc balls in a row.
        (D==black -> E==black), (F==black), (A==black -> B==black)),%
        A\==white,  %A cannot be white.
        B\==white,  %B cannot be white.
        E\==white,  %E cannot be white.
        F\==white,  %F cannot be white.
        printout2([A,B,C,D,E,F]).      %Print out any solutions found.

%Prints any solutions found.
printout2([A,B,C,D,E,F]) :-
          nl,
          write('The order of balls from left to right is: '), nl,
          write('A is '), write(A),nl,
          write('B is '), write(B),nl,
          write('C is '), write(C),nl,
          write('D is '), write(D),nl,
          write('E is '), write(E),nl,
          write('F is '), write(F),nl.

%The number of solutions is 240


%Situation#3
%You have eight colored balls: 1 black, 2 white, 2 red, and 3 green.
%1.The balls in position 2 and 3 are not green.
%2.The balls in position 4 and 8 are the same color.
%3.The balls in position 1 and 7 are of different colors.
%4.There is a green ball to the left of every red ball.
%5.A white ball is neither first nor last.
%6.The balls in position 6 and 7 are not red.

go3 :- permutationQ([black,white,white,red,red,green,green,green],[A,B,C,D,E,F,G,H]),
       ((D==red -> H==red);    %Position 4 and 8 must be the same color
        (A==green -> B==red);  %There is a green colored ball to the left of every red ball.
        (G==green -> H==red);  %
        (D==green -> E==red)), %
        
        A\==G,    %Position 1 and 7 can't be the same color
        
        A\==white,  %The first and last position can't be white.
        H\==white,
        D\==white,  %Position 4 can't be white because green must be at position 4
                    %because red is at position 5 and there must be green to the left of red.
        D\==black,  %Position 4 can't be black there is only one black and position 4 and 8 has to be the same.
        H\==black,  %
        
        B\==green,  %Position 2 and 3 cannot be green.
        C\==green,
        F\==green,  %Position 6 cannot be green since position 7 cannot be red.
        
        C\==red,   %Position 3 and 4 cannot be red because position 2 and 3 cannot be green.
        D\==red,   %
        H\==red,   %Position 8 cannot be red since position 4 cannot be red and they must be the same color.
        F\==red,   %Position 6 and 7 cannot be red.
        G\==red,   %
        printout3([A,B,C,D,E,F,G,H]). Print any solutions found.

%Print situation#3's solutions.
printout3([A,B,C,D,E,F,G,H]) :-
          nl,
          write('The order of balls from left to right is: '), nl,
          write('A is '), write(A),nl,
          write('B is '), write(B),nl,
          write('C is '), write(C),nl,
          write('D is '), write(D),nl,
          write('E is '), write(E),nl,
          write('F is '), write(F),nl,
          write('G is '), write(G),nl,
          write('H is '), write(H),nl.
          
%The number of Solutions is 72