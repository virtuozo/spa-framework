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

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.StyleInjector;

import hitz.virtuozo.ui.api.Icon;
import hitz.virtuozo.ui.api.Layout;

public class LandingPageLayout implements Layout {
  private Navbar bar = new Navbar();
  
  private Intro intro = new Intro();

  public Navbar navbar(){
    return this.bar;
  }
  
  public Intro intro(){
    return this.intro;
  }
  
  public Section addSection(){
    Section section = new Section();
    HTML.body().add(section);
    return section;
  }

  public void attach() {
    HTML.body().addChild(this.bar).addChild(this.intro);
    StyleInjector.inject(".intro { width:100%; position:relative; padding:20% 0 0 0;}");
    StyleInjector.inject(".intro .slogan { text-align: center;}");
    StyleInjector.inject(".intro .page-scroll { text-align: center; }");
    StyleInjector.inject(".intro-text { font-size: 18px; }");
    StyleInjector.inject("@media(min-width:767px) { .intro { height: 100%; padding: 0; } .intro-text { font-size: 25px; }}");
  }
  
  public class Intro extends Section{
    private Slogan slogan = new Slogan();
    
    private Tag<AnchorElement> link = Tag.asAnchor().css("btn btn-circle");
    
    public Intro() {
      Tag<DivElement> scrollTo = Tag.asDiv().css("page-scroll");
      this.add(this.slogan).add(scrollTo);
    }
    
    public Intro scrollTo(String id, Icon icon){
      this.link.element().setHref("#" + id);
      icon.appendTo(this.link);
      return this;
    }
    
    public Slogan slogan() {
      return slogan;
    }
    
    public class Slogan extends Composite<Slogan>{
      public Slogan() {
        super(Elements.div());
        this.css("slogan");
      }
    }
  }
  
  public class Section extends Composite<Section> {
    public Section() {
      super(Elements.create("section"));
    }
  }
}