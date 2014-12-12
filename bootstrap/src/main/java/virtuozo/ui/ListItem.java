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

import virtuozo.ui.OrderList.Type;
import virtuozo.ui.api.HasText;

public final class ListItem extends Composite<ListItem> implements HasText<ListItem>{
  private Text text = new Text();
  
  public ListItem() {
    super(Elements.li());
    this.add(this.text);
  }
  
  public OrderList addList(Type type){
    OrderList list = new OrderList(type);
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