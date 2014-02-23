// Copyright 2003-2014 Adobe Systems Inc. All Rights Reserved.
// This software is proprietary; use is subject to license terms.
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
