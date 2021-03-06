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

import virtuozo.infra.Clause;
import virtuozo.infra.event.ActivationEvent.ActivationHandler;
import virtuozo.infra.event.DeactivationEvent.DeactivationHandler;
import virtuozo.infra.handlers.HasClickHandlers;
import virtuozo.interfaces.UIComponent;

import com.google.gwt.dom.client.Element;

public interface HasActivation<C extends UIComponent> extends HasClickHandlers<C>, UIComponent {
  C activate();
  
  C onActivate(ActivationHandler handler);

  C deactivate();

  C onDeactivate(DeactivationHandler handler);

  boolean active();

  boolean match(Element element);
  
  public static enum Clauses implements Clause{
    ACTIVE{
      @Override
      public boolean matches(UIComponent component) {
        if(component instanceof HasActivation){
          HasActivation<?> activation = (HasActivation<?>) component;
          return activation.active();
        }
        return false;
      }
    };
  }
}
