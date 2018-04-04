Game class is responsible to run the game. It firstly asks the user to
choose whether to go first. If user inputs any response started by "y"
in keyboard, it will consider user would like to go first and then the 
human player will use Stone.RED. Otherwise, computer player, named as
zs will use Stone.RED and place stone first.
Each time a stone is placed, Myborad will check if the game is over, and 
if so, it will print out which color of stone is the winner and also
congratulations. Moreover, it will show how many captures each side get
during the game.

MyBoard is totally following the manual and the interface. Also, I wrote
unit test for it.

HumanPlayer class just asks human player to input the coordinate they 
hope to place the stone. The first stone should be at (9, 9) and the 
second stone should not be within 3 intersections of the center, if human
player chooses to go first.

ZeshenPlayer will firstly detect whether there are any small rows made
by the opposite and try to stop them. If not, it will then try to find
stones of itself, if any, and place stone around it. Otherwise, it will 
choose empty spot and place stone there randomly.

To run my code, just run Game class, and enter yes or no into keyborad
after it asking "Do you want to go first?".
Then, it will display the current borad after each placing. Also, if the 
user input coordinate is ilegal, which may be out of bound, not a integer,
not (9, 9) for the first move or within 3 intersections of the center for
the second move, it will remind player that is not legal and input again
until it gets legal moves.

