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

public class Direction extends CssClass {
  private Direction(String name) {
    super(name);
  }

  @Override
  protected StyleChooser chooser() {
    return STYLES;
  }

  public static final Direction BOTTOM = new Direction("bottom");
  public static final Direction LEFT = new Direction("left");
  public static final Direction RIGHT = new Direction("right");
  public static final Direction TOP = new Direction("top");
  static final StyleChooser STYLES = new StyleChooser(BOTTOM, LEFT, RIGHT, TOP);

  public static class Horizontal {
    public static final Direction LEFT = Direction.LEFT;
    public static final Direction RIGHT = Direction.RIGHT;
  }

  public static class Vertical {
    public static final Direction BOTTOM = Direction.BOTTOM;
    public static final Direction TOP = Direction.TOP;
  }
}