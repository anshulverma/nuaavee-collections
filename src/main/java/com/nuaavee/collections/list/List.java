package com.nuaavee.collections.list;

import javax.annotation.Nullable;

/**
 * A list describes a collection of similar elements. Provides basic
 * functionality of a list like <code>get</code>, <code>add</code> and
 * <code>remove</code>.
 * @param <T> The type of elements in the list.
 * @author Anshul Verma
 */
public interface List<T> extends Iterable<T> {

  /**
   * Returns the item at <code>index</code> if such an element exists.
   * @param index The index of the element (starting from zero).
   * @return The element at <code>index</code>.
   * @throws IndexOutOfBoundsException When <code>index</code> is invalid
   * (i.e. less than zero or greater than <code>size</code>.
   */
  @Nullable
  T get(int index);

  /**
   * Add an item at the end of the list.
   * @param item The item to add.
   * @return <code>true</code> if added successfully, <code>false</code> otherwise.
   */
  boolean add(@Nullable T item);

  /**
   * Adds an item at the specified index in the list.
   * @param item Item to add.
   * @param index Index to add the item at.
   * @return <code>true</code> if item was added successfully. <code>false</code> if
   * there was an error while adding the element.
   * @throws IndexOutOfBoundsException If the <code>index</code> is not valid.
   */
  boolean add(@Nullable T item, int index);

  /**
   * Remove an item at the specified index.
   * @param index Index at which we want to remove the item.
   * @return The element that was removed from the list.
   * @throws IndexOutOfBoundsException If the <code>index</code> is not valid.
   */
  @Nullable
  T remove(int index);

  /**
   * Searches the list to find the first occurrence of <code>item</code>.
   * @param item The item to search for in the list.
   * @return The index of the first occurrence of <code>item</code> if found, -1
   * otherwise.
   */
  int indexOf(@Nullable T item);

  /**
   * Get the current size of the list.
   * @return The size of the list.
   */
  int size();

  /**
   * Replace the item at <code>index</code> with the specified item.
   *
   * @param item The item to replace.
   * @param index The index at which the item must be placed.
   * @throws IndexOutOfBoundsException If the specified <code>index</code> is not valid.
   */
  T replace(T item, int index);
}
