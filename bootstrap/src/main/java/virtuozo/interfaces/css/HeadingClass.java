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
package virtuozo.interfaces.css;

import virtuozo.infra.StyleChooser;
import virtuozo.interfaces.CssClass;

public class HeadingClass extends CssClass{
  private HeadingClass(String name) {
    super(name);
  }

  @Override
  protected StyleChooser chooser() {
    return STYLES;
  }
  
  public static final HeadingClass ONE = new HeadingClass("h1");
  public static final HeadingClass TWO = new HeadingClass("h2");
  public static final HeadingClass THREE = new HeadingClass("h3");
  public static final HeadingClass FOUR = new HeadingClass("h4");
  public static final HeadingClass FIVE = new HeadingClass("h5");
  public static final HeadingClass SIX = new HeadingClass("h6");
  private static final StyleChooser STYLES = new StyleChooser(ONE, TWO, THREE, FOUR, FIVE, SIX);
}