package com.keviny.egg.gui;

import com.raylib.Raylib;

public class InfoPanel extends EggComponent {
  private Raylib.Texture infoImage;
  private EggButton okButton;

  @Override
  public void draw() {
    if (!isShouldDraw()) return;
  }
}
