== How to Use ==

Egg is a program to make simple drawings through code.

- Create a file ending with ".egg".
- Write your Egg code in this file. This file is called a script. (See "Learn Egg" & "Examples")
- Save it somewhere you can find it, like on your desktop.
- Launch Egg.
- Load in the file by clicking the "Load New" button, and finding it through the file picker.
- Hit the play button the start drawing from the script, or the stop button to stop drawing the script.

== Learn Egg ==

pen_move x -> Moves the pen by "x" steps in its current direction
pen_rotate x -> Rotates the pen "x" degrees clockwise
pen_up -> Lifts the pen down, so it won't draw when it moves
pen_down -> Puts the pen down again, so it can draw when it moves
pen_color color_name -> Changes the pen's color to "color_name". See section below for valid colors:

red
orange
yellow
green
blue
purple 

== Examples ==

square.egg -> Draws a square
pen_move 50
pen_rotate rotate 90
pen_move 50
pen_rotate rotate 90
pen_move 50
pen_rotate rotate 90
pen_move 90

triangle.egg -> Draws a triangle 

pen_move 250
pen_rotate 120
pen_move 250
pen_rotate 120
pen_move 250

