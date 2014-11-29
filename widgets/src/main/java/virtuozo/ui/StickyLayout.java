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

import virtuozo.ui.Composite;
import virtuozo.ui.Elements;
import virtuozo.ui.HTML;
import virtuozo.ui.api.Layout;

import com.google.gwt.dom.client.StyleInjector;

public class StickyLayout implements Layout {
  private Body body = new Body();
  private Footer footer = new Footer();

  public void attach() {
    StyleInjector.inject("html { position: relative; min-height: 100%;}");
    StyleInjector.inject("body { margin-bottom: 60px;}");
    StyleInjector.inject("#page-layout-footer { position: absolute; bottom: 0; width: 100%; height: 10%; background-color: #f5f5f5;}");
    HTML.body().addChild(this.body).addChild(this.footer);
  }
  
  @Override
  public void detach() {
    HTML.body().detachChildren();
    this.body.detachChildren();
    this.footer.detachChildren();
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