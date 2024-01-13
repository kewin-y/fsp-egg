package com.keviny.egg.controller;

import com.keviny.egg.gui.EggStage;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

public class EggController {
  private EggStage eggStage;
  private String currentScriptPath;

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
        SwingUtilities.invokeLater(
            new Runnable() {

              @Override
              public void run() {
                JFileChooser fileChooser = new JFileChooser();
                int ret = fileChooser.showOpenDialog(null);

                if (ret == JFileChooser.APPROVE_OPTION) {
                  System.out.println(fileChooser.getSelectedFile().getPath());
                }
              }
            });
      };
}
