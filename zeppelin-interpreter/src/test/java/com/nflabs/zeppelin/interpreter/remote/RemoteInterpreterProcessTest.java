/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nflabs.zeppelin.interpreter.remote;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.HashMap;

import org.junit.Test;

import com.nflabs.zeppelin.interpreter.remote.RemoteInterpreterProcess;
import com.nflabs.zeppelin.interpreter.thrift.RemoteInterpreterService.Client;

public class RemoteInterpreterProcessTest {

  @Test
  public void testStartStop() {
    RemoteInterpreterProcess rip = new RemoteInterpreterProcess("../bin/interpreter.sh", "nonexists", new HashMap<String, String>());
    assertFalse(rip.isRunning());
    assertEquals(0, rip.referenceCount());
    assertEquals(1, rip.reference());
    assertEquals(2, rip.reference());
    assertEquals(true, rip.isRunning());
    assertEquals(1, rip.dereference());
    assertEquals(true, rip.isRunning());
    assertEquals(0, rip.dereference());
    assertEquals(false, rip.isRunning());
  }

  @Test
  public void testClientFactory() throws Exception {
    RemoteInterpreterProcess rip = new RemoteInterpreterProcess("../bin/interpreter.sh", "nonexists", new HashMap<String, String>());
    rip.reference();
    assertEquals(0, rip.getNumActiveClient());
    assertEquals(0, rip.getNumIdleClient());

    Client client = rip.getClient();
    assertEquals(1, rip.getNumActiveClient());
    assertEquals(0, rip.getNumIdleClient());

    rip.releaseClient(client);
    assertEquals(0, rip.getNumActiveClient());
    assertEquals(1, rip.getNumIdleClient());

    rip.dereference();
  }

}
