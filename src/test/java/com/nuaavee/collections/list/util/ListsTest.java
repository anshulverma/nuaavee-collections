// Copyright 2003-2014 Adobe Systems Inc. All Rights Reserved.
// This software is proprietary; use is subject to license terms.
package com.nuaavee.collections.list.util;

import com.nuaavee.collections.list.List;
import com.nuaavee.collections.model.Book;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Anshul Verma
 */
public class ListsTest {

  @Test
  public void testCreateEmptyList() {
    List<Object> list = Lists.newLinkedList();
    assertTrue(list.isEmpty());
  }

  @Test
  public void testCanCreateLinkedList() {
    List<Book> books = Lists.newLinkedList(
        new Book("Clean Code", "Robert C. Martin", 52, 431),
        new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt", 35, 650),
        new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma", 40, 536),
        new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 31, 467),
        new Book("UNIX Systems Programming: Communication, Concurrency and Threads", "Kay A. Robbins", 61, 763));

    assertFalse(books.isEmpty());
    assertEquals(5, books.size());
  }
}
