package com.keviny.egg.gui;

import com.raylib.Raylib;
import com.raylib.Raylib.Rectangle;

// Abstract class for components on Egg
public abstract class EggComponent {
  private Raylib.Rectangle bounds;
  private boolean shouldDraw = true;

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
