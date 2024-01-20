package com.keviny.egg.gui;

import com.keviny.egg.interpreter.EggError;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ErrorPanel extends JOptionPane {

  // This class contains all the text GUI for the error panel
  private class Info extends JPanel {
    public Info(List<EggError> errors) {
      String errorText = "";
      for (EggError error : errors) {
        errorText += error.toString() + "\n";
      }
      JTextArea errorTextArea = new JTextArea(errorText);

      errorTextArea.setColumns(43);
      errorTextArea.setLineWrap(true);
      errorTextArea.setWrapStyleWord(true);
      add(errorTextArea);
    }
  }

  private final List<EggError> errors;
  private final String msg;

  /**
   * Constructor that intializes final fields
   *
   * @param errors the {@code}List<EggError> of errors to display
   * @param msg the title of the {@code}JOptionPane
   */
  public ErrorPanel(List<EggError> errors, String msg) {
    this.errors = errors;
    this.msg = msg;
  }

  // Display the JOptionPane
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
