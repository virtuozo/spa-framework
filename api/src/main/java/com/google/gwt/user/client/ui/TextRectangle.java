package com.google.gwt.user.client.ui;

import com.google.gwt.dom.client.Element;

public class TextRectangle extends Element {
  protected TextRectangle() {
    super();
  }
  
  public final native double left()/*-{ 
    return this.left || 0;
  }-*/;

  public final native double top()/*-{ 
    return this.top || 0; 
  }-*/;
  
  public final native double right()/*-{ 
    return this.right || 0; 
  }-*/;
  
  public final native double bottom()/*-{ 
    return this.bottom || 0; 
  }-*/;
  
  public final double width(){
    return this.right() - this.left();
  }
  
  public final double height(){
    return this.bottom() - this.top();
  }
}