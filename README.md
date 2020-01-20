# WasteSortApp
AUCSC 320 Winter 2020 An App for Learning and Beyond for a Waste Sort Game

Developers : Jared Matson, Joseph Menezes, Harshil Vyas

This project was created to tackle the problem of waste contamination within Augustana. Augustana campus has
had a waste contamination problem for quite some time now and it is our goal to try and educate students and faculty
to potentially reduce these contamination levels.

To achieve this, we decided to implement a game aspect for students, and a "waste guide" for faculty. This waste guide allows for users
to enter what item they currently want to dispose of, and the application will search through a database in hopes of letting
the user know where the item should be placed.

Known Bugs: 
1.  There is an issue if the user turns off their display while in the middle of the game. If the user does turn off their display, the timer bar at the top
of the game will restart, but the sound effects that occur at specific times in the game will not restart.
2.  Another issue we found with the display being turned off is volume. The volume will turn back on when the display is turned back on, but the volume symbol at the top right
indicates that the volume is disabled.
3.  Another small issue within our game is with specific objects that  spawn in the middle of the screen. These objects are sometimes
shifted slightly to the left when the image being loaded in is small
4.  Our last known bug potentially happens with user that have enabled developer mode. Since our progress bar at the top of the game
is considered an animation, users can go into developer settings and make this animation run slowly. This effectivly causes users to have a game that
lasts much longer than normal which they can exploit to get very high scores.
