# ICS4U1 FSP - Egg (Easy Graphics)

- A simple engine used to create 2D drawings
    - Its primary function is to introduce programming to children/beginners
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

