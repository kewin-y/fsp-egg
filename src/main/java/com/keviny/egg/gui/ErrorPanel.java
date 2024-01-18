package com.keviny.egg.gui;

import com.keviny.egg.interpreter.EggError;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ErrorPanel extends JOptionPane {

  // This class contains all the text GUi for the error panel
  private class Info extends JPanel {
    private List<EggError> errors;

    //
    public Info(List<EggError> errors) {
      this.errors = errors;

      /*
       * Setting the layout to a top-aligned GridBagLayout
       * The code to top-align the layout is from
       *
       * https://stackoverflow.com/questions/23951882/how-to-align-the-elements-to-the
       * -top-in-a-gridbaglayout
       * I've used this code in my previous project
       */

      GridBagLayout gbl = new GridBagLayout();
      setLayout(gbl);
      gbl.columnWidths = new int[] {0, 0, 0, 0};
      gbl.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
      gbl.columnWeights = new double[] {0.0, 0.0, 1.0, Double.MIN_VALUE};
      gbl.rowWeights =
          new double[] {
            0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE
          };

      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(10, 10, 10, 10);
      gbc.gridx = 0;
      gbc.fill = GridBagConstraints.HORIZONTAL;

      setLayout(gbl);
      String errorText = "";
      for (EggError error : errors) {
        errorText += error.toString() + "\n";
        System.out.println(error);
      }
      JTextArea errorTextArea = new JTextArea(errorText);

      errorTextArea.setColumns(43);
      errorTextArea.setLineWrap(true);
      errorTextArea.setWrapStyleWord(true);
      // GUIUtils.setFontRenderingHints(nameTextArea);
      gbc.gridy = 0;
      add(errorTextArea, gbc);
    }
  }

  private final List<EggError> errors;
  private final String msg;

  public ErrorPanel(List<EggError> errors, String msg) {
    this.errors = errors;
    this.msg = msg;
  }

  public void show() {
    JScrollPane scrollableInfo =
        new JScrollPane(
            new Info(errors),
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    int height = errors.size() * 20;
    if (height > 500) height = 500;
    scrollableInfo.setPreferredSize(new Dimension(500, height));
    scrollableInfo.getVerticalScrollBar().setUnitIncrement(20);
    showMessageDialog(null, scrollableInfo, msg, 1);
  }
}
