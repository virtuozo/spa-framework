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

import virtuozo.ui.api.Clause;
import virtuozo.ui.api.DetachChildrenEvent.DetachChildrenHandler;
import virtuozo.ui.api.HasComponents;
import virtuozo.ui.api.UIComponent;

import com.google.gwt.dom.client.Element;

@SuppressWarnings("unchecked")
public abstract class Composite<C extends Composite<C>> extends Component<C> implements HasComponents<C, UIComponent> {

  public Composite() {
    super();
  }

  public Composite(Element element) {
    super(element);
  }

  public Composite(Component<?> widget) {
    super(widget);
  }
  
  @Override
  public C onDetachChildren(DetachChildrenHandler handler) {
    return super.onDetachChildren(handler);
  }

  @Override
  public C detachChildren() {
    return super.detachChildren();
  }
  
  @Override
  public C add(UIComponent add) {
    return super.addChild(add);
  }
  
  @Override
  public C addFirstChild(UIComponent add) {
    return super.addFirstChild(add);
  }

  @Override
  public C adopt(UIComponent child) {
    return super.adoptChild(child);
  }

  @Override
  public C insert(UIComponent add, UIComponent before) {
    return super.insertChild(add, before);
  }

  @Override
  public Iterable<UIComponent> children() {
    return super.childrenComponents();
  }
  
  @Override
  public C firstChild(UIComponent add) {
    return super.addFirstChild(add);
  }
  
  @Override
  public UIComponent firstChild() {
    return super.firstChild();
  }
  
  @Override
  public UIComponent lastChild() {
    return super.lastChild();
  }

  @Override
  public UIComponent childAt(int index) {
    return super.childAt(index);
  }
  
  @Override
  public UIComponent find(Clause clause) {
    return (UIComponent) super.find(clause);
  }
  
  @Override
  public Iterable<UIComponent> findAll(Clause clause) {
    return super.findAll(clause);
  }

  public int indexOf(UIComponent child) {
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

  public C remove(UIComponent child) {
    return super.removeChild(child);
  }
}