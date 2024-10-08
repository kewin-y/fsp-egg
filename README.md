# ICS4U1 FSP - Egg (Easy Graphics)

- Interpreter built by following [_Crafting Interpreters_](https://craftinginterpreters.com/) by Robert Nystrom
- To run, make sure Maven and JDK (at least 21) are installed. Execute `mvn clean install` and `mvn exec:java`
    - This wasn't meant to be packaged; only to be ran in an IDE (It was for a high school course).  

```java
/*
 * === Name ===
 * Kevin Yu
 *
 * === Date ===
 * 2024-01-19
 *
 * === Course ===
 * ICS4U1
 *
 * === Title ===
 * Egg (Easy Graphics g)
 *
 * == Description ===
 * A way to draw simple images using code
 *
 * == Features ==
 * Pen moving
 * Pen rotating
 * Pen color
 * Controlling whether or not pen draws (Putting pen up or down)
 * Dynamic & reloadable scripts
 *
 * == Major Skills ==
 * Raylib -> Infinite Game loop, medium-level GUI design
 * Java Swing
 * Interpreter & language design
 *
 * === Concerns: ===
 * Does not work on musl due to glibc being required for JavaCPP bindings of Jaylib (This probably won't matter)
 * No text editor -> user must write their own script in notepad, or some other external software
 * User can spawn multiple help panels at a time -> might be a concern?
 * Has not been extensibly tested, however, all examples do work.
 * Errors when setting a script do not appear -> this should be rare enough that it won't matter
 *
 * == Notes ==
 * User can draw out of bounds and underneath some buttons: Mostly an intended feature to give users more freedom
 * */
```
