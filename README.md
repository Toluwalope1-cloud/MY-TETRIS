# MY-TETRIS
I designed this game as a class project, together with a classmate.
We made this game using the principles of Object-orieneted programming in java. 
It follows the classic rules of tetris, where players must strategically rotate and place falling blocks of different shapes to create complete horizontal lines. These horizontal lines get cleared and increase the score of the player.

Features:
1. Object-oriented Programming: Using the idea of OOP, the game is structured into classes, eleven classes to be specific. The main classes are tetris display, tetris game, teris window, and tetris brick. The tetris brick class has 7 sub-classes, whch represent the seven possible shapes of falling bricks.
2. Interactive gameplay: Users/players interact with the game using various keys on the keyboard. This feature was infused using the key listener interface. Players can also reduce or increase the game board size by modifying the number of columns and/or rows in the tetris window class.
3. Scoring: The score of a player increases when one or more horizontal row of blocks is full. It clears this row and moves all rows above it down. Also, higher scores are given when multiple lines are cleared at once.
4. Game over detection: Just like the classic tetris game, this game ends when a stack of block(tetrominoes) reaches the top row, and no other can be added on it.

How to Play
1. Use the left and right arrow keys to move the falling tetromino horizontally.
2. Use the down arrow key to accelerate the falling speed of the tetromino.
3. Press the up arrow key to rotate the falling tetromino.
4. Pause the game using the space bar.
5. Clear the game board and restart using the "N" key.
6. Complete horizontal lines to clear them and earn points.
7. The game ends when the stack of tetrominoes reaches the top of the board.

Contributing

Contributions are welcome! If you have any ideas for improving the game or fixing bugs, feel free to open an issue or submit a pull request.

Credits

This Tetris game was created by Tolu Kuseju and Yvonne Ariri. It is based on the classic game developed by Alexey Pajitnov in 1984.
