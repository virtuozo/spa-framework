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
import virtuozo.interfaces.Row.Column;

import com.google.gwt.dom.client.StyleElement;
import com.google.gwt.dom.client.StyleInjector;

public class LandingPageLayout implements Layout<LandingPageLayout> {
  private Navbar bar = Navbar.create();

  private StyleElement style;
  
  public static LandingPageLayout create() {
    return new LandingPageLayout();
  }

  private LandingPageLayout() {
    super();
  }

  public Navbar navbar() {
    return this.bar;
  }

  public Section addSection() {
    Section section = new Section();
    HTML.body().add(section);
    return section;
  }

  public LandingPageLayout attach() {
    HTML.body().addChild(this.bar);
    
    StringBuilder styleCss = new StringBuilder();
    styleCss.append("html { width: 100%; height: 100%; } body { width: 100%; height: 100%; position: relative;}");
    styleCss.append("*, *:after, *::before { -webkit-box-sizing: border-box; -moz-box-sizing: border-box; box-sizing: border-box;}");
    styleCss.append("@media(min-width:767px) {.intro-text { font-size: 25px; }}");
    styleCss.append("section { padding: 5%; display:block; position:relative; z-index:120; }");
    styleCss.append("section::before, section::after { position: absolute; content: ''; pointer-events: none;}");
    
    this.style = StyleInjector.injectStylesheet(styleCss.toString());
    
    return this;
  }

  @Override
  public LandingPageLayout detach() {
    HTML.body().detachChildren();
    this.style.removeFromParent();
    this.bar.detachChildren();
    return this;
  }

  public class Section extends Composite<Section> {
    private Row row;

    private RichHeading heading = RichHeading.one();

    private Section() {
      super(Elements.create("section"));
      this.init();
    }

    private void init() {
      Container container = Container.fluid();
      this.row = container.addRow();
      this.add(this.heading).add(container);
    }

    public RichHeading heading() {
      return this.heading;
    }

    public Column addColumn() {
      return this.row.addColumn();
    }
  }
}