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

  /**
   * Returns <code>true</code> by comparing the other object in following steps:
   * <ol>
   *   <li>
   *     <code>obj</code> must extend from {@link List} interface.
   *   </li>
   *   <li>
   *     The size of both lists should match.
   *   </li>
   *   <li>
   *     Tee <code>equals()</code> method of every element must return true for
   *     every element in the other list at the same index. The only time this may not
   *     be true if both elements are <code>null</code>.
   *   </li>
   * </ol>
   * @param obj The object to test against.
   * @return <code>true</code> If the two lists are equal, <code>false</code> otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof List)) {
      return false;
    }

    List other = (List) obj;
    if (size() != other.size()) {
      return false;
    }

    Iterator<T> it1 = iterator();
    Iterator it2 = other.iterator();
    while (it1.hasNext() && it2.hasNext()) {
      T o1 = it1.next();
      Object o2 = it2.next();
      if (!(o1 == null ? o2 == null : o1.equals(o2))) {
        return false;
      }
    }
    return true;
  }

  /**
   * Iterates through all elements and calls <code>toString()</code> of each
   * of them to build a concatenated string.
   * @return A concatenated string containing string representations of all elements.
   */
  @Override
  public String toString() {
    if (isEmpty()) {
      return "[ ]";
    }

    StringBuilder str = new StringBuilder("[ ");
    Iterator<T> iterator = iterator();
    while (iterator.hasNext()) {
      T item = iterator.next();
      if (this == item) {
        str.append("(this list)");
      } else {
        str.append(item);
      }
      if (iterator.hasNext()) {
        str.append(", ");
      }
    }
    return str.append(" ]").toString();
  }

  @Override
  @SuppressWarnings("unchecked")
  public Iterator<T> iterator() {
    return new Itr();
  }

  @SuppressWarnings("unchecked")
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
