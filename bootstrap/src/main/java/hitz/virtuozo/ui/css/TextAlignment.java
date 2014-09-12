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
package hitz.virtuozo.ui.css;

import hitz.virtuozo.ui.CssClass;
import hitz.virtuozo.ui.StyleChooser;

public class TextAlignment extends CssClass {
  private TextAlignment(String name) {
    super(name);
  }

  @Override
  protected StyleChooser chooser() {
    return STYLES;
  }

  public static final TextAlignment LEFT = new TextAlignment("text-left");

  public static final TextAlignment CENTER = new TextAlignment("text-center");

  public static final TextAlignment RIGHT = new TextAlignment("text-right");

  public static final TextAlignment JUSTIFY = new TextAlignment("text-justify");

  private static final StyleChooser STYLES = new StyleChooser(LEFT, CENTER, RIGHT, JUSTIFY);
}