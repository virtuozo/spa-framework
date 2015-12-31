package com.google.gwt.user.client.ui;

public class Offset {
  private double left;
  private double top;

  public Offset(double left, double top) {
    super();
    this.left = left;
    this.top = top;
  }

  public double left() {
    return this.left;
  }

  public double top() {
    return this.top;
  }
}