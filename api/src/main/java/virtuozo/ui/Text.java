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

import virtuozo.ui.api.HasText;

public final class Text extends Component<Text> implements HasText<Text> {

  public Text() {
    super(Elements.span());
    this.hide();
  }
  
  @Override
  public Text text(String text) {
    this.element().setInnerText(text);
    return this.show();
  }

  @Override
  public String text() {
    return this.element().getInnerText();
  }
}