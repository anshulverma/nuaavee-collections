// Copyright 2003-2014 Adobe Systems Inc. All Rights Reserved.
// This software is proprietary; use is subject to license terms.
package com.nuaavee.collections.list;

import javax.annotation.Nullable;

/**
 * A doubly circular linked list implementation of {@link List} interface.
 *
 * @author Anshul Verma
 */
public class LinkedList<T> extends AbstractList<T> {

  private ListItem<T> root = null;

  private int size;

  @Nullable
  @Override
  public T get(int index) {
    return getListItem(index).data;
  }

  private ListItem<T> getListItem(int index) {
    checkIndex(index);
    ListItem<T> cursor = root;
    while (index-- > 0) {
      cursor = cursor.next;
    }
    return cursor;
  }

  @Override
  public boolean add(@Nullable T item) {
    ListItem<T> listItem = new ListItem<T>(item);
    if (root == null) {
      listItem.next = listItem.prev = listItem;
      root = listItem;
    } else {
      listItem.next = root;
      listItem.prev = root.prev;
      root.prev.next = listItem;
      root.prev = listItem;
    }
    size++;
    modCount++;
    return true;
  }

  @Override
  public boolean add(@Nullable T item, int index) {
    checkIndex(index, size + 1);
    if (root == null || index == size) {
      return add(item);
    }
    ListItem<T> listItem = getListItem(index);
    ListItem<T> newListItem = new ListItem<T>(item);
    newListItem.next = listItem;
    newListItem.prev = listItem.prev;
    listItem.prev.next = newListItem;
    listItem.prev = newListItem;
    if(index == 0) {
      root = newListItem;
    }
    size++;
    modCount++;
    return true;
  }

  @Nullable
  @Override
  public T remove(int index) {
    checkIndex(index);
    ListItem<T> listItem = getListItem(index);
    listItem.prev.next = listItem.next;
    listItem.next.prev = listItem.prev;
    if (size == 1) {
      root = null;
    } else if (index == 0) {
      root = root.next;
    }
    size--;
    modCount++;
    return listItem.data;
  }

  private void checkIndex(int index) {
    checkIndex(index, size);
  }

  private void checkIndex(int index, int size) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public T replace(T item, int index) {
    ListItem<T> listItem = getListItem(index);
    T original = listItem.data;
    listItem.data = item;
    modCount++;
    return original;
  }

  private class ListItem<T> {

    T data;
    ListItem<T> next;
    ListItem<T> prev;

    ListItem(T data) {
      this.data = data;
    }

  }
}
