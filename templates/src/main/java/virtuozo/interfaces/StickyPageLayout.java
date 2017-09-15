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

import com.google.gwt.dom.client.StyleElement;
import com.google.gwt.dom.client.StyleInjector;

public class StickyPageLayout implements Layout<StickyPageLayout> {
  private Body body = new Body();
  private Footer footer = new Footer();
  private StyleElement style;
  
  public static StickyPageLayout create(){
    return new StickyPageLayout();
  }
  
  private StickyPageLayout() {
    super();
  }
  
  public StickyPageLayout attach() {
    StringBuilder styleCss = new StringBuilder();
    styleCss.append("html { position: relative; min-height: 100%;}");
    styleCss.append("body { margin-bottom: 60px;}");
    styleCss.append("#page-layout-footer { position: absolute; bottom: 0; width: 100%;}");
    styleCss.append("#page-layout-body { height: 100%; width: 100%;}");
    
    this.style = StyleInjector.injectStylesheet(styleCss.toString());
    HTML.body().addChild(this.body).addChild(this.footer);
  
    return this;
  }
  
  @Override
  public StickyPageLayout detach() {
    HTML.body().detachChildren();
    this.body.detachChildren();
    this.footer.detachChildren();
    this.style.removeFromParent();
  
    return this;
  }

  public Body body() {
    return this.body;
  }

  public Footer footer() {
    return this.footer;
  }

  public class Body extends Composite<Body> {
    Body() {
      super(Elements.div());
      this.id("page-layout-body");
    }
  }

  public class Footer extends Composite<Footer> {
    Footer() {
      super(Elements.div());
      this.id("page-layout-footer");
    }
  }
}