package com.nuaavee.collections.list.util;

import com.nuaavee.collections.list.LinkedList;
import com.nuaavee.collections.list.List;

/**
 * @author Anshul Verma
 */
public final class Lists {

  private Lists() { }

  public static <T> List<T> newLinkedList(T... items) {
    List<T> list = new LinkedList<>();
    for (T item : items) {
      list.add(item);
    }
    return list;
  }
}
