// Copyright 2003-2014 Adobe Systems Inc. All Rights Reserved.
// This software is proprietary; use is subject to license terms.
package com.nuaavee.collections.list;

import com.nuaavee.collections.list.util.Lists;

/**
 * @author Anshul Verma
 */
public class ArrayListTest extends AbstractListTest {

  @Override
  protected <T> List<T> newList(T... items) {
    return Lists.newArrayList(items);
  }
}
