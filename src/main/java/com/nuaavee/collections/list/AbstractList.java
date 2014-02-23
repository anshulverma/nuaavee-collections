package com.nuaavee.collections.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Anshul Verma
 */
public abstract class AbstractList<T> implements List<T> {

  protected int modCount = 0;

  @Override
  public boolean addAll(@Nonnull List<T> items) {
    int addModCount = modCount;
    for (T item : items) {
      add(item);
    }
    return modCount != addModCount;
  }

  @Override
  public int indexOf(@Nullable T item) {
    ListIterator<T> itr = listIterator();
    if (item == null) {
      while (itr.hasNext()) {
        if (itr.next() == null) {
          return itr.previousIndex();
        }
      }
    } else {
      while(itr.hasNext()) {
        if (item.equals(itr.next())) {
          return itr.previousIndex();
        }
      }
    }
    return -1;
  }

  @Override
  public boolean contains(@Nullable T item) {
    return indexOf(item) >= 0;
  }

  @Override
  public boolean remove(T item) {
    int index = indexOf(item);
    if (index < 0) {
      return false;
    }
    remove(index);
    return true;
  }

  @Override
  public Iterator<T> iterator() {
    return new Itr();
  }

  public ListIterator<T> listIterator() {
    return new ListItr();
  }

  private class Itr implements Iterator<T> {
    int position = 0;
    int lastPosotion = -1;
    int itModCount = modCount;

    @Override
    public boolean hasNext() {
      checkForModification();
      return position < size();
    }

    @Override
    public T next() {
      checkForModification();
      try {
        T item = get(position);
        lastPosotion = position;
        position++;
        return item;
      } catch (IndexOutOfBoundsException e) {
        throw new NoSuchElementException();
      }
    }

    @Override
    public void remove() {
      if (lastPosotion < 0) {
        throw new IllegalStateException();
      }
      checkForModification();
      AbstractList.this.remove(lastPosotion);
      position = lastPosotion;
      lastPosotion = -1;
      itModCount = modCount;
    }

    protected void checkForModification() {
      if (itModCount != modCount) {
        throw new ConcurrentModificationException();
      }
    }
  }

  private class ListItr extends Itr implements ListIterator<T> {

    @Override
    public boolean hasPrevious() {
      checkForModification();
      return position > 0;
    }

    @Override
    public T previous() {
      checkForModification();
      try {
        T item = get(position - 1);
        lastPosotion = position;
        position--;
        return item;
      } catch (IndexOutOfBoundsException e) {
        throw new NoSuchElementException();
      }
    }

    @Override
    public int nextIndex() {
      checkForModification();
      return position;
    }

    @Override
    public int previousIndex() {
      checkForModification();
      return position - 1;
    }

    @Override
    public void set(T item) {
      if (lastPosotion < 0) {
        throw new IllegalStateException();
      }
      checkForModification();
      AbstractList.this.replace(position, item);
      itModCount = modCount;
    }

    @Override
    public void add(T item) {
      checkForModification();
      AbstractList.this.add(item, position);
      position++;
      lastPosotion = -1;
      itModCount = modCount;
    }
  }
}
