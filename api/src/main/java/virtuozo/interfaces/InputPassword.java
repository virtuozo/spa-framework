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

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.impl.TextBoxImpl;

public final class InputPassword extends Input<InputPassword> {

  private TextBoxImpl textBoxCursor = GWT.create(TextBoxImpl.class);
  
  private final Placeholder placeholderImpl = GWT.create(Placeholder.class);

  public static InputPassword create(){
    return new InputPassword();
  }
  
  private InputPassword() {
    super(Elements.password());
  }

  @Override
  public InputPassword clear() {
    return this.value("");
  }

  public InputPassword placeholder(String placeholder) {
    this.placeholderImpl.apply(this, placeholder);
    return this;
  }

  @Override
  public String value() {
    return this.placeholderImpl.valueOf(this);
  }

  public InputPassword maxLength(int maxLength) {
    this.element().setMaxLength(maxLength);
    return this;
  }

  public InputPassword cursorAt(int index) {
    this.selectRange(index, 0);
    return this;
  }

  public int cursorPosition() {
    return this.textBoxCursor.getCursorPos((com.google.gwt.user.client.Element) this.element().cast());
  }

  public InputPassword selectAll() {
    int length = this.element().getValue().length();
    if (length > 0) {
      this.selectRange(0, length);
    }
    return this;
  }

  private void selectRange(int index, int length) {
    this.textBoxCursor.setSelectionRange((com.google.gwt.user.client.Element) this.element().cast(), index, length);
  }
}