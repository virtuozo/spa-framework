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
import virtuozo.ui.Elements;
import virtuozo.ui.Tag;
import virtuozo.ui.Text;
import virtuozo.ui.api.HasText;

import com.google.gwt.dom.client.Element;

public final class Heading extends Component<Heading> implements HasText<Heading> {

  private Text textHolder = new Text();

  private Tag<Element> secondary = Tag.as(Elements.small()).hide();

  public Heading(Level level) {
    super(Elements.heading(level.ordinal() + 1));
    this.addChild(this.textHolder).addChild(this.secondary);
  }

  public Heading headline(String text) {
    this.secondary.text(text).show();
    return this;
  }

  public String headline() {
    return this.secondary.text();
  }

  @Override
  public Heading text(String text) {
    this.textHolder.text(text);
    return this;
  }

  @Override
  public String text() {
    return this.textHolder.text();
  }

  public static enum Level {
    ONE, TWO, THREE, FOUR, FIVE, SIX;

    public String level() {
      return String.valueOf(this.ordinal() + 1);
    }
  }
}