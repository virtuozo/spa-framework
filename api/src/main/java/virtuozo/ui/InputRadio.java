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

import virtuozo.ui.interfaces.UICheck;



public final class InputRadio extends Input<InputRadio> implements UICheck<InputRadio, String> {

  public static InputRadio create(String name){
    return new InputRadio(name);
  }
  
  private InputRadio(String name) {
    super(Elements.radio(name));
  }

  @Override
  public InputRadio clear() {
    return this.uncheck();
  }

  @Override
  public InputRadio check() {
    this.element().setChecked(true);
    return this;
  }
  
  @Override
  public InputRadio uncheck() {
    this.element().setChecked(false);
    return this;
  }
  
  @Override
  public Boolean checked() {
    return this.element().isChecked();
  }
}