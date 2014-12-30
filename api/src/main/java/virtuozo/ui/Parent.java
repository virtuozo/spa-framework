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

import virtuozo.infra.CastIterable;
import virtuozo.ui.api.Clause;
import virtuozo.ui.api.DetachChildrenEvent.DetachChildrenHandler;
import virtuozo.ui.api.HasComponents;
import virtuozo.ui.api.UIComponent;

import com.google.gwt.dom.client.Element;

public abstract class Parent<P extends Parent<P, C>, C extends UIComponent> extends Component<P> implements HasComponents<P, C> {

  public Parent() {
    super();
  }

  public Parent(Element element) {
    super(element);
  }

  public Parent(Component<?> widget) {
    super(widget);
  }

  @Override
  public P detachChildren() {
    return super.detachChildren();
  }
  
  @Override
  public P firstChild(C add) {
    return super.addFirstChild(add);
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
    return new CastIterable<C, UIComponent>(super.childrenComponents());
  }
  
  @Override
  public C firstChild() {
    return super.firstChild();
  }
  
  @Override
  public C lastChild() {
    return super.lastChild();
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
  
  @Override
  public C find(Clause clause) {
    return super.find(clause);
  }
  
  @Override
  public Iterable<C> findAll(Clause clause) {
    return super.findAll(clause);
  }
  
  @Override
  public P onDetachChildren(DetachChildrenHandler handler) {
    return super.onDetachChildren(handler);
  }
}