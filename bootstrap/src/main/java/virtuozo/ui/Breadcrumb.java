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

import virtuozo.ui.Component;
import virtuozo.ui.Tag;
import virtuozo.ui.api.HasClickHandlers;
import virtuozo.ui.api.HasText;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;

public class Breadcrumb extends Component<Breadcrumb> {
  private OrderList breadcrumb = new OrderList(OrderList.Type.ORDERED);

  public Breadcrumb() {
    this.incorporate(this.breadcrumb);
    this.css().set("breadcrumb");
  }

  public BreadcrumbItem addItem() {
    return new BreadcrumbItem(this.breadcrumb.addItem());
  }
  
  public class BreadcrumbItem extends Component<BreadcrumbItem> implements HasText<BreadcrumbItem>, HasClickHandlers<BreadcrumbItem> {
    private Tag<AnchorElement> anchor;

    public BreadcrumbItem(ListItem item) {
      super(item);
      if(item.childrenCount() > 1){
        this.anchor = (Tag) item.childAt(1).asComponent();
        return;
      }
      
      this.anchor = Tag.asAnchor();
      this.addChild(this.anchor);
    }

    @Override
    public BreadcrumbItem onClick(ClickHandler handler) {
      this.anchor.onClick(handler);
      return this;
    }

    @Override
    public BreadcrumbItem onDoubleClick(DoubleClickHandler handler) {
      this.anchor.onDoubleClick(handler);
      return this;
    }

    @Override
    public BreadcrumbItem text(String text) {
      this.anchor.text(text);
      return this;
    }

    @Override
    public String text() {
      return this.anchor.text();
    }
  }
}