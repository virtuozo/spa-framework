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
package virtuozo.interfaces;

import virtuozo.infra.handlers.HasClickHandlers;
import virtuozo.interfaces.Anchor;
import virtuozo.interfaces.Component;
import virtuozo.interfaces.OrderList.ListItem;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;

public class Breadcrumb extends Component<Breadcrumb> {
  private OrderList breadcrumb = OrderList.ordered();

  public static Breadcrumb create(){
    return new Breadcrumb();
  }
  
  private Breadcrumb() {
    this.incorporate(this.breadcrumb);
    this.css().set("breadcrumb");
  }

  public BreadcrumbItem addItem() {
    return new BreadcrumbItem(this.breadcrumb.addItem());
  }
  
  public BreadcrumbLink addLink(){
    return new BreadcrumbLink().attachTo(this.breadcrumb.addItem());
  }
  
  public class BreadcrumbLink extends Component<BreadcrumbLink> implements HasText<BreadcrumbLink>, HasClickHandlers<BreadcrumbLink>{
    private BreadcrumbLink() {
      super(Anchor.create());
    }
    
    @Override
    public BreadcrumbLink onClick(ClickHandler handler) {
      this.on(handler);
      return this;
    }

    @Override
    public BreadcrumbLink onDoubleClick(DoubleClickHandler handler) {
      this.on(handler);
      return this;
    }
    
    @Override
    public BreadcrumbLink text(String text) {
      this.element().setInnerText(text);
      return this;
    }
    
    @Override
    public String text() {
      return this.element().getInnerText();
    }
  }
  
  public class BreadcrumbItem extends Component<BreadcrumbItem> implements HasText<BreadcrumbItem> {

    private BreadcrumbItem(ListItem item) {
      super(item);
    }

    @Override
    public BreadcrumbItem text(String text) {
      this.element().setInnerText(text);
      return this;
    }

    @Override
    public String text() {
      return this.element().getInnerText();
    }
  }
}