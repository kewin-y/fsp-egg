package com.keviny.egg.gui;

import com.keviny.egg.controller.EggController;
import com.raylib.Jaylib;
import com.raylib.Raylib;
import java.util.ArrayList;

public class EggStage {
  // Some constants for the GUI components
  private final int SCREEN_WIDTH = 1280;
  private final int SCREEN_HEIGHT = 720;
  private final int BUTTON_Y_BOT = 640;

  private final EggController controller;

  public EggStage(EggController controller) {
    this.controller = controller;

    Raylib.Vector2 mousePosition = new Jaylib.Vector2();
    /*
     * Initialization
     */

    Jaylib.SetConfigFlags(Jaylib.FLAG_WINDOW_UNDECORATED);
    Jaylib.InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Egg - Easy Graphics");
    boolean exitWindow = false;
    Jaylib.SetTargetFPS(60);

    /*
     * Game Objects
     */

    EggButton pausePlayButton = new EggButton("assets/sprites/play.png", 576, BUTTON_Y_BOT);
    pausePlayButton.attachFunction(controller.handlePausePlay);

    EggButton restartButton = new EggButton("assets/sprites/restart.png", 624, BUTTON_Y_BOT);
    restartButton.attachFunction(controller.handleRestart);

    EggButton stopButton = new EggButton("assets/sprites/stop.png", 672, BUTTON_Y_BOT);
    stopButton.attachFunction(controller.handleStop);

    ArrayList<EggButton> buttons = new ArrayList<>();
    buttons.add(pausePlayButton);
    buttons.add(restartButton);
    buttons.add(stopButton);

    // Main game loop
    while (!exitWindow && !Jaylib.WindowShouldClose()) // Detect window close button or ESC key
    {
      /*
       * Update
       */
      mousePosition = Jaylib.GetMousePosition();
      
      // Poll buttons
      for (EggButton b : buttons) {
        b.poll(mousePosition);
      }
      /*
       * Draw
       */
      Jaylib.BeginDrawing();

      Jaylib.ClearBackground(Jaylib.WHITE);

      // Window decoration
      exitWindow =
          Jaylib.GuiWindowBox(new Jaylib.Rectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT), "#198# Egg");

      // Mouse pos debugging
      Jaylib.DrawText(
          ("Mouse Position: [" + mousePosition.x() + ", " + mousePosition.y() + "]"),
          10,
          40,
          10,
          Jaylib.DARKGRAY);

      // Draw buttons
      for (EggButton b : buttons) {
        Jaylib.DrawTextureV(
            b.getTexture(),
            new Jaylib.Vector2().x(b.getBounds().x()).y(b.getBounds().y()),
            b.getCurrentColor());
      }

      /*
       * End Drawing
       */
      Jaylib.EndDrawing();
    }

    /*
     * De-Initialization
     */
    Jaylib.CloseWindow(); // Close window and OpenGL context
    // Don't have to worry abt the resource leak warnings
    // (I hope)
  }
}
