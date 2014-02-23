// Copyright 2003-2014 Adobe Systems Inc. All Rights Reserved.
// This software is proprietary; use is subject to license terms.
package com.nuaavee.collections.model;

/**
 * @author Anshul Verma
 */
public class Book {

  private String title;
  private String author;
  private int price;
  private int pages;

  public Book(String title, String author, int price, int pages) {
    this.title = title;
    this.author = author;
    this.price = price;
    this.pages = pages;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public int getPrice() {
    return price;
  }

  public int getPages() {
    return pages;
  }
}
