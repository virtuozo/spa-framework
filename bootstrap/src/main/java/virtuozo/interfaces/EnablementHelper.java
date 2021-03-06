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

import virtuozo.infra.EventInterceptor;
import virtuozo.interfaces.css.State;

import com.google.gwt.user.client.Event;

public class EnablementHelper<C extends UIComponent> implements HasEnablement<C>{

  private C target;
  
  private EventInterceptor interceptor = new EventInterceptor() {
    
    @Override
    public boolean shouldFire(Event event) {
      return !EnablementHelper.this.target.asComponent().css().contains(State.DISABLED);
    }
  };
  
  public static <C extends UIComponent> EnablementHelper<C> to(C target){
    return new EnablementHelper<C>(target);
  }
  
  private EnablementHelper(C target) {
    this.target = target;
  }

  @Override
  public C enable() {
    this.target.asComponent().css().remove(State.DISABLED);
    return this.target;
  }

  @Override
  public C disable() {
    this.target.asComponent().css(State.DISABLED);
    return this.target;
  }

  @Override
  public boolean disabled() {
    return this.target.asComponent().css().contains(State.DISABLED);
  }
  
  public EnablementHelper<C> intercept(UIComponent component){
    component.asComponent().onEvent(this.interceptor);
    return this;
  }
}