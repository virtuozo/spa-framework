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

import hitz.virtuozo.infra.MessageFormat;
import hitz.virtuozo.ui.api.PageChangeEvent;
import hitz.virtuozo.ui.api.PageChangeEvent.PageChangeHandler;

import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class Pager extends Widget<Pager> {
  private OrderList list = new OrderList(OrderList.Type.UNORDERED);

  private PaginationItem previous = new PaginationItem(this.list.addItem()).icon(Glyphicon.CHEVRON_LEFT).disable();

  private ListItem message = this.list.addItem();

  private PaginationItem next = new PaginationItem(this.list.addItem()).icon(Glyphicon.CHEVRON_RIGHT);

  private int page = 1;

  private int pages;

  private String messageTemplate = "{0} - {1}";

  public Pager() {
    this.compound(this.list);
    this.css().set("pager");

    this.previous.onClick(new ClickHandler() {
      public void onClick(ClickEvent event) {
        Pager.this.doPrevious();
      }
    });

    this.message.css().set("pager-text");

    StyleInjector.inject(".pager-text {padding:6px;}");
    this.next.onClick(new ClickHandler() {
      public void onClick(ClickEvent event) {
        Pager.this.doNext();
      }
    });
  }

  public PaginationItem previousItem() {
    return this.previous;
  }

  public PaginationItem nextItem() {
    return this.previous;
  }

  public Pager block() {
    this.previous.css().set("previous");
    this.next.css().set("next");
    return this;
  }

  public Pager messageTemplate(String template) {
    this.messageTemplate = template;
    return this.count();
  }

  public Pager pages(int pages) {
    this.pages = pages;
    return this.count();
  }

  public int currentPage() {
    return this.page;
  }

  public Pager onPageChange(PageChangeHandler handler) {
    return this.addHandler(PageChangeEvent.TYPE, handler);
  }

  protected Pager doPrevious() {
    this.next.enable();
    return this.run(-1);
  }

  protected Pager doNext() {
    this.previous.enable();
    return this.run(1);
  }

  Pager run(int direction) {
    PaginationItem control = direction > 0 ? this.next : this.previous;

    int newPage = (this.page + direction);

    if (newPage == this.pages || newPage == 1) {
      control.disable();
    }

    if (newPage > 0 && newPage <= this.pages) {
      this.page += direction;
      this.fireEvent(new PageChangeEvent(this.page));
      this.count();
      return this;
    }

    control.disable();
    return this;
  }
  
  public Pager reset() {
    this.page = 1;

    this.previous.disabled();
    this.next.activate();

    return this.count();
  }

  private Pager count() {
    this.message.text(MessageFormat.format(this.messageTemplate, this.page, this.pages));
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