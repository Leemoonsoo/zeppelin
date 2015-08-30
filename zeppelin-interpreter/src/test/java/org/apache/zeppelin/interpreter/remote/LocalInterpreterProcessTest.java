package org.apache.zeppelin.interpreter.remote;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class LocalInterpreterProcessTest {
  String localRepo = System.getProperty("java.io.tmpdir") + "/localrepo";

  @Test
  public void testStartStop() {
    LocalInterpreterProcess process = new LocalInterpreterProcess(
        "../bin/interpreter.sh", "nonexists", new HashMap<String, String>(),
        10 * 1000, localRepo);

    assertFalse(process.isRunning());
    process.start();
    assertEquals("localhost", process.getHost());
    assertTrue(process.getPort() > 0);
    assertTrue(process.isRunning());
    process.stop();
    assertFalse(process.isRunning());
  }

}
