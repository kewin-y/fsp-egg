/* Kevin Yu
 * ICS4U1
 * EGG (Easy Graphics)
 * */
package com.keviny.egg;

import java.io.IOException;

import com.keviny.egg.gui.EggStage;
import com.keviny.egg.interpreter.EggInterpreter;

public class Egg {
  public static void main(String args[]) throws IOException {
    EggInterpreter.startInterpreter(new String[]{});
    // new EggStage();
    return;
  }
}
