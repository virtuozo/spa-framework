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

import hitz.virtuozo.infra.CastIterable;
import hitz.virtuozo.ui.api.UIWidget;

import com.google.gwt.dom.client.Element;

@SuppressWarnings("unchecked")
public abstract class Parent<P extends Parent<P, C>, C extends UIWidget> extends Widget<P> {

  public Parent() {
    super();
  }

  public Parent(Element element) {
    super(element);
  }

  public Parent(Widget<?> widget) {
    super(widget);
  }

  @Override
  public P detachChildren() {
    return super.detachChildren();
  }

  public P add(C add) {
    return super.addChild(add);
  }

  public P adopt(C child) {
    return super.adoptChild(child);
  }

  public P insert(C add, C before) {
    return super.insertChild(add, before);
  }

  public Iterable<C> children() {
    return new CastIterable<C, UIWidget>(super.childrenWidgets());
  }

  @Override
  public C childAt(int index) {
    return (C) super.childAt(index);
  }

  public int indexOf(C child) {
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

  public P remove(C child) {
    return super.removeChild(child);
  }
}