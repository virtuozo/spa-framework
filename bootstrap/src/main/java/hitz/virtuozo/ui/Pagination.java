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

import hitz.virtuozo.ui.api.Assets;
import hitz.virtuozo.ui.api.PageChangeEvent;
import hitz.virtuozo.ui.api.PageChangeEvent.PageChangeHandler;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class Pagination extends Component<Pagination> {
  private Assets assets = GWT.create(Assets.class);

  private PaginationItem previous;

  private PaginationItem next;

  private PaginationItem active;

  private OrderList list = new OrderList(OrderList.Type.UNORDERED);

  public Pagination() {
    this.incorporate(this.list);
    this.css().set("pagination");
  }
  
  public Pagination pages(int pages) {
    this.detachChildren();

    if (this.previous == null) {
      this.previous = new PaginationItem(this.list.addItem());
      this.previous.icon(this.assets.previousIcon());
      this.previous.onClick(new ClickHandler() {
        public void onClick(ClickEvent event) {
          Pagination.this.previous();
        }
      });
    }

    this.previous.disable();
    this.addChild(this.previous);

    for (int i = 0; i < pages; i++) {
      final PaginationItem item = new PaginationItem(this.list.addItem());
      item.text(String.valueOf(i + 1));
      item.onClick(new ClickHandler() {
        public void onClick(ClickEvent event) {
          if (!item.disabled()) {
            Pagination.this.active.enable();
            Pagination.this.next.enable();
            Pagination.this.previous.enable();
            Pagination.this.active = item;
            Pagination.this.run(0);
          }
        }
      });
      this.addChild(item);
    }

    if (this.next == null) {
      this.next = new PaginationItem(this.list.addItem());
      this.next.icon(this.assets.nextIcon());
      this.next.onClick(new ClickHandler() {
        public void onClick(ClickEvent event) {
          Pagination.this.next();
        }
      });
    }

    this.addChild(this.next);
    this.active = this.childAt(1);
    this.active.activate().disable();
    return this;
  }

  public int currentPage() {
    return this.indexOfChild(this.active);
  }
  
  public Pagination onPageChange(PageChangeHandler handler){
    return this.addHandler(PageChangeEvent.TYPE, handler);
  }

  public Pagination previous() {
    this.next.enable();
    if(this.previous.disabled()){
      return this;
    }
    return this.run(-1);
  }

  public Pagination next() {
    this.previous.enable();
    if(this.next.disabled()){
      return this;
    }
    return this.run(1);
  }

  Pagination run(int direction) {
    PaginationItem selection = this.childAt(this.currentPage() + direction);
    int selectionIdx = this.indexOfChild(selection);
    
    if((selectionIdx - 1) == this.indexOfChild(this.previous)){
      this.previous.disable();
    }
    
    if((selectionIdx + 1) == this.indexOfChild(this.next)){
      this.next.disable();
    }
    
    this.active.deactivate().enable();
    selection.disable();

    this.active = selection;
    this.fireEvent(new PageChangeEvent(Integer.valueOf(this.active.text())));

    return this;
  }

  public static class Size extends CssClass {
    private Size(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }
    
    public static final Size LARGE = new Size("pagination-lg");
    public static final Size MEDIUM = new Size("pagination-md");
    public static final Size SMALL = new Size("pagination-sm");
    private static final StyleChooser STYLES = new StyleChooser(LARGE, MEDIUM, SMALL);
  }
}