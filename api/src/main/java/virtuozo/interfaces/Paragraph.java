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

public final class Paragraph extends Component<Paragraph> implements HasText<Paragraph>, HasHtml<Paragraph> {

  public static Paragraph create(){
    return new Paragraph();
  }
  
  private Paragraph() {
    super(Elements.p());
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