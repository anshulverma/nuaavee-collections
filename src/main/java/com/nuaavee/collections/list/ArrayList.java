package com.nuaavee.collections.list;

import com.nuaavee.collections.list.util.Arrays;
import javax.annotation.Nullable;

/**
 * @author Anshul Verma
 */
public class ArrayList<T> extends AbstractList<T> {

  private static final int MIN_CAPACITY = 10;

  private Object[] items;
  int size = 0;

  public ArrayList() {
    items = new Object[MIN_CAPACITY];
  }

  @Override
  @SuppressWarnings("unchecked")
  public T get(int index) {
    checkIndex(index);
    return (T) items[index];
  }

  @Override
  public boolean add(@Nullable T item) {
    ensureCapacity();
    modCount++;
    items[size] = item;
    size++;
    return true;
  }

  @Override
  public boolean add(@Nullable T item, int index) {
    checkIndexForAdd(index);
    modCount++;
    ensureCapacity();
    System.arraycopy(items, index, items, index + 1, size - index);
    items[index] = item;
    size++;
    return true;
  }

  private void ensureCapacity() {
    if (items.length <= size) {
      items = Arrays.copyOf(items, (size * 3) / 2);
    }
  }

  @Nullable
  @Override
  public T remove(int index) {
    checkIndex(index);
    modCount++;
    T item = get(index);
    int itemsToMove = size - index - 1;
    if (itemsToMove > 0) {
      System.arraycopy(items, index + 1, items, index, itemsToMove);
    }
    items[--size] = null;
    return item;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public T replace(int index, T item) {
    checkIndex(index);
    modCount++;
    T oldItem = get(index);
    items[index] = item;
    return oldItem;
  }

  @Override
  public boolean clear() {
    if (size == 0) {
      return false;
    }
    items = new Object[MIN_CAPACITY];
    size = 0;
    return true;
  }
}
