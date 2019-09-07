package com.drakeet.about;

import androidx.annotation.NonNull;

/**
 * @author drakeet
 */
public class License {

  public static final String MIT = "MIT License";
  public static final String APACHE_2 = "Apache Software License 2.0";
  public static final String GPL_V3 = "GNU general public license Version 3";

  public String name;
  public String author;
  public String type;
  public String url;

  public License() {}

  public License(@NonNull String name, @NonNull String author, @NonNull String type, @NonNull String url) {
    this.name = name;
    this.author = author;
    this.type = type;
    this.url = url;
  }
}