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
import hitz.virtuozo.ui.Heading;
import hitz.virtuozo.ui.StyleChooser;

public class HeadingClass extends CssClass{
  private HeadingClass(String name) {
    super(name);
  }

  @Override
  protected StyleChooser chooser() {
    return STYLES;
  }
  
  public static HeadingClass get(Heading.Level level){
    switch(level){
      case ONE:
        return HeadingClass.LEVEL_ONE;
      case TWO:
        return HeadingClass.LEVEL_TWO;
      case THREE:
        return HeadingClass.LEVEL_THREE;
      case FOUR:
        return HeadingClass.LEVEL_FOUR;
      case FIVE:
        return HeadingClass.LEVEL_FIVE;
      case SIX:
        return HeadingClass.LEVEL_SIX;
    }
    
    return null;
  }

  public static final HeadingClass LEVEL_ONE = new HeadingClass("h1");
  public static final HeadingClass LEVEL_TWO = new HeadingClass("h2");
  public static final HeadingClass LEVEL_THREE = new HeadingClass("h3");
  public static final HeadingClass LEVEL_FOUR = new HeadingClass("h4");
  public static final HeadingClass LEVEL_FIVE = new HeadingClass("h5");
  public static final HeadingClass LEVEL_SIX = new HeadingClass("h6");
  private static final StyleChooser STYLES = new StyleChooser(LEVEL_ONE, LEVEL_TWO, LEVEL_THREE, LEVEL_FOUR, LEVEL_FIVE, LEVEL_SIX);
}
