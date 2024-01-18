package com.keviny.egg.controller;

import com.keviny.egg.gui.EggStage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EggController {
  EggStage stage;

  public EggController() {
    stage = new EggStage(this);
    stage.displayGui();
  }

  /*
   * "Control" Functions
   * I refer to them as Functions because they inherit from a functional
   * interface.
   * This allows me treat them like objects
   */

  // {{{ Function that pauses/plays the user's script
  public OnClick handlePlay = () -> {
    stage.setDrawingScript(true);
  };

  // }}}

  // {{{ Function that stops the execution of the user's script
  public OnClick handleStop = () -> {
    stage.setDrawingScript(false);
  };
  // }}}

  // {{{ Function that displays a file picker for the user
  // Allows them to pick a script to play
  public OnClick handleLoadNew = () -> {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter fnef = new FileNameExtensionFilter("Egg Scripts (.egg)", "egg");
    fileChooser.setFileFilter(fnef);
    int ret = fileChooser.showOpenDialog(null);

    if (ret == JFileChooser.APPROVE_OPTION) {
      stage.updateScript(fileChooser.getSelectedFile());
    }
  };

  // }}}

}
