# Lab7Optional

I have completed the following optional tasks:

- Notifying a player it's his turn by printing a message. He is notified to wait for its turn, the same way.

- Implementing a timekeeper thread. It count the game's running time in seconds and prints them on the screen when the game ends.
If the game exceeds a certain time limit, it forces the game to stop.

- Implementing the Player abstract class and its subclasses in the following way: 

RandomPlayer -> randomly extracts a token

SmartPlayer -> analyzes the boards' tokens and his own arithmetic progressions array before choosing a token, in order to extend the length of his arithmetic progression. If this is not possible, a random index will be assigned.

ManualPlayer -> scans the index from the keyboard. If the index is not properly chosen by the client (meaning the token doesn
t exist) a random index will be assigned 

*See the output7.jpg* photo to see how the program notifies the players and how it deals with an unproperly chosen index ( Jasmine's case, she is a manual player)
