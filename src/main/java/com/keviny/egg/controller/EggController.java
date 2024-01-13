package com.keviny.egg.controller;

import com.keviny.egg.gui.EggStage;

public class EggController {
  EggStage es;

  public EggController() {
    es = new EggStage(this);
  }

  public OnClickFunction handlePausePlay =
      () -> {
        System.out.println("Pause/Play");
      };
  public OnClickFunction handleRestart =
      () -> {
        System.out.println("Restart");
      };
  public OnClickFunction handleStop =
      () -> {
        System.out.println("Stop");
      };
  public OnClickFunction handleLoadNew =
      () -> {
        System.out.println("Loading New");
      };
}
