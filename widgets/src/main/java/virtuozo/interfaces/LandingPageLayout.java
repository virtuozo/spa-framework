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
import virtuozo.infra.events.DetachChildrenEvent;
import virtuozo.interfaces.Anchor;
import virtuozo.interfaces.Composite;
import virtuozo.interfaces.Container;
import virtuozo.interfaces.HTML;
import virtuozo.interfaces.Heading;
import virtuozo.interfaces.Icon;
import virtuozo.interfaces.Layout;
import virtuozo.interfaces.Navbar;
import virtuozo.interfaces.Row;
import virtuozo.interfaces.Tag;
import virtuozo.interfaces.Row.Column;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class LandingPageLayout implements Layout<LandingPageLayout> {
  private Navbar bar = Navbar.create();

  private Intro intro = new Intro();

  public static LandingPageLayout create() {
    return new LandingPageLayout();
  }

  private LandingPageLayout() {
    super();
  }

  public Navbar navbar() {
    return this.bar;
  }

  public Intro intro() {
    return this.intro;
  }

  public Section addSection() {
    Section section = new Section();
    HTML.body().add(section);
    return section;
  }

  public LandingPageLayout attach() {
    HTML.body().addChild(this.bar).addChild(this.intro);

    StyleInjector.inject("html { width: 100%; height: 100%; } body { width: 100%; height: 100%; position: relative;}");
    StyleInjector.inject("*, *:after, *::before { -webkit-box-sizing: border-box; -moz-box-sizing: border-box; box-sizing: border-box;}");
    StyleInjector.inject(".intro { width:100%; position:relative; padding:20% 0 0 0;}");
    StyleInjector.inject(".intro .slogan { text-align: center;}");
    StyleInjector.inject(".intro .page-scroll { text-align: center; }");
    StyleInjector.inject(".intro-text { font-size: 18px; }");
    StyleInjector.inject("@media(min-width:767px) { .intro { height: 100%; } .intro-text { font-size: 25px; }}");
    StyleInjector.inject("section { padding-top: 5%; padding-bottom: 5%; display:block; position:relative; z-index:120; }");
    StyleInjector.inject("section::before, section::after { position: absolute; content: ''; pointer-events: none;}");
    
    return this;
  }

  @Override
  public LandingPageLayout detach() {
    HTML.body().detachChildren();
    this.bar.detachChildren();
    this.intro.detachChildren();
    return this;
  }

  public class Intro extends Section {
    private Slogan slogan = new Slogan();

    private Anchor link = Anchor.create().css("btn btn-circle");

    private Intro() {
      Tag<DivElement> scrollTo = Tag.asDiv().css("page-scroll");
      scrollTo.add(this.link);
      this.css("intro").add(this.slogan).add(scrollTo);
    }

    @Override
    public Section detachChildren() {
      this.slogan.detachChildren();
      this.link.detachChildren();
      return this.fireEvent(new DetachChildrenEvent());
    }

    public Intro scrollTo(final Section section, Icon icon) {
      this.link.onClick(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          section.scrollTo();
        }
      });
      icon.attachTo(this.link);
      return this;
    }

    public Slogan slogan() {
      return slogan;
    }

    public class Slogan extends Composite<Slogan> {
      private Slogan() {
        super(Elements.div());
        this.css("slogan");
      }
    }
  }

  public class Section extends Composite<Section> {
    private Row row;

    private Heading heading = Heading.one();

    private Section() {
      super(Elements.create("section"));
      this.init();
    }

    private void init() {
      Container container = Container.fluid();
      this.row = container.addRow();
      this.add(this.heading).add(container);
    }

    public Heading heading() {
      return this.heading;
    }

    public Column addColumn() {
      return this.row.addColumn();
    }
  }
}