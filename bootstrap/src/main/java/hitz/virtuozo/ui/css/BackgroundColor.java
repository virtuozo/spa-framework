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

public class BackgroundColor extends CssClass {
  private BackgroundColor(String name) {
    super(name);
  }

  @Override
  protected StyleChooser chooser() {
    return STYLES;
  }

  public static final BackgroundColor DANGER = new BackgroundColor("bg-danger");

  public static final BackgroundColor INFO = new BackgroundColor("bg-info");

  public static final BackgroundColor PRIMARY = new BackgroundColor("bg-primary");

  public static final BackgroundColor SUCCESS = new BackgroundColor("bg-success");

  public static final BackgroundColor WARNING = new BackgroundColor("bg-warning");

  private static final StyleChooser STYLES = new StyleChooser(DANGER, INFO, PRIMARY, SUCCESS, WARNING);
}