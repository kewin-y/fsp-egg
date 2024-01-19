/*
 * === Name ===
 * Kevin Yu
 *
 * === Course ===
 * ICS4U1
 *
 * === Date ===
 * 2024-01-19
 *
 * === Title ===
 * EGG (Easy Graphics g)
 *
 * === Concerns: ===
 * Does not work on musl due to glibc being required for JavaCPP bindings of Jaylib 
 * No text editor -> user must write their own script in notepad, or some other external software
 * */
package com.keviny.egg;

import com.keviny.egg.controller.EggStageController;
import com.keviny.egg.gui.EggStage;

import java.io.IOException;

// This is the main class that instantiates the contoller
public class EggApplication {
  public static void main(String args[]) throws IOException {
    new EggStage();
    return;
  }
}
