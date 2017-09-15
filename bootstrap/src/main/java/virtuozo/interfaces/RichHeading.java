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
import virtuozo.interfaces.Heading.Level;

import com.google.gwt.dom.client.Element;

public final class RichHeading extends Component<RichHeading> implements HasText<RichHeading> {

  private Text textHolder = Text.create();

  private Tag<Element> secondary = Tag.as(Elements.small()).hide();
  
  public static RichHeading one(){
    return new RichHeading(Level.ONE);
  }
  
  public static RichHeading two(){
    return new RichHeading(Level.TWO);
  }
  
  public static RichHeading three(){
    return new RichHeading(Level.THREE);
  }
  
  public static RichHeading four(){
    return new RichHeading(Level.FOUR);
  }
  
  public static RichHeading five(){
    return new RichHeading(Level.FIVE);
  }
  
  public static RichHeading six(){
    return new RichHeading(Level.SIX);
  }

  private RichHeading(Level level) {
    super(Elements.heading(level.ordinal() + 1));
    this.addChild(this.textHolder).addChild(this.secondary);
  }

  public RichHeading headline(String text) {
    this.secondary.text(text).show();
    return this;
  }

  public String headline() {
    return this.secondary.text();
  }

  @Override
  public RichHeading text(String text) {
    this.textHolder.text(text);
    return this;
  }

  @Override
  public String text() {
    return this.textHolder.text();
  }
}