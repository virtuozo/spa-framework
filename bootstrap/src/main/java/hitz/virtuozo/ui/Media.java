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

import hitz.virtuozo.infra.api.HasClickHandlers;
import hitz.virtuozo.ui.Composite;
import hitz.virtuozo.ui.Elements;
import hitz.virtuozo.ui.Widget;
import hitz.virtuozo.ui.api.UIWidget;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;

public class Media extends Widget<Media> {
  private Object object = new Object();

  private Body body = new Body();

  public Media() {
    super(Elements.div());
    this.init();
  }

  Media(ListItem item) {
    this.incorporate(item);
    this.init();
  }

  private void init() {
    this.addChild(this.object).addChild(this.body);
    this.css().set("media");
  }

  public Object object() {
    return this.object;
  }

  public Body body() {
    return this.body;
  }

  public class Body extends Composite<Body> {
    public Body() {
      super(Elements.div());
      this.css().set("media-body");
    }

    public Heading addHeading() {
      Heading heading = new Heading(Heading.Level.FOUR);
      heading.css().set("media-heading");
      this.addChild(heading);
      return heading;
    }

    public Paragraph addText() {
      Paragraph text = new Paragraph();
      this.add(text);
      return text;
    }
    
    public Media addMedia() {
      Media media = new Media();
      this.add(media);
      return media;
    }
  }

  public class Object extends Widget<Object> implements HasClickHandlers<Object> {

    public Object() {
      super(Elements.a());
      this.element().setHref("javascript:void(0)");
      this.css().set(hitz.virtuozo.ui.css.Floating.LEFT);
    }

    public Object add(UIWidget widget) {
      this.add(widget);
      widget.asWidget().css("media-object");
      return this;
    }

    public Image addImage() {
      Image image = new Image();
      this.addChild(image);
      image.css().set("media-object");
      return image;
    }

    @Override
    public Object onClick(ClickHandler handler) {
      return this.on(handler);
    }

    @Override
    public Object onDoubleClick(DoubleClickHandler handler) {
      return this.on(handler);
    }

    public AnchorElement element() {
      return super.element();
    }
  }
}
