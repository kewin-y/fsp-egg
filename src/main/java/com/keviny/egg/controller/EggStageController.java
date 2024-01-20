package com.keviny.egg.controller;

import com.keviny.egg.gui.EggStage;
import com.keviny.egg.gui.InfoFrame;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

// Holds functions related to buttons
public class EggStageController {
  EggStage stage;

  public EggStageController(EggStage stage) {
    this.stage = stage;
  }

  /*
   * "Control" Functions
   * I refer to them as Functions because they inherit from a functional
   * interface.
   * This allows me treat them like objects
   */

  /*
   * Instruction handling:
   * Cannot pass in parameters since lambda function has to match it's
   * signature
   * (I defined the functional interface to have no parameters for simplicity)
   */

  public OnClick handleHowTo = () -> new InfoFrame("How-To", InfoFrame.InfoType.HOW_TO);
  public OnClick handleLearn = () -> new InfoFrame("Learn", InfoFrame.InfoType.LEARN);
  public OnClick handleExamples = () -> new InfoFrame("Examples", InfoFrame.InfoType.EXAMPLES);

  public OnClick handleStop = () -> stage.getDrawing().setShouldDraw(false);

  // {{{ Function that pauses/plays the user's script
  public OnClick handlePlay =
      () -> {
        // Refresh so I don't have to reload it again
        File sc = stage.getDrawing().getScript();
        stage.updateScript(sc);
        stage.getDrawing().setShouldDraw(true);
      };

  // }}}

  // {{{ Function that displays a file picker for the user
  // Allows them to pick a script to play
  public OnClick handleLoadNew =
      () -> {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Egg Scripts (.egg)", "egg");
        fileChooser.setFileFilter(fnef);
        int ret = fileChooser.showOpenDialog(null);

        if (ret == JFileChooser.APPROVE_OPTION) {
          stage.updateScript(fileChooser.getSelectedFile());
          stage.getDrawing().setShouldDraw(false);
        }
      };

  // }}}

}
