package org.fmagarot.core;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class CoreApplication {

  public static void main(String[] args) {
    Quarkus.run(args);
  }
}
