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

public class ButtonColor extends CssClass {
  private ButtonColor(String name) {
    super(name);
  }

  @Override
  protected StyleChooser chooser() {
    return STYLES;
  }

  public static final ButtonColor DEFAULT = new ButtonColor("btn-default");

  public static final ButtonColor PRIMARY = new ButtonColor("btn-primary");

  public static final ButtonColor SUCCESS = new ButtonColor("btn-success");

  public static final ButtonColor INFO = new ButtonColor("btn-info");

  public static final ButtonColor WARNING = new ButtonColor("btn-warning");

  public static final ButtonColor DANGER = new ButtonColor("btn-danger");

  public static final ButtonColor LINK = new ButtonColor("btn-link");

  private static final StyleChooser STYLES = new StyleChooser(DEFAULT, PRIMARY, SUCCESS, INFO, WARNING, DANGER, LINK);
}