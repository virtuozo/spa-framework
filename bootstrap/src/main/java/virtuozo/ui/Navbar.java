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

import virtuozo.ui.css.State;
import virtuozo.ui.events.ActivationEvent;
import virtuozo.ui.events.ActivationEvent.ActivationHandler;
import virtuozo.ui.events.DeactivationEvent;
import virtuozo.ui.events.DeactivationEvent.DeactivationHandler;
import virtuozo.ui.events.DetachChildrenEvent;
import virtuozo.ui.interfaces.HasActivation;
import virtuozo.ui.interfaces.HasClickHandlers;
import virtuozo.ui.interfaces.HasIcon;
import virtuozo.ui.interfaces.HasText;
import virtuozo.ui.interfaces.Icon;
import virtuozo.ui.interfaces.UIComponent;
import virtuozo.ui.interfaces.UIInput;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;

public class Navbar extends Component<Navbar> {
  private Container container = Container.fluid();

  private Header header = new Header();

  private Collapse collapse = new Collapse();

  private Facet left = new Facet();
  
  private FormFacet leftForm = new FormFacet();
  
  private FormFacet rightForm = new FormFacet();

  private Facet right = new Facet();

  private ActivationHelper activationHelper = ActivationHelper.create();

  public static Navbar create(){
    return new Navbar();
  }
  
  private Navbar() {
    super(Elements.create("nav"));
    this.role("navigation").css().set("navbar", "navbar-default");
    this.addChild(this.container);
    this.container.addChild(this.header).addChild(this.collapse);
    this.collapse.addChild(this.left).addChild(this.leftForm).addChild(this.rightForm).addChild(this.right);
    this.left.css("navbar-left");
    this.leftForm.css("navbar-left");
    this.rightForm.css("navbar-right");
    this.right.css("navbar-right");
  }
  
  @Override
  protected Navbar detachChildren() {
    this.left.detachChildren();
    this.leftForm.detachChildren();
    this.right.detachChildren();
    this.rightForm.detachChildren();
    this.header.brand.detachChildren();
    this.fireEvent(new DetachChildrenEvent());
    return this;
  }

  public Brand brand() {
    return this.header.brand;
  }

  public Facet leftFacet() {
    return this.left.show();
  }
  
  public FormFacet leftForm() {
    return this.leftForm.show();
  }

  public Facet rightFacet() {
    return this.right.show();
  }
  
  public FormFacet rightForm() {
    return this.rightForm.show();
  }
  
  public class FormFacet extends Component<FormFacet>{
    public FormFacet() {
      super(Elements.div());
      this.hide().css().set("nav", "navbar-form");
    }
    
    public <I extends UIInput<?, V>, V> FormFacet addInput(I input){
      Tag<DivElement> group = Tag.asDiv().css("form-group");
      group.add(input.asComponent().css("form-control"));
      return this.addChild(group);
    }
    
    public Button addButton(){
      Button button = Button.create();
      this.addChild(button);
      return button;
    }
  }

  public class Facet extends Component<Facet> {
    public Facet() {
      super(Elements.div());
      this.hide().css().set("nav");
    }

    public Paragraph addText() {
      Paragraph text = Paragraph.create();
      this.add(text, "navbar-text");
      return text;
    }

    public Button addButton() {
      Button button = Button.create();
      this.add(button, "navbar-btn");

      return button;
    }

    public DropItem addDropItem() {
      return new DropItem(this.createNav().addItem());
    }
    
    public NavItem addItem() {
      OrderList nav = this.createNav();

      NavItem item = new NavItem(nav.addItem());
      Navbar.this.activationHelper.add(item);
      return item;
    }

    private OrderList createNav() {
      OrderList nav = OrderList.unordered();
      nav.css("nav", "navbar-nav");
      this.addChild(nav);

      return nav;
    }

    private Facet add(UIComponent widget, String clazz) {
      widget.asComponent().css(clazz);
      return this.addChild(widget);
    }
    
