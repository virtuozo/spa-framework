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
import virtuozo.infra.StyleChooser;


public final class Container extends Parent<Container, Row> {

  public static Container fixed() {
    return new Container(Type.FIXED);
  }
  
  public static Container fluid() {
    return new Container(Type.FLUID);
  }
  
  private Container(Type type) {
    super(Elements.div());
    this.css().set(type);
  }

  public Row addRow() {
    Row row = Row.create();
    this.addChild(row);
    return row;
  }
  
  static class Type extends CssClass {
    private Type(String name) {
      super(name);
    }
    
    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }

    static final Type FIXED = new Type("container");
    static final Type FLUID = new Type("container-fluid");
    static final StyleChooser STYLES = new StyleChooser(FIXED, FLUID);
  }
}