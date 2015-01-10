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


public final class Thumbnail extends Component<Thumbnail> {
  private Caption caption = new Caption().hide();

  private Image image = Image.create();

  public static Thumbnail create(){
    return new Thumbnail();
  }
  
  private Thumbnail() {
    super(Elements.div());
    this.addChild(this.image).addChild(this.caption).css().set("thumbnail");
  }

  public Image image() {
    return this.image;
  }

  public Caption caption() {
    return this.caption.show();
  }

  public class Caption extends Composite<Caption> {
    public Caption() {
      super(Elements.div());
      this.css().set("caption");
    }
  }
}