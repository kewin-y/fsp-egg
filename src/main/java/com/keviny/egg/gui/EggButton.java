// Sourced from https://www.raylib.com/examples.html
package com.keviny.egg.gui;

import com.keviny.egg.controller.OnClick;
import com.raylib.Jaylib;
import com.raylib.Raylib;

public class EggButton {
  private static final Raylib.Color DEFAULT = Jaylib.WHITE;
  private static final Raylib.Color HOVERED = new Jaylib.Color(255, 255, 255, 180);
  private static final Raylib.Color CLICKED = new Jaylib.Color(255, 255, 255, 120);

  private Raylib.Texture texture;
  private Raylib.Rectangle bounds;
  private OnClick onClick;

  private Raylib.Color currentColor;

  public EggButton(String spritePath, int x, int y) {
    texture = Jaylib.LoadTexture(spritePath);

    // JavaCPP does not generate constructors. The recommended JavaCPP way to
    // initialize a struct is like this: var vec = new Vector3().x(1).y(2).z(3);

    // The position and size of the button
    bounds = new Jaylib.Rectangle().x(x).y(y).width(texture.width()).height(texture.height());
  }

  /**
   * Polls for button events given the position of the mouse and calls any function required
   *
   * @param mousePos the position of the mouse
   */
  public void poll(Raylib.Vector2 mousePos) {
    boolean clicked = false;
    if (Jaylib.CheckCollisionPointRec(mousePos, bounds)) {
      if (Jaylib.IsMouseButtonDown(Jaylib.MOUSE_BUTTON_LEFT)) currentColor = CLICKED;
      else currentColor = HOVERED;

      if (Jaylib.IsMouseButtonReleased(Jaylib.MOUSE_BUTTON_LEFT)) clicked = true;
    } else currentColor = DEFAULT;

    if (clicked) {
      if (onClick == null) {
        throw new UnsupportedOperationException("No function attached to this button.");
      }

      onClick.onClickMethod();
    }
  }

  // Responsible for displaying the button
  public void draw() {
    Jaylib.DrawTextureV(
        texture,
        new Jaylib.Vector2(bounds.x(), bounds.y()),
        // some objects like this.
        currentColor);
  }

  /*
   * Getters & Setters
   */

  // Attaches a functional interface to add button actions
  public void attachFunction(OnClick onClickFunction) {
    this.onClick = onClickFunction;
  }

  public Raylib.Texture getTexture() {
    return texture;
  }

  public void setTexture(Raylib.Texture texture) {
    this.texture = texture;
  }

  public Raylib.Rectangle getBounds() {
    return bounds;
  }

  public Raylib.Color getCurrentColor() {
    return currentColor;
  }
}
