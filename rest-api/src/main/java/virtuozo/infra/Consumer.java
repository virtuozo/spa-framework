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

import virtuozo.infra.HashObject;
import virtuozo.infra.Rest.PathBuilder;
import virtuozo.infra.RestMethod.MediaType;
import virtuozo.infra.api.HashCallback;

public abstract class Consumer<H extends HashObject> {
  private MediaType contentType;
  
  public Consumer(MediaType contentType) {
    this.contentType = contentType;
  }
  
  protected Consumer<H> get(PathBuilder builder, HashCallback<H> callback){
    this.caller(builder).get().accept(this.contentType).send(callback);
    return this;
  }
  
  protected Consumer<H> put(PathBuilder builder, HashCallback<H> callback){
    this.caller(builder).put().accept(this.contentType).send(callback);
    return this;
  }
  
  protected Consumer<H> post(PathBuilder builder, HashCallback<H> callback){
    this.caller(builder).post().accept(this.contentType).send(callback);
    return this;
  }
  
  protected Consumer<H> delete(PathBuilder builder, HashCallback<H> callback){
    this.caller(builder).delete().accept(this.contentType).send(callback);
    return this;
  }
  
  protected Rest caller(PathBuilder builder){
    return Rest.create(builder);
  }
}