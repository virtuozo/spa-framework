/**
 * Copyright (C) 2004-2014 the original author or authors. See the notice.md file distributed with
 * this work for additional information regarding copyright ownership.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package virtuozo.ui;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Clear;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Float;
import com.google.gwt.dom.client.Style.FontStyle;
import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.ListStyleType;
import com.google.gwt.dom.client.Style.OutlineStyle;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.TableLayout;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.TextDecoration;
import com.google.gwt.dom.client.Style.TextJustify;
import com.google.gwt.dom.client.Style.TextOverflow;
import com.google.gwt.dom.client.Style.TextTransform;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.dom.client.Style.WhiteSpace;

public final class Style {

  private Component<?> widget;

  public Style(Component<?> widget) {
    this.widget = widget;
  }

  public Style clearBackgroundColor() {
    this.style().clearBackgroundColor();
    return this;
  }

  public Style clearBackgroundImage() {
    this.style().clearBackgroundImage();
    return this;
  }

  public Style clearBorderColor() {
    this.style().clearBorderColor();
    return this;
  }

  public Style clearBorderStyle() {
    this.style().clearBorderStyle();
    return this;
  }

  public Style clearBorderWidth() {
    this.style().clearBorderWidth();
    return this;
  }

  public Style clearBottom() {
    this.style().clearBottom();
    return this;
  }

  public Style clearClear() {
    this.style().clearClear();
    return this;
  }

  public Style clearColor() {
    this.style().clearColor();
    return this;
  }

  public Style clearCursor() {
    this.style().clearCursor();
    return this;
  }

  public Style clearDisplay() {
    this.style().clearDisplay();
    return this;
  }

  public Style clearFloat() {
    this.style().clearFloat();
    return this;
  }

  public Style clearFontSize() {
    this.style().clearFontSize();
    return this;
  }

  public Style clearFontStyle() {
    this.style().clearFontStyle();
    return this;
  }

  public Style clearFontWeight() {
    this.style().clearFontWeight();
    return this;
  }

  public Style clearHeight() {
    this.style().clearHeight();
    return this;
  }

  public Style clearLeft() {
    this.style().clearLeft();
    return this;
  }

  public Style clearLineHeight() {
    this.style().clearLineHeight();
    return this;
  }

  public Style clearListStyleType() {
    this.style().clearListStyleType();
    return this;
  }

  public Style clearMargin() {
    this.style().clearMargin();
    return this;
  }

  public Style clearMarginBottom() {
    this.style().clearMarginBottom();
    return this;
  }

  public Style clearMarginLeft() {
    this.style().clearMarginLeft();
    return this;
  }

  public Style clearMarginRight() {
    this.style().clearMarginRight();
    return this;
  }

  public Style clearMarginTop() {
    this.style().clearMarginTop();
    return this;
  }
  
  public Style clearMinHeight() {
    return this.clearProperty("minHeight");
  }
  
  public Style clearMaxHeight() {
    return this.clearProperty("maxHeight");
  }

  public Style clearMinWidth() {
    return this.clearProperty("minWidth");
  }

  public Style clearMaxWidth() {
    return this.clearProperty("maxWidth");
  }

  public Style clearOpacity() {
    this.style().clearOpacity();
    return this;
  }

  public Style clearOutlineColor() {
    this.style().clearOutlineColor();
    return this;
  }

  public Style clearOutlineStyle() {
    this.style().clearOutlineStyle();
    return this;
  }

  public Style clearOutlineWidth() {
    this.style().clearOutlineWidth();
    return this;
  }

  public Style clearOverflow() {
    this.style().clearOverflow();
    return this;
  }

  public Style clearOverflowX() {
    this.style().clearOverflowX();
    return this;
  }

  public Style clearOverflowY() {
    this.style().clearOverflowY();
    return this;
  }

  public Style clearPadding() {
    this.style().clearPadding();
    return this;
  }

  public Style clearPaddingBottom() {
    this.style().clearPaddingBottom();
    return this;
  }

  public Style clearPaddingLeft() {
    this.style().clearPaddingLeft();
    return this;
  }

  public Style clearPaddingRight() {
    this.style().clearPaddingRight();
    return this;
  }

  public Style clearPaddingTop() {
    this.style().clearPaddingTop();
    return this;
  }

  public Style clearPosition() {
    this.style().clearPosition();
    return this;
  }

  public Style clearProperty(String name) {
    this.style().clearProperty(name);
    return this;
  }

  public Style clearRight() {
    this.style().clearRight();
    return this;
  }

  public Style clearTableLayout() {
    this.style().clearTableLayout();
    return this;
  }

  public Style clearTextAlign() {
    this.style().clearTextAlign();
    return this;
  }

  public Style clearTextDecoration() {
    this.style().clearTextDecoration();
    return this;
  }

  public Style clearTextIndent() {
    this.style().clearTextIndent();
    return this;
  }

  public Style clearTextJustify() {
    this.style().clearTextJustify();
    return this;
  }

  public Style clearTextOverflow() {
    this.style().clearTextOverflow();
    return this;
  }

  public Style clearTextTransform() {
    this.style().clearTextTransform();
    return this;
  }

  public Style clearTop() {
    this.style().clearTop();
    return this;
  }

  public Style clearVisibility() {
    this.style().clearVisibility();
    return this;
  }

  public Style clearWhiteSpace() {
    this.style().clearWhiteSpace();
    return this;
  }

  public Style clearWidth() {
    this.style().clearWidth();
    return this;
  }

  public Style clearZIndex() {
    this.style().clearZIndex();
    return this;
  }

  public String backgroundColor() {
    return this.style().getBackgroundColor();
  }

  public String backgroundImage() {
    return this.style().getBackgroundImage();
  }

  public String borderColor() {
    return this.style().getBorderColor();
  }

  public String borderStyle() {
    return this.style().getBorderStyle();
  }

  public String borderWidth() {
    return this.style().getBorderWidth();
  }

  public String bottom() {
    return this.style().getBottom();
  }

  public String clear() {
    return this.style().getClear();
  }

  public String color() {
    return this.style().getColor();
  }

  public String cursor() {
    return this.style().getCursor();
  }

  public String display() {
    return this.style().getDisplay();
  }

  public String fontSize() {
    return this.style().getFontSize();
  }

  public String fontStyle() {
    return this.style().getFontStyle();
  }

  public String fontWeight() {
    return this.style().getFontWeight();
  }

  public String height() {
    return this.style().getHeight();
  }

  public String left() {
    return this.style().getLeft();
  }

  public String lineHeight() {
    return this.style().getLineHeight();
  }

  public String listStyleType() {
    return this.style().getListStyleType();
  }

  public String margin() {
    return this.style().getMargin();
  }

  public String marginBottom() {
    return this.style().getMarginBottom();
  }

  public String marginLeft() {
    return this.style().getMarginLeft();
  }

  public String marginRight() {
    return this.style().getMarginRight();
  }

  public String marginTop() {
    return this.style().getMarginTop();
  }
  
  public String minHeight() {
    return this.style().getProperty("minHeight");
  }
  
  public String maxHeight() {
    return this.style().getProperty("maxHeight");
  }

  public String minWidth() {
    return this.style().getProperty("minWidth");
  }

  public String maxWidth() {
    return this.style().getProperty("maxWidth");
  }

  public String opacity() {
    return this.style().getOpacity();
  }

  public String overflow() {
    return this.style().getOverflow();
  }

  public String overflowX() {
    return this.style().getOverflowX();
  }

  public String overflowY() {
    return this.style().getOverflowY();
  }

  public String padding() {
    return this.style().getPadding();
  }

  public String paddingBottom() {
    return this.style().getPaddingBottom();
  }

  public String paddingLeft() {
    return this.style().getPaddingLeft();
  }

  public String paddingRight() {
    return this.style().getPaddingRight();
  }

  public String paddingTop() {
    return this.style().getPaddingTop();
  }

  public String position() {
    return this.style().getPosition();
  }

  public String property(String name) {
    return this.style().getProperty(name);
  }

  public String right() {
    return this.style().getRight();
  }

  public String tableLayout() {
    return this.style().getTableLayout();
  }

  public String textAlign() {
    return this.style().getTextAlign();
  }

  public String textDecoration() {
    return this.style().getTextDecoration();
  }

  public String textIndent() {
    return this.style().getTextIndent();
  }

  public String textJustify() {
    return this.style().getTextJustify();
  }

  public String textOverflow() {
    return this.style().getTextOverflow();
  }

  public String textTransform() {
    return this.style().getTextTransform();
  }

  public String top() {
    return this.style().getTop();
  }

  public String verticalAlign() {
    return this.style().getVerticalAlign();
  }

  public String visibility() {
    return this.style().getVisibility();
  }

  public String whiteSpace() {
    return this.style().getWhiteSpace();
  }

  public String width() {
    return this.style().getWidth();
  }

  public String zIndex() {
    return this.style().getZIndex();
  }

  public Style backgroundColor(String value) {
    this.style().setBackgroundColor(value);
    return this;
  }

  public Style backgroundImage(String value) {
    this.style().setBackgroundImage(value);
    return this;
  }

  public Style borderColor(String value) {
    this.style().setBorderColor(value);
    return this;
  }

  public Style borderStyle(BorderStyle value) {
    this.style().setBorderStyle(value);
    return this;
  }

  public Style borderWidth(double value, Unit unit) {
    this.style().setBorderWidth(value, unit);
    return this;
  }

  public Style bottom(double value, Unit unit) {
    this.style().setBottom(value, unit);
    return this;
  }

  public Style clear(Clear value) {
    this.style().setClear(value);
    return this;
  }

  public Style color(String value) {
    this.style().setColor(value);
    return this;
  }

  public Style cursor(Cursor value) {
    this.style().setCursor(value);
    return this;
  }

  public Style display(Display value) {
    this.style().setDisplay(value);
    return this;
  }
  
  public Style display(String value){
    this.style().setProperty("display", value);
    return this;
  }

  public Style floating(Float value) {
    this.style().setFloat(value);
    return this;
  }

  public Style fontSize(double value, Unit unit) {
    this.style().setFontSize(value, unit);
    return this;
  }

  public Style fontStyle(FontStyle value) {
    this.style().setFontStyle(value);
    return this;
  }

  public Style fontWeight(FontWeight value) {
    this.style().setFontWeight(value);
    return this;
  }

  public Style height(double value, Unit unit) {
    this.style().setHeight(value, unit);
    return this;
  }

  public Style left(double value, Unit unit) {
    this.style().setLeft(value, unit);
    return this;
  }

  public Style lineHeight(double value, Unit unit) {
    this.style().setLineHeight(value, unit);
    return this;
  }

  public Style listStyleType(ListStyleType value) {
    this.style().setListStyleType(value);
    return this;
  }

  public Style margin(double value, Unit unit) {
    this.style().setMargin(value, unit);
    return this;
  }

  public Style marginBottom(double value, Unit unit) {
    this.style().setMarginBottom(value, unit);
    return this;
  }

  public Style marginLeft(double value, Unit unit) {
    this.style().setMarginLeft(value, unit);
    return this;
  }

  public Style marginRight(double value, Unit unit) {
    this.style().setMarginRight(value, unit);
    return this;
  }

  public Style marginTop(double value, Unit unit) {
    this.style().setMarginTop(value, unit);
    return this;
  }

  public Style minHeight(double value, Unit unit) {
    this.style().setProperty("minHeight", value, unit);
    return this;
  }
  
  public Style maxHeight(double value, Unit unit) {
    this.style().setProperty("maxHeight", value, unit);
    return this;
  }

  public Style minWidth(double value, Unit unit) {
    this.style().setProperty("minWidth", value, unit);
    return this;
  }

  public Style maxWidth(double value, Unit unit) {
    this.style().setProperty("maxWidth", value, unit);
    return this;
  }

  public Style opacity(double value) {
    this.style().setOpacity(value);
    return this;
  }

  public Style outlineColor(String value) {
    this.style().setOutlineColor(value);
    return this;
  }

  public Style outlineStyle(OutlineStyle value) {
    this.style().setOutlineStyle(value);
    return this;
  }

  public Style outlineWidth(double value, Unit unit) {
    this.style().setOutlineWidth(value, unit);
    return this;
  }

  public Style overflow(Overflow value) {
    this.style().setOverflow(value);
    return this;
  }

  public Style overflowX(Overflow value) {
    this.style().setOverflowX(value);
    return this;
  }

  public Style overflowY(Overflow value) {
    this.style().setOverflowY(value);
    return this;
  }

  public Style padding(double value, Unit unit) {
    this.style().setPadding(value, unit);
    return this;
  }

  public Style paddingBottom(double value, Unit unit) {
    this.style().setPaddingBottom(value, unit);
    return this;
  }

  public Style paddingLeft(double value, Unit unit) {
    this.style().setPaddingLeft(value, unit);
    return this;
  }

  public Style paddingRight(double value, Unit unit) {
    this.style().setPaddingRight(value, unit);
    return this;
  }

  public Style paddingTop(double value, Unit unit) {
    this.style().setPaddingTop(value, unit);
    return this;
  }

  public Style position(Position value) {
    this.style().setPosition(value);
    return this;
  }

  public Style right(double value, Unit unit) {
    this.style().setRight(value, unit);
    return this;
  }

  public Style tableLayout(TableLayout value) {
    this.style().setTableLayout(value);
    return this;
  }

  public Style textAlign(TextAlign value) {
    this.style().setTextAlign(value);
    return this;
  }

  public Style textDecoration(TextDecoration value) {
    this.style().setTextDecoration(value);
    return this;
  }

  public Style textIndent(double value, Unit unit) {
    this.style().setTextIndent(value, unit);
    return this;
  }

  public Style textJustify(TextJustify value) {
    this.style().setTextJustify(value);
    return this;
  }

  public Style textOverflow(TextOverflow value) {
    this.style().setTextOverflow(value);
    return this;
  }

  public Style textTransform(TextTransform value) {
    this.style().setTextTransform(value);
    return this;
  }

  public Style top(double value, Unit unit) {
    this.style().setTop(value, unit);
    return this;
  }

  public Style verticalAlign(VerticalAlign value) {
    this.style().setVerticalAlign(value);
    return this;
  }

  public Style verticalAlign(double value, Unit unit) {
    this.style().setVerticalAlign(value, unit);
    return this;
  }

  public Style visibility(Visibility value) {
    this.style().setVisibility(value);
    return this;
  }

  public Style whiteSpace(WhiteSpace value) {
    this.style().setWhiteSpace(value);
    return this;
  }

  public Style width(double value, Unit unit) {
    this.style().setWidth(value, unit);
    return this;
  }

  public Style property(String style, String value) {
    this.style().setProperty(style, value);
    return this;
  }

  public Style zIndex(int value) {
    this.style().setZIndex(value);
    return this;
  }

  public int computePropertyInt(String name) {
    String value = this.computeProperty(this.widget.element(), name);

    if (value == null || value.trim().isEmpty() || value.contains("auto")) {
      return 0;
    }

    for (Unit unit : Unit.values()) {
      value = value.replace(unit.getType(), "");
    }

    if (value.contains(" ")) {
      value = value.split(" ")[0];
    }

    return Integer.valueOf(value);
  }

  public String computeProperty(String name) {
    return this.computeProperty(this.widget.element(), name);
  }

  native String computeProperty(Element element, String name)/*-{
		if (element.currentStyle){
			return element.currentStyle[name]
		}
		if (document.defaultView && document.defaultView.getComputedStyle){
			return document.defaultView.getComputedStyle(element, "")[name]
		}
		
		return element.style[name]
  }-*/;

  private com.google.gwt.dom.client.Style style() {
    return this.widget.element().getStyle();
  }
}