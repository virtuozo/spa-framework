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

import virtuozo.infra.HttpClient.PathBuilder;
import virtuozo.infra.HttpMethod.MediaType;
import virtuozo.infra.data.JSObject;

import com.google.gwt.core.client.JavaScriptObject;

public class Consumer<J extends JavaScriptObject> {
  private MediaType contentType;
  
  private PathBuilder path;
  
  protected Consumer(PathBuilder path, MediaType contentType) {
    this.path = path;
    this.contentType = contentType;
  }
  
  public Consumer<J> list(JsArrayCallback<J> callback){
    this.client().get().accept(this.contentType).send(callback);
    return this;
  }
  
  protected Consumer<J> get(JSOCallback<J> callback){
    this.client().get().accept(this.contentType).send(callback);
    return this;
  }
  
  protected Consumer<J> put(JSObject object, JSOCallback<J> callback){
    this.client().put().accept(this.contentType).json(object).send(callback);
    return this;
  }
  
  protected Consumer<J> post(JSObject object, JSOCallback<J> callback){
    this.client().post().accept(this.contentType).json(object).send(callback);
    return this;
  }
  
  protected Consumer<J> delete(JSObject object, JSOCallback<J> callback){
    this.client().delete().accept(this.contentType).json(object).send(callback);
    return this;
  }
  
  private HttpClient client(){
    return HttpClient.create(this.path);
  }
}