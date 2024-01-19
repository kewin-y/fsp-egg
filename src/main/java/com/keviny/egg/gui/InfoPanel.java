package com.keviny.egg.gui;

import com.raylib.Raylib;

public class InfoPanel extends EggComponent {
  private static final int WIDTH = 600;
  private static final int HEIGHT = 600;
  private static final int X_POS = EggStage.centerX(WIDTH);
  private static final int Y_POS = EggStage.centerY(HEIGHT);
  private Raylib.Texture infoImage;
  private EggButton okButton;


  public InfoPanel() {

  }

  @Override
  public void draw() {
    if (!isShouldDraw()) return;
  }
}
