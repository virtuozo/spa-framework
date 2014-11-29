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

import virtuozo.ui.Component;
import virtuozo.ui.Composite;
import virtuozo.ui.CssClass;
import virtuozo.ui.Elements;
import virtuozo.ui.StyleChooser;

public class Panel extends Component<Panel> {
  private Header header = new Header();

  private Body body = new Body();

  private Footer footer = new Footer();

  public Panel() {
    super(Elements.div());
    this.css().set("panel").append(Panel.Color.DEFAULT);
    this.addChild(this.header.hide()).addChild(this.body.hide()).addChild(this.footer.hide());
  }

  public Header header() {
    return this.header.show();
  }

  public Body body() {
    return this.body.show();
  }

  public Footer footer() {
    return this.footer.show();
  }

  public Table addTable() {
    Table table = new Table();
    this.addChild(table);
    return table;
  }

  public ListGroup addListGroup() {
    ListGroup group = new ListGroup();
    this.addChild(group);
    return group;
  }

  public class Header extends Composite<Header> {
    private Heading heading;
    
    public Header() {
      super(Elements.div());
      this.css().set("panel-heading");
      this.heading = new Heading(Heading.Level.THREE).css("panel-title");
      this.add(this.heading);
    }

    public Heading heading() {
      return this.heading;
    }
  }

  public class Body extends Composite<Body> {
    public Body() {
      super(Elements.div());
      this.css().set("panel-body");
    }
  }

  public class Footer extends Composite<Footer> {
    public Footer() {
      super(Elements.div());
      this.css().set("panel-footer");
    }
  }

  public static class Color extends CssClass {
    private Color(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }

    public static final Color PRIMARY = new Color("panel-primary");
    public static final Color SUCCESS = new Color("panel-success");
    public static final Color INFO = new Color("panel-info");
    public static final Color WARNING = new Color("panel-warning");
    public static final Color DANGER = new Color("panel-danger");
    public static final Color DEFAULT = new Color("panel-default");
    private static final StyleChooser STYLES = new StyleChooser(PRIMARY, SUCCESS, INFO, WARNING, DANGER, DEFAULT);
  }
}
