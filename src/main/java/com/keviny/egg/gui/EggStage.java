package com.keviny.egg.gui;

import com.raylib.Jaylib;
import com.raylib.Raylib;

public class EggStage {
  private final int SCREEN_WIDTH = 1280;
  private final int SCREEN_HEIGHT = 720;

  public EggStage() {
    Raylib.Vector2 mousePosition = new Raylib.Vector2();
    // Initialization
    // ---------------------------------------------------------------------------------------

    Jaylib.SetConfigFlags(Jaylib.FLAG_WINDOW_UNDECORATED);
    Jaylib.InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Egg - Easy Graphics");

    boolean exitWindow = false;

    Jaylib.SetTargetFPS(60);
    // --------------------------------------------------------------------------------------

    // Main game loop
    while (!exitWindow && !Jaylib.WindowShouldClose()) // Detect window close button or ESC key
    {
      // Update
      // ----------------------------------------------------------------------------------
      mousePosition = Jaylib.GetMousePosition();
      // Draw
      // ----------------------------------------------------------------------------------
      Jaylib.BeginDrawing();

      Jaylib.ClearBackground(Jaylib.WHITE);

      exitWindow =
          Jaylib.GuiWindowBox(
              new Jaylib.Rectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT), "#198# Egg");

      Jaylib.DrawText(
          ("Mouse Position: [" + mousePosition.x() + ", " + mousePosition.y() + "]"),
          10,
          40,
          10,
          Jaylib.DARKGRAY);

      Jaylib.EndDrawing();
      // ----------------------------------------------------------------------------------
    }

    // De-Initialization
    // --------------------------------------------------------------------------------------
    Jaylib.CloseWindow(); // Close window and OpenGL context
    // --------------------------------------------------------------------------------------
  }
}
