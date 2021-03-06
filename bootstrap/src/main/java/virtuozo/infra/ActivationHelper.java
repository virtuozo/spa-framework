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
package virtuozo.infra;

import java.util.ArrayList;
import java.util.List;

import virtuozo.infra.event.ActivationEvent;
import virtuozo.infra.event.DeactivationEvent;
import virtuozo.interfaces.HasActivation;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class ActivationHelper implements ClickHandler {
  private List<HasActivation<?>> activationList = new ArrayList<HasActivation<?>>();

  private Behavior behavior = new ToggleBehavior();
  
  public static ActivationHelper create(){
    return new ActivationHelper();
  }
  
  private ActivationHelper() {
    super();
  }
  
  public void behavior(Behavior behaviour) {
    this.behavior = behaviour;
  }

  public ActivationHelper add(HasActivation<?> add) {
    this.activationList.add(add);
    add.onClick(this);
    return this;
  }

  public ActivationHelper reset() {
    for (HasActivation<?> widget : this.activationList) {
      widget.deactivate();
    }
    return this;
  }

  @Override
  public void onClick(ClickEvent event) {
    this.behavior.doActivation(event.getRelativeElement(), activationList);
  }
  
  public static class ToggleBehavior implements Behavior {
    @Override
    public void doActivation(Element element, List<HasActivation<?>> activationList) {
      for (HasActivation<?> widget : activationList) {
        if (widget.match(element)) {
          widget.activate().asComponent().fireEvent(new ActivationEvent());
          continue;
        }
        widget.deactivate().asComponent().fireEvent(new DeactivationEvent());
      }
    }
  }

  public static interface Behavior {
    void doActivation(Element element, List<HasActivation<?>> activationList);
  }
}