package com.google.gwt.user.client.ui;

public class Offset {
  private int left;
  private int top;

  Offset(int left, int top) {
    super();
    this.left = left;
    this.top = top;
  }

  public int left() {
    return this.left;
  }

  public int top() {
    return this.top;
  }
}