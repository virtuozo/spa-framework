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
import virtuozo.interfaces.Component;
import virtuozo.interfaces.CssClass;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.resources.client.ImageResource;

public final class Image extends Component<Image> {

  public static Image create(){
    return new Image();
  }
  
  private Image() {
    super(Elements.img());
  }

  public Image shape(Shape shape) {
    this.css().set(shape);
    return this;
  }

  public Image src(ImageResource resource) {
    return this.src(resource.getSafeUri().asString());
  }

  public Image src(String url) {
    this.element().setSrc(url);

    return this;
  }
  
  public Image responsive(){
	  this.css("img-responsive");
	  return this;
  }

  protected ImageElement element() {
    return super.element();
  }

  public static class Shape extends CssClass {
    private Shape(String name) {
      super(name);
    }
    
    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }

    public static final Shape ROUNDED = new Shape("img-rounded");
    public static final Shape CIRCLE = new Shape("img-circle");
    public static final Shape THUMBNAIL = new Shape("img-thumbnail");
    private static final StyleChooser STYLES = new StyleChooser(ROUNDED, CIRCLE, THUMBNAIL);
  }
}