# Learn Egg

Egg's syntax is simple and easy to understand! Every action in egg is based on the Pen. The Pen is what draws for you in Egg. You can tell it how to move and draw.

## Pen Actions:

```Egg
pen_move x
pen_rotate x
pen_up
pen_down
pen_color color_name
```

Here is what each Pen Action does in detail:

| <div style="width:200px">Action</div> | Description                                                                                           | Example                                                     |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------- | ----------------------------------------------------------- |
| `pen_move x`                          | Moves the Pen `x` units in the direction it's currently facing. It will draw line if the Pen is down. | `pen_move 50` moves the Pen 50 steps.                       |
| `pen_rotate x`                        | Rotates the Pen `x` degrees clockwise. Rotating the Pen does not make it draw.                        | `pen_rotate 90` rotates the Pen 90 degrees.                 |
| `pen_up`                              | Lifts the Pen up. When the Pen moves while it's lifted up, it will not draw.                          | `pen_up`will lift the Pen up and make it stop drawing.      |
| `pen_down`                            | Puts the Pen back down so that it can draw again.                                                     | `pen_down` will put the Pen back and make allow it to draw. |
| `pen_color *color*`                   | Changes the Pen's color                                                                               | `pen_color blue` makes the Pen blue.                        |

## Colors:

Colors that you can use with `pen_color`:

- <span style="color:#000000">black</span>
- <span style="color:#E62937">red</span>
- <span style="color:#FFA100">orange</span>
- <span style="color:#FDF900">yellow</span>
- <span style="color:#00E430">green</span>
- <span style="color:#0079F1">blue</span>
- <span style="color:#C87AFF">purple</span>
