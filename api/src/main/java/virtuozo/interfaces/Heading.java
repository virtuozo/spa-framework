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

public final class Heading extends Composite<Heading> implements HasText<Heading> {

  public static Heading one(){
    return new Heading(Level.ONE);
  }
  
  public static Heading two(){
    return new Heading(Level.TWO);
  }
  
  public static Heading three(){
    return new Heading(Level.THREE);
  }
  
  public static Heading four(){
    return new Heading(Level.FOUR);
  }
  
  public static Heading five(){
    return new Heading(Level.FIVE);
  }
  
  public static Heading six(){
    return new Heading(Level.SIX);
  }

  private Heading(Level level) {
    super(Elements.heading(level.ordinal() + 1));
  }

  @Override
  public Heading text(String text) {
    this.element().setInnerText(text);
    return this;
  }

  @Override
  public String text() {
    return this.element().getInnerText();
  }

  static enum Level {
    ONE, TWO, THREE, FOUR, FIVE, SIX;

    public String level() {
      return String.valueOf(this.ordinal() + 1);
    }
  }
}