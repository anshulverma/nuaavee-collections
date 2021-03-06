package com.nuaavee.collections.list;

import com.nuaavee.collections.list.util.Lists;
import com.nuaavee.collections.model.Book;
import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author Anshul Verma
 */
public abstract class AbstractListTest {

  protected abstract <T> List<T> newList(T... items);

  @Test
  public void testSingleAddition() {
    List<Book> books = newList();
    assertTrue("initially list should be empty", books.isEmpty());
    assertEquals("size should be zero since the list is empty", 0, books.size());
    assertTrue("should be able to add one element to list",
        books.add(new Book("Clean Code", "Robert C. Martin", 52, 431)));
    assertFalse("list should not be empty now", books.isEmpty());
    assertEquals("there should be one element in the list", 1, books.size());
  }

  @Test
  public void testMultipleAddition() {
    List<Book> myBooks = newList(
        new Book("Clean Code", "Robert C. Martin", 52, 431),
        new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt", 35, 650),
        new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma", 40, 536),
        new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 31, 467),
        new Book("UNIX Systems Programming: Communication, Concurrency and Threads", "Kay A. Robbins", 61, 763));
    List<Book> books = newList();
    assertTrue("should be able to add multiple elements to list", books.addAll(myBooks));
    assertEquals("should have 5 items in the list", 5, books.size());
  }

  @Test
  public void testCanAddDuplicates() {
    Book book1 = new Book("Clean Code", "Robert C. Martin", 52, 431);
    List<Book> myBooks = newList(book1, book1, book1);
    assertEquals("should have three duplicate items", 3, myBooks.size());
    assertTrue("should be able to remove the first occurance of item", myBooks.remove(book1));
    assertEquals("should be able to remove item by index", book1, myBooks.remove(1));
    assertEquals(1, myBooks.size());
  }

  @Test
  public void testAddWithIndex() {
    Book book1 = new Book("Clean Code", "Robert C. Martin", 52, 431);
    Book book2 = new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 31, 467);
    Book book3 = new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma", 40, 536);
    List<Book> books = newList();
    assertTrue("should be able to add item at first index", books.add(book1, 0));
    assertTrue("should be able to add item at first index", books.add(book2, 0));
    assertTrue("should be able to add item at first index", books.add(book3, 0));
    assertEquals(3, books.size());
    assertEquals(book1, books.get(2));
    assertEquals(book2, books.get(1));
    assertEquals(book3, books.get(0));
  }

  @Test
  public void testAddWithIndexThrowsExceptionIfInvalidIndex() {
    Book book1 = new Book("Clean Code", "Robert C. Martin", 52, 431);
    Book book2 = new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 31, 467);
    Book book3 = new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma", 40, 536);
    Book book4 = new Book("UNIX Systems Programming: Communication, Concurrency and Threads", "Kay A. Robbins", 9, 763);
    List<Book> books = newList();
    try {
      books.add(book1, 1);
      fail("should throw IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) { }
    try {
      books.add(book1, -1);
      fail("should throw IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) { }
    books = newList(book1, book2, book3);
    try {
      books.add(book4, 4);
      fail("should throw IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) { }
  }

  @Test
  public void testCanAddNull() {
    List<Book> books = newList();
    books.add(null);
    assertFalse("should not be empty", books.isEmpty());
    assertEquals(1, books.size());
    assertEquals("should have null as first element", null, books.get(0));
    books.add(new Book("Clean Code", "Robert C. Martin", 52, 431));
    assertEquals(2, books.size());
    assertEquals("should have null as first element", 0, books.indexOf(null));
  }

  @Test
  public void testGetItem() {
    Book book1 = new Book("Clean Code", "Robert C. Martin", 52, 431);
    Book book2 = new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 31, 467);
    Book book3 = new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma", 40, 536);
    List<Book> books = newList();
    assertTrue("should be able to add a element to list", books.add(book1));
    assertTrue("should be able to add a element to list", books.add(book2));
    assertTrue("should be able to add a element to list", books.add(book3));
    assertEquals(book1, books.get(0));
    assertEquals(book2, books.get(1));
    assertEquals(book3, books.get(2));
  }

  @Test
  public void testRemoveItem() {
    Book book1 = new Book("Clean Code", "Robert C. Martin", 52, 431);
    Book book2 = new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 31, 467);
    Book book3 = new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma", 40, 536);
    List<Book> books = newList(book1, book2, book3);
    assertEquals("should be able to remove element by index", book2, books.remove(1));
    assertEquals(2, books.size());
    assertFalse("should not be able to remove item that does not exist", books.remove(book2));
    assertTrue("should be able to remove item that exists", books.remove(book3));
    assertEquals(1, books.size());
  }

  @Test
  public void testClear() {
    Book book1 = new Book("Clean Code", "Robert C. Martin", 52, 431);
    Book book2 = new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 31, 467);
    Book book3 = new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma", 40, 536);
    List<Book> myBooks = newList(book1, book2, book3);
    assertEquals(3, myBooks.size());
    assertTrue("should change the contents of the list", myBooks.clear());
    assertTrue("list should be empty after clearing", myBooks.isEmpty());
    assertFalse("should not change the list since it is already empty", myBooks.clear());
  }

  @Test
  public void testIndexOf() {
    Book book1 = new Book("Clean Code", "Robert C. Martin", 52, 431);
    Book book2 = new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 31, 467);
    Book book3 = new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma", 40, 536);
    List<Book> myBooks = newList(book1, book2, book3);
    assertEquals(3, myBooks.size());
    assertEquals("should return correct index if item exists", 1, myBooks.indexOf(book2));
    myBooks.remove(book2);
    assertThat("should return negative number if item does not exist", myBooks.indexOf(book2), lessThan(0));
  }

  @Test
  public void testContains() {
    Book book1 = new Book("Clean Code", "Robert C. Martin", 52, 431);
    Book book2 = new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 31, 467);
    Book book3 = new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma", 40, 536);
    List<Book> myBooks = newList(book1, book2, book3);
    assertEquals(3, myBooks.size());
    assertTrue("should contain an item that exist in the list", myBooks.contains(book2));
    myBooks.remove(book2);
    assertFalse("should not contain item once removed", myBooks.contains(book2));
  }

  @Test
  public void testReplace() {
    Book book1 = new Book("Clean Code", "Robert C. Martin", 52, 431);
    Book book2 = new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 31, 467);
    Book book3 = new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma", 40, 536);
    Book book4 = new Book("UNIX Systems Programming: Communication, Concurrency and Threads", "Kay A. Robbins", 9, 763);
    List<Book> myBooks = newList(book1, book2, book3);
    assertEquals("should find item at correct index since it exists", 1, myBooks.indexOf(book2));
    assertEquals("should match original item on replacing", book2, myBooks.replace(1, book4));
    assertThat("should not find item after replace", myBooks.indexOf(book2), lessThan(0));
    assertEquals("should find new item at original index", 1, myBooks.indexOf(book4));
    try {
      myBooks.replace(3, book2);
      fail("should throw IndexOutOfBoundsException");
    } catch (IndexOutOfBoundsException e) { }
  }

  @Test
  public void testEquals() {
    List<Book> myBooks1 = newList(
        new Book("Clean Code", "Robert C. Martin", 52, 431),
        new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt", 35, 650),
        new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma", 40, 536),
        new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 31, 467),
        new Book("UNIX Systems Programming: Communication, Concurrency and Threads", "Kay A. Robbins", 61, 763));
    List<Book> myBooks2 = newList(
        new Book("Clean Code", "Robert C. Martin", 52, 431),
        new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt", 35, 650),
        new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma", 40, 536),
        new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 31, 467),
        new Book("UNIX Systems Programming: Communication, Concurrency and Threads", "Kay A. Robbins", 61, 763));
    assertEquals("list should be equal because they have the same items even though they are not the same objects",
        myBooks1, myBooks2);
    assertEquals("nulls in the list should equate",
        newList("one", null, "three"), newList("one", null, "three"));
  }

  @Test
  public void testNotEquals() {
    List<String> catNames = newList("kitty", "meow", "chunmun");
    assertFalse("should not be equal to null", catNames.equals(null));
    assertFalse("should not be equal if contents differ", catNames.equals(newList("sher")));
  }

  @Test
  public void testIterator() {
    List<Book> myBooks = newList(
        new Book("Clean Code", "Robert C. Martin", 52, 431),
        new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt", 35, 650),
        new Book("UNIX Systems Programming: Communication, Concurrency and Threads", "Kay A. Robbins", 61, 763));
    List<String> bookTitles = newList();
    for (Book book : myBooks) {
      bookTitles.add(book.getTitle());
    }
    assertEquals("iterator should have iterated over all elements in sequence of addition",
        newList("Clean Code", "The Pragmatic Programmer: From Journeyman to Master",
            "UNIX Systems Programming: Communication, Concurrency and Threads"), bookTitles);
  }

  @Test
  public void testIteratorRemove() {
    List<Book> myBooks = newList(
        new Book("Clean Code", "Robert C. Martin", 52, 431),
        new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt", 35, 650),
        null,
        new Book("Code Complete: A Practical Handbook of Software Construction", "Steve McConnell", 31, 467),
        new Book("UNIX Systems Programming: Communication, Concurrency and Threads", "Kay A. Robbins", 61, 763));
    Iterator<Book> bookIterator = myBooks.iterator();
    try {
      bookIterator.remove();
      fail("should throw IllegalStateException");
    } catch (IllegalStateException e) { }
    assertTrue("should have next element", bookIterator.hasNext());
    assertEquals("next() should be equal to the first element", new Book("Clean Code", "Robert C. Martin", 52, 431),
        bookIterator.next());
    bookIterator.remove();
    try {
      bookIterator.remove();
      fail("should not be able to call remove twice");
    } catch (IllegalStateException e) { }
    assertEquals("size should have reduced by one", 4, myBooks.size());
    assertEquals("second element should have moved to first index",
        new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt", 35, 650), myBooks.get(0));
    assertTrue("there should be more elements in the list", bookIterator.hasNext());
    assertEquals("next() should also return the second element now",
        new Book("The Pragmatic Programmer: From Journeyman to Master", "Andrew Hunt", 35, 650), bookIterator.next());
    assertEquals("null values should also be returned", null, bookIterator.next());
  }

  @Test
  public void testIteratorOnEmptyList() {
    Iterator<String> iterator = this.<String> newList().iterator();
    assertFalse("there should be no elements in empty iterator", iterator.hasNext());
  }

  @Test
  public void testCannotModifyListWhileIterating() {
    List<String> catNames = newList("kitty", "meow", "chunmun");
    Iterator<String> catNameIterator = catNames.iterator();
    assertEquals("should be able to call next since the list is not modified", "kitty", catNameIterator.next());
    catNames.add("sher");
    try {
      catNameIterator.hasNext();
      fail("hasNext() should throw ConcurrentModificationException");
    } catch (ConcurrentModificationException e) { }
    try {
      catNameIterator.next();
      fail("next() should throw ConcurrentModificationException");
    } catch (ConcurrentModificationException e) { }
    try {
      catNameIterator.remove();
      fail("remove() should throw ConcurrentModificationException");
    } catch (ConcurrentModificationException e) { }
  }
}
