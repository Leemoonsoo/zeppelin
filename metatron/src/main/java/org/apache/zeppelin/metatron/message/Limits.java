package org.apache.zeppelin.metatron.message;

import java.util.LinkedList;
import java.util.List;

public class Limits {
  List<String> sort = new LinkedList<>();
  long limit;

  public Limits(long limit) {
    this.limit = limit;
  }

  public long getLimit() {
    return limit;
  }
}