package com.nuaavee.collections.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(title)
        .append(author)
        .append(price)
        .append(pages)
        .toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Book)) {
      return false;
    }

    Book other = (Book) obj;
    return new EqualsBuilder()
        .append(title, other.title)
        .append(author, other.author)
        .append(price, other.price)
        .append(pages, other.pages)
        .isEquals();
  }
}
