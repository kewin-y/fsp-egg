// Sourced from https://www.raylib.com/examples.html
package com.keviny.egg.gui;

import com.keviny.egg.controller.OnClick;
import com.raylib.Jaylib;
import com.raylib.Raylib;

public class EggButton extends EggComponent {
  private static final Raylib.Color DEFAULT = Jaylib.WHITE;
  private static final Raylib.Color HOVERED = new Jaylib.Color(255, 255, 255, 180);
  private static final Raylib.Color CLICKED = new Jaylib.Color(255, 255, 255, 120);
  private Raylib.Color currentColor;

  private Raylib.Texture texture;
  private OnClick onClick;

  public EggButton(String spritePath, int x, int y) {
    texture = Jaylib.LoadTexture(spritePath);

    // JavaCPP does not generate constructors. The recommended JavaCPP way to
    // initialize a struct is like this: var vec = new Vector3().x(1).y(2).z(3);

    // The position and size of the button
    setBounds(new Jaylib.Rectangle().x(x).y(y).width(texture.width()).height(texture.height()));
  }

  // Responsible for displaying the button
  public void draw() {
    Jaylib.DrawTextureV(
        texture,
        new Jaylib.Vector2(getBounds().x(), getBounds().y()),
        // some objects like this.
        currentColor);
  }

  /**
   * Polls for button events given the position of the mouse and calls any function required
   *
   * @param mousePos the position of the mouse
   */
  public void poll(Raylib.Vector2 mousePos) {
    boolean clicked = false;
    if (Jaylib.CheckCollisionPointRec(mousePos, getBounds())) {
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

  public Raylib.Color getCurrentColor() {
    return currentColor;
  }
}
