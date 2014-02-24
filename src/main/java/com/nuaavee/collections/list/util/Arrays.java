package com.nuaavee.collections.list.util;

import java.lang.reflect.Array;

/**
 * @author Anshul Verma
 */
public final class Arrays {

  @SuppressWarnings("unchecked")
  public static <T> T[] copyOf(T[] original, int newLength) {
    return (T[]) copyOf(original, newLength, original.getClass());
  }

  @SuppressWarnings("unchecked")
  private static <T> T[] copyOf(T[] original, int newLength, Class<? extends T[]> clazz) {
    T[] newArray = (T[]) Array.newInstance(clazz, newLength);
    System.arraycopy(original, 0, newArray, 0, Math.min(original.length, newLength));
    return newArray;
  }
}
