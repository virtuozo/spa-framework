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

import virtuozo.infra.Elements;
import virtuozo.interfaces.OrderList.ListItem;

import com.google.gwt.dom.client.Element;

public final class OrderList extends Parent<OrderList, ListItem> {

  public static OrderList ordered(){
    return new OrderList(Type.ORDERED);
  }
  
  public static OrderList unordered(){
    return new OrderList(Type.UNORDERED);
  }
  
  private OrderList(Type option) {
    super(option.resolveElement());
  }
  
  public ListItem addItem() {
    ListItem item = new ListItem();
    this.add(item);
    return item;
  }
  
  public static class ListItem extends Composite<ListItem> implements HasText<ListItem>{
    private Text text = Text.create();
    
    ListItem() {
      super(Elements.li());
      this.add(this.text);
    }
    
    public OrderList addUnorderedList(){
      OrderList list = OrderList.unordered();
      this.addChild(list);
      return list;
    }
    
    public OrderList addOrderedList(){
      OrderList list = OrderList.ordered();
      this.addChild(list);
      return list;
    }
    
    @Override
    public String text() {
      return this.text.text();
    }
    
    public ListItem text(String text) {
      this.text.text(text);
      return this;
    }
  }
  
  static enum Type {
    UNORDERED, ORDERED;

    Element resolveElement() {
      if (this.equals(UNORDERED)) {
        return Elements.ul();
      }

      return Elements.ol();
    }
  }
}