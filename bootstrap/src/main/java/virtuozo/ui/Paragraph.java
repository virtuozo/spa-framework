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
import virtuozo.ui.api.HasHtml;
import virtuozo.ui.api.HasText;

public final class Paragraph extends Component<Paragraph> implements HasText<Paragraph>, HasHtml<Paragraph> {

  public Paragraph() {
    super(Elements.p());
  }

  public Paragraph lead() {
    this.css("lead");
    return this;
  }
  
  @Override
  public Paragraph html(String html) {
    this.element().setInnerHTML(html);
    return this;
  }
  
  @Override
  public String html() {
    return this.element().getInnerHTML();
  }

  @Override
  public Paragraph text(String text) {
    this.element().setInnerText(text);
    return this;
  }

  @Override
  public String text() {
    return this.element().getInnerText();
  }
}