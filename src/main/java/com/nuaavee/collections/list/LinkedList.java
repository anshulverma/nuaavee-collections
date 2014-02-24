package com.nuaavee.collections.list;

import javax.annotation.Nullable;

/**
 * A doubly circular linked list implementation of {@link List} interface.
 *
 * @author Anshul Verma
 */
public class LinkedList<T> extends AbstractList<T> {

  private ListItem<T> root = null;

  private int size = 0;

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
      root.addBefore(listItem);
    }
    size++;
    modCount++;
    return true;
  }

  @Override
  public boolean add(@Nullable T item, int index) {
    checkIndexForAdd(index);
    if (root == null || index == size) {
      return add(item);
    }
    ListItem<T> listItem = getListItem(index);
    listItem.addBefore(new ListItem<T>(item));
    if(index == 0) {
      root = listItem.prev;
    }
    size++;
    modCount++;
    return true;
  }

  @Override
  public T remove(int index) {
    checkIndex(index);
    ListItem<T> listItem = getListItem(index);
    listItem.remove();
    if (size == 1) {
      root = null;
    } else if (index == 0) {
      root = root.next;
    }
    size--;
    modCount++;
    return listItem.data;
  }

  @Override
  public boolean clear() {
    if (size == 0) {
      return false;
    }
    root = null;
    size = 0;
    modCount++;
    return true;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public T replace(int index, T item) {
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

    public void addBefore(ListItem<T> item) {
      item.next = this;
      item.prev = prev;
      prev.next = item;
      prev = item;
    }

    public void remove() {
      prev.next = next;
      next.prev = prev;
    }
  }
}
