package com.nuaavee.collections.list.util;

import com.nuaavee.collections.list.ArrayList;
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

  public static <T> List<T> newArrayList(T... items) {
    List<T> list = new ArrayList<>();
    for (T item : items) {
      list.add(item);
    }
    return list;
  }
}
