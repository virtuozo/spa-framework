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

import virtuozo.infra.events.HideEvent;
import virtuozo.infra.events.ShowEvent;
import virtuozo.infra.events.ToggleEvent;
import virtuozo.infra.events.HideEvent.HideHandler;
import virtuozo.infra.events.ShowEvent.ShowHandler;
import virtuozo.infra.events.ToggleEvent.ToggleHandler;

public interface HasVisibility<T> {

  T onHide(HideHandler handler);

  T onShow(ShowHandler handler);

  T onToggleVisibility(ToggleHandler handler);

  T show();
  
  T hide();
  
  T toggleVisibility();
  
  boolean visible();
}