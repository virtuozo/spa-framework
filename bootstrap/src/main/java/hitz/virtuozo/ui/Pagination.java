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

import hitz.virtuozo.infra.api.EventHandler;
import hitz.virtuozo.infra.api.EventType;
import hitz.virtuozo.ui.CssClass;
import hitz.virtuozo.ui.Event;
import hitz.virtuozo.ui.StyleChooser;
import hitz.virtuozo.ui.Widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class Pagination extends Widget<Pagination> {
  private PaginationItem previous;

  private PaginationItem next;

  private PaginationItem active;

  private OrderList list = new OrderList(OrderList.Type.UNORDERED);

  public Pagination() {
    this.compound(this.list);
    this.css().set("pagination");
  }
  
  public PaginationItem previousItem(){
    return this.previous;
  }
  
  public PaginationItem nextItem(){
    return this.next;
  }

  public Pagination pages(int pages) {
    this.detachChildren();

    if (this.previous == null) {
      this.previous = new PaginationItem(this.list.addItem());
      this.previous.icon(Glyphicon.CHEVRON_LEFT);
      this.previous.onClick(new ClickHandler() {
        public void onClick(ClickEvent event) {
          Pagination.this.doPrevious();
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
      this.next.icon(Glyphicon.CHEVRON_RIGHT);
      this.next.onClick(new ClickHandler() {
        public void onClick(ClickEvent event) {
          Pagination.this.doNext();
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
  
  public Pagination onPageChange(EventHandler<Integer> handler){
    return this.addHandler(FireableEvent.PAGINATE, handler);
  }

  protected Pagination doPrevious() {
    this.next.enable();
    if(this.previous.disabled()){
      return this;
    }
    return this.run(-1);
  }

  protected Pagination doNext() {
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
    this.fireEvent(new Event<Integer>(FireableEvent.PAGINATE, this, Integer.valueOf(this.active.text())));

    return this;
  }

  static enum FireableEvent implements EventType{
    PAGINATE;
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