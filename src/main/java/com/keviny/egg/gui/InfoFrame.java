package com.keviny.egg.gui;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class InfoFrame extends JFrame {
  // Enum's to represent the types of frames
  public static enum InfoType {
    EXAMPLES,
    HOW_TO,
    LEARN,
  }

  public InfoFrame(String title, InfoType type) {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setTitle(title);

    ImageIcon instruction = new ImageIcon("assets/sprites/how_to.png");
    JLabel l = new JLabel(instruction);
    JPanel p = new JPanel();
    p.add(l);

    JScrollPane scrollPane = new JScrollPane(l);
    scrollPane.setPreferredSize(new Dimension(650, 660));
    add(scrollPane);

    setSize(650, 600);
    setVisible(true);
    setLocationRelativeTo(null);
  }
}
