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
import virtuozo.ui.Tag;
import virtuozo.ui.css.ButtonColor;

import com.google.gwt.dom.client.DivElement;

public class Jumbotron extends Component<Jumbotron> {
  private Tag<DivElement> container = Tag.asDiv();

  private Heading heading = new Heading(Heading.Level.ONE);
  
  private Paragraph message = new Paragraph();
  
  private Button button = new Button();

  public Jumbotron() {
    super(Elements.div());
    this.css().set("jumbotron");
    this.addChild(this.container);
    this.container.add(this.heading).css().set(Container.Type.FIXED);
  }

  public Heading header() {
    return this.heading;
  }

  public Paragraph message(){
    this.container.add(this.message);
    return this.message;
  }
  
  public Button button(){
    this.container.add(this.button);
    this.button.css(ButtonColor.PRIMARY, Button.Size.LARGE);
    return this.button;
  }
}