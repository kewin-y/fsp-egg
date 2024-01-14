// Sourced from https://www.raylib.com/examples.html
package com.keviny.egg.gui;

import com.keviny.egg.controller.OnClickFunction;
import com.raylib.Jaylib;
import com.raylib.Raylib;

public class EggButton {
  private static final Raylib.Color DEFAULT = Jaylib.WHITE;
  private static final Raylib.Color HOVERED = new Jaylib.Color(255, 255, 255, 180);
  private static final Raylib.Color CLICKED = new Jaylib.Color(255, 255, 255, 120);

  private Raylib.Texture texture;
  private Raylib.Rectangle bounds;
  private OnClickFunction onClickFunction;

  private Raylib.Color currentColor;

  public EggButton(String spritePath, int x, int y) {
    texture = Jaylib.LoadTexture(spritePath);

    // JavaCPP does not generate constructors. The recommended JavaCPP way to
    // initialize a struct is like this: var vec = new Vector3().x(1).y(2).z(3);

    // The position and size of the button
    bounds = new Jaylib.Rectangle().x(x).y(y).width(texture.width()).height(texture.height());
  }

  // Polls for button events and calls any function required
  // https://github.com/raysan5/raylib/blob/master/examples/textures/textures_sprite_button.c
  public void poll(Raylib.Vector2 mousePos) {
    boolean clicked = false;
    if (Jaylib.CheckCollisionPointRec(mousePos, bounds)) {
      if (Jaylib.IsMouseButtonDown(Jaylib.MOUSE_BUTTON_LEFT)) currentColor = CLICKED;
      else currentColor = HOVERED;

      if (Jaylib.IsMouseButtonReleased(Jaylib.MOUSE_BUTTON_LEFT)) clicked = true;
    } else currentColor = DEFAULT;

    if (clicked) {
      if (onClickFunction == null) {
        throw new UnsupportedOperationException("No function attached to this button.");
      }

      onClickFunction.onClickMethod();
    }
  }

  /*
   * Getters & Setters
   */

  public Raylib.Texture getTexture() {
    return texture;
  }

  public void setTexture(Raylib.Texture texture) {
    this.texture = texture;
  }

  public Raylib.Rectangle getBounds() {
    return bounds;
  }

  public void attachFunction(OnClickFunction onClickFunction) {
    this.onClickFunction = onClickFunction;
  }

  public Raylib.Color getCurrentColor() {
    return currentColor;
  }
}
