package com.keviny.egg.controller;

import com.keviny.egg.gui.EggStage;
import java.io.File;
import javax.swing.JFileChooser;

public class EggController {
  private File currentScript;
  EggStage eggStage;

  public EggController() {
    eggStage = new EggStage(this);
  }

  // Function that pauses/plays the user's script
  public OnClickFunction handlePausePlay =
      () -> {
        System.out.println("Pause/Play");
      };

  // Function that restarts the execution of the user's script
  public OnClickFunction handleRestart =
      () -> {
        System.out.println("Restart");
      };

  // Function that stops the execution of the user's script
  public OnClickFunction handleStop =
      () -> {
        System.out.println("Stop");
      };

  // Function that displays a file picker for the user
  // Allows them to pick a script to play
  public OnClickFunction handleLoadNew =
      () -> {
        JFileChooser fileChooser = new JFileChooser();
        int ret = fileChooser.showOpenDialog(null);

        if (ret == JFileChooser.APPROVE_OPTION) {
          currentScript = fileChooser.getSelectedFile();
          eggStage.setScriptIndicator(currentScript.getName());
        }
      };
}
