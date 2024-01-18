# ICS4U1 FSP - Egg (Easy Graphics)

- A way to draw simple images using code
- Interpreter built by following [*Crafting Interpreters*](https://craftinginterpreters.com/) by Robert Nystrom

expression     → literal
               | unary
               | binary
               | grouping ;

literal        → NUMBER; 
grouping       → "(" expression ")" ;
unary          →  "-"  expression ;
binary         → expression operator expression ;
operator       →> "+"  | "-"  | "*" | "/" ;

