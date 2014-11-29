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

import virtuozo.infra.HashObject;
import virtuozo.ui.Table.Cell;

@SuppressWarnings("unchecked")
public abstract class TextGridColumn<T extends TextGridColumn<T, H>, H extends HashObject> extends SortableGridColumn<T, H> {

  @Override
  public boolean filterable() {
    return true;
  }

  public T render(int rowIndex, Cell cell, H object) {
    cell.text(this.toString(object));
    return (T) this;
  }

  public abstract String toString(H object);
}