package com.keviny.egg.gui;

import java.awt.Dimension;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

// Class that displays instructions/info
public class InfoFrame extends JFrame {

  // Enum to represent the types of frames
  public static enum InfoType {
    EXAMPLES,
    HOW_TO,
    LEARN,
  }

  // For easier lookup
  private static HashMap<InfoType, String> images;

  static {
    images = new HashMap<>();
    images.put(InfoType.EXAMPLES, "assets/sprites/examples.png");
    images.put(InfoType.HOW_TO, "assets/sprites/how_to.png");
    images.put(InfoType.LEARN, "assets/sprites/learn.png");
  }

  // Constructor which sets the image, title, and displays the frame
  public InfoFrame(String title, InfoType type) {

    // Determine the image
    ImageIcon instruction = new ImageIcon(images.get(type));
    JLabel l = new JLabel(instruction);
    JPanel p = new JPanel();
    p.add(l);

    // Initialize the scrollpane
    JScrollPane scrollPane = new JScrollPane(l);
    scrollPane.setPreferredSize(new Dimension(650, 660));
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.getVerticalScrollBar().setUnitIncrement(20);
    add(scrollPane);

    // Frame initialization
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setTitle(title);
    setSize(650, 600);
    setVisible(true);
    setLocationRelativeTo(null);
  }
}
