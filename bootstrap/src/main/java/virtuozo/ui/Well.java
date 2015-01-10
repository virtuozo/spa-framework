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


public class Well extends Composite<Well> {
  public static Well create(){
    return new Well();
  }
  
  private Well() {
    super(Elements.div());
    this.css().set("well");
  }
  
  public static class Size extends CssClass{
    private Size(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }
    
    public static final Size LARGE = new Size("well-lg");
    public static final Size MEDIUM = new Size("well-md");
    public static final Size SMALL = new Size("well-sm");
    private static final StyleChooser STYLES = new StyleChooser(LARGE, MEDIUM, SMALL);
  }
}