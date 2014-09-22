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
package hitz.virtuozo.ui;

import hitz.virtuozo.ui.api.Clause;
import hitz.virtuozo.ui.api.UIWidget;

import com.google.gwt.dom.client.Element;

public abstract class Composite<C extends Composite<C>> extends Widget<C> {

  public Composite() {
    super();
  }

  public Composite(Element element) {
    super(element);
  }

  public Composite(Widget<?> widget) {
    super(widget);
  }

  @Override
  public C detachChildren() {
    return super.detachChildren();
  }

  public C add(UIWidget add) {
    return super.addChild(add);
  }

  public C adopt(UIWidget child) {
    return super.adoptChild(child);
  }

  public C insert(UIWidget add, UIWidget before) {
    return super.insertChild(add, before);
  }

  public Iterable<UIWidget> children() {
    return super.childrenWidgets();
  }

  public <W extends UIWidget> W childAt(int index) {
    return super.childAt(index);
  }
  
  @Override
  public <C extends UIWidget> C find(Clause clause) {
    return super.find(clause);
  }
  
  @Override
  public Iterable<UIWidget> findAll(Clause clause) {
    return super.findAll(clause);
  }

  public int indexOf(UIWidget child) {
    return super.indexOfChild(child);
  }

  @Override
  public int childrenCount() {
    return super.childrenCount();
  }

  @Override
  public boolean hasChildren() {
    return super.hasChildren();
  }

  public C remove(UIWidget child) {
    return super.removeChild(child);
  }
}