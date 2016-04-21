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
import virtuozo.interfaces.Component;
import virtuozo.interfaces.Tag;

import com.google.gwt.dom.client.Element;

public final class Blockquote extends Component<Blockquote> implements HasText<Blockquote>{
  private Paragraph textHolder = Paragraph.create();
  
  private Tag<Element> footer = Tag.as(Elements.create("footer")).hide();
  
  public static Blockquote create(){
    return new Blockquote();
  }
  
  private Blockquote() {
    super(Elements.blockquote());
    this.addChild(textHolder).addChild(this.footer);
  }
  
  public String footer(){
    return this.footer.text();
  }
  
  public Blockquote footer(String footer){
    this.footer.show().text(footer);
    return this;
  }
  
  @Override
  public String text() {
    return this.textHolder.text();
  }
  
  @Override
  public Blockquote text(String text) {
    this.textHolder.text(text);
    return this;
  }
  
  public Blockquote reverse(){
    if(this.css().contains("blockquote-reverse")){
      this.css().remove("blockquote-reverse");
      return this;
    }
    
    this.css("blockquote-reverse");
    return this;
  }
}