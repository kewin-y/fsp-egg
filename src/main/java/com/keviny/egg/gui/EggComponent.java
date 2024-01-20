package com.keviny.egg.gui;

import com.raylib.Raylib;

// Abstract class for components in Egg
public abstract class EggComponent {
  private Raylib.Rectangle bounds;
  private boolean shouldDraw = true;

  public abstract void draw();

  public Raylib.Rectangle getBounds() {
    return bounds;
  }

  public void setBounds(Raylib.Rectangle bounds) {
    this.bounds = bounds;
  }

  public boolean isShouldDraw() {
    return shouldDraw;
  }

  public void setShouldDraw(boolean shouldDraw) {
    this.shouldDraw = shouldDraw;
  }

}
