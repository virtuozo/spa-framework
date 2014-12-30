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

import virtuozo.ui.api.HasClickHandlers;
import virtuozo.ui.api.UIComponent;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;

public class Media extends Component<Media> {
	private Object object = new Object();

	private Body body = new Body();

	Media(Floating floating) {
		super(Elements.div());
		this.init(floating);
	}

	Media(ListItem item, Floating floating) {
		this.incorporate(item);
		this.init(floating);
	}

	private void init(Floating floating) {
		this.css().set("media");
		this.addChild(this.object).addChild(this.body);

		if(floating.equals(Floating.RIGHT)){
			this.addChild(this.object.detach());
		}
		this.object.css(floating);
	}
	
	public Media css(Alignment alignment) {
		this.object.css(alignment);
		return this;
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
			return this.addMedia(Floating.LEFT);
		}

		public Media addMedia(Floating floating) {
			Media media = new Media(floating);
			this.add(media);
			return media;
		}
	}

	public class Object extends Component<Object> implements
			HasClickHandlers<Object> {

		public Object() {
			super(Elements.a());
			this.element().setHref("javascript:void(0)");
			this.css(Floating.LEFT).css(Alignment.TOP);
		}
		
		public Object add(UIComponent widget) {
			this.add(widget);
			widget.asComponent().css("media-object");
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
	
	public static class Alignment extends CssClass{
		private Alignment(String name) {
			super(name);
		}

		@Override
		protected StyleChooser chooser() {
			return STYLES;
		}

		public static final Alignment TOP = new Alignment("media-top");

		public static final Alignment MIDDLE = new Alignment("media-middle");
		
		public static final Alignment BOTTOM = new Alignment("media-bottom");

		private static final StyleChooser STYLES = new StyleChooser(TOP, MIDDLE, BOTTOM);
	}

	public static class Floating extends CssClass {
		private Floating(String name) {
			super(name);
		}

		@Override
		protected StyleChooser chooser() {
			return STYLES;
		}
		
		public static final Floating LEFT = new Floating("media-left");

		public static final Floating RIGHT = new Floating("media-right");

		private static final StyleChooser STYLES = new StyleChooser(LEFT, RIGHT);
	}
}
