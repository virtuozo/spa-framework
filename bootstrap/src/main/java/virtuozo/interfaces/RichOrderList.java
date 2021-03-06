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

import virtuozo.infra.StyleChooser;
import virtuozo.interfaces.OrderList.ListItem;
import virtuozo.interfaces.OrderList.Type;

public final class RichOrderList extends Parent<RichOrderList, ListItem> {

  public static RichOrderList ordered(){
    return new RichOrderList(Type.ORDERED);
  }
  
  public static RichOrderList unordered(){
    return new RichOrderList(Type.UNORDERED);
  }
  
  private RichOrderList(Type option) {
    super(option.resolveElement());
  }
  
  public ListItem addItem() {
    ListItem item = new ListItem();
    this.add(item);
    return item;
  }
  
  public static class Style extends CssClass{
    private Style(String name) {
      super(name);
    }
    
    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }
    
    public static final Style UNSTYLED = new Style("list-unstyled");
    public static final Style INLINE = new Style("list-inline");
    private static final StyleChooser STYLES = new StyleChooser(UNSTYLED, INLINE);
  }
}