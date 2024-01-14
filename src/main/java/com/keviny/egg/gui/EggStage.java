package com.keviny.egg.gui;

import com.keviny.egg.controller.OnClickFunction;
import com.raylib.Jaylib;
import com.raylib.Raylib;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;

public class EggStage { // Some constants for the GUI components
  private static final int SCREEN_WIDTH = 1280;
  private static final int SCREEN_HEIGHT = 720;
  private static final int BUTTON_Y_BOT = 640;
  private static final int FONT_SIZE = 16;

  private ArrayList<EggButton> buttons = new ArrayList<>();

  private File currentScript;
  private String scriptIndicator = "[No Script Selected]";
  private int indicatorXPos;

  // {{{ Function that pauses/plays the user's script
  public OnClickFunction handlePausePlay =
      () -> {
        System.out.println("Pause/Play");
      };
  // }}}

  // {{{ Function that restarts the execution of the user's script
  public OnClickFunction handleRestart =
      () -> {
        System.out.println("Restart");
      };
  // }}}

  // {{{ Function that stops the execution of the user's script
  public OnClickFunction handleStop =
      () -> {
        System.out.println("Stop");
      };
  // }}}

  // {{{ Function that displays a file picker for the user
  // Allows them to pick a script to play
  public OnClickFunction handleLoadNew =
      () -> {
        JFileChooser fileChooser = new JFileChooser();
        int ret = fileChooser.showOpenDialog(null);

        if (ret == JFileChooser.APPROVE_OPTION) {
          currentScript = fileChooser.getSelectedFile();

          scriptIndicator = String.format("[%s]", currentScript.getName());
          indicatorXPos = getCoordinateToCenter(scriptIndicator);
        }
      };

  // }}}

  // {{{ Creates the components of the stage
  // - Buttons
  private void initStageComponents() {
    // Create the buttons and attach their functions
    EggButton pausePlayButton = new EggButton("assets/sprites/play.png", 576, BUTTON_Y_BOT);
    pausePlayButton.attachFunction(handlePausePlay);

    EggButton restartButton = new EggButton("assets/sprites/restart.png", 624, BUTTON_Y_BOT);
    restartButton.attachFunction(handleRestart);

    EggButton stopButton = new EggButton("assets/sprites/stop.png", 672, BUTTON_Y_BOT);
    stopButton.attachFunction(handleStop);

    EggButton loadNewButton = new EggButton("assets/sprites/load_new.png", 581, 40);
    loadNewButton.attachFunction(handleLoadNew);

    buttons.add(pausePlayButton);
    buttons.add(restartButton);
    buttons.add(stopButton);
    buttons.add(loadNewButton);
  }
  // }}}

  // Determines the proper x coordinate to center some text
  private int getCoordinateToCenter(String text) {
    return SCREEN_WIDTH / 2 - Jaylib.MeasureText(text, FONT_SIZE) / 2;
  }

  // The constructor which runs the main game loop
  // All the displaying of graphics happens here
  public EggStage() {
    Raylib.Vector2 mousePosition = new Jaylib.Vector2();

    /*
     * Initialization
     */
    Jaylib.SetConfigFlags(Jaylib.FLAG_WINDOW_UNDECORATED);
    Jaylib.InitWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "Egg - Learn to Code");
    boolean exitWindow = false;
    Jaylib.SetTargetFPS(60);

    /*
     * Game Objects
     */
    initStageComponents();
    indicatorXPos = getCoordinateToCenter(scriptIndicator);
    // Selected]
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

      Jaylib.ClearBackground(Jaylib.RAYWHITE);

      // Window decoration
      exitWindow =
          Jaylib.GuiWindowBox(new Jaylib.Rectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT), "#198# Egg");

      // Draw buttons
      for (EggButton b : buttons) {
        Jaylib.DrawTextureV(
            b.getTexture(),
            new Jaylib.Vector2().x(b.getBounds().x()).y(b.getBounds().y()),
            b.getCurrentColor());
      }

      Jaylib.DrawText(scriptIndicator, indicatorXPos, 60, FONT_SIZE, Jaylib.GRAY);

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