    public class NavItem extends Component<NavItem> implements HasText<NavItem>, HasClickHandlers<NavItem>, HasActivation<NavItem> {
      private Tag<AnchorElement> anchor = Tag.asAnchor();

      NavItem(ListItem item) {
        super(item);
        this.addChild(this.anchor);
      }
      
      @Override
      public NavItem onActivate(ActivationHandler handler) {
        return this.addHandler(ActivationEvent.TYPE, handler);
      }
      
      @Override
      public NavItem onDeactivate(DeactivationHandler handler) {
        return this.addHandler(DeactivationEvent.TYPE, handler);
      }

      @Override
      public NavItem onClick(ClickHandler handler) {
        this.anchor.onClick(handler);
        return this;
      }

      @Override
      public NavItem onDoubleClick(DoubleClickHandler handler) {
        this.anchor.onDoubleClick(handler);
        return this;
      }
      
      public NavItem target(UIComponent target){
        this.anchor.element().setHref("#" + target.asComponent().id());
        return this;
      }

      @Override
      public String text() {
        return this.anchor.text();
      }

      @Override
      public NavItem text(String text) {
        this.anchor.text(text);
        return this;
      }

      @Override
      public NavItem activate() {
        this.css(State.ACTIVE);
        return this;
      }

      @Override
      public NavItem deactivate() {
        this.css().remove(State.ACTIVE);
        return this;
      }

      @Override
      public boolean active() {
        return this.css().contains(State.ACTIVE);
      }

      @Override
      public boolean match(Element element) {
        return this.anchor.id().equals(element.getId());
      }
    }
  }

  class Collapse extends Component<Collapse> {
    public Collapse() {
      super(Elements.div());
      this.css().set("collapse", "navbar-collapse");
    }
  }

  class Header extends Component<Header> {
    private Button toggle = Button.create();

    private Brand brand = new Brand();

    public Header() {
      super(Elements.div());
      this.css().set("navbar-header");
      this.addChild(this.toggle).addChild(this.brand);
      this.toggle.css().set("navbar-toggle");
      this.toggle.addChild(new IconBar()).addChild(new IconBar()).addChild(new IconBar());
      this.toggle.onClick(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          Navbar.this.collapse.css().toggle("in");
        }
      });
    }

    class IconBar extends Component<IconBar> {
      public IconBar() {
        super(Elements.span());
        this.css().set("icon-bar");
      }
    }
  }

  public class Brand extends Component<Brand> implements HasText<Brand>, HasIcon<Brand>, HasClickHandlers<Brand> {
    public Brand() {
      super(Elements.a());
      this.element().setHref("javascript:void(0)");
      this.css().set("navbar-brand");
    }

    @Override
    public Brand onClick(ClickHandler handler) {
      return this.on(handler);
    }

    @Override
    public Brand onDoubleClick(DoubleClickHandler handler) {
      return this.on(handler);
    }

    public Brand image(Image image) {
      return this.addChild(image);
    }

    @Override
    public Brand icon(Icon icon) {
      icon.attachTo(this);
      return this;
    }

    @Override
    public String text() {
      return this.element().getInnerText();
    }

    @Override
    public Brand text(String text) {
      this.element().setInnerText(text);
      return this;
    }

    public AnchorElement element() {
      return super.element();
    }
  }

  public static class Type extends CssClass {
    private Type(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }

    public static final Type DEFAULT = new Type("navbar-default");
    public static final Type INVERSE = new Type("navbar-inverse");
    private static final StyleChooser STYLES = new StyleChooser(DEFAULT, INVERSE);
  }

  public static class Placement extends CssClass {
    private Placement(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }

    public static final Placement TOP = new Placement("navbar-fixed-top");
    public static final Placement BOTTOM = new Placement("navbar-fixed-bottom");
    public static final Placement STATIC = new Placement("navbar-static-top");
    private static final StyleChooser STYLES = new StyleChooser(TOP, BOTTOM, STATIC);
  }
}