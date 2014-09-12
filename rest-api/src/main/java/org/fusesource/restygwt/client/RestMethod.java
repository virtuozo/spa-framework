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

package org.fusesource.restygwt.client;

import hitz.virtuozo.infra.JSObject;

import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.xml.client.Document;

public class RestMethod extends Method {

  private Method wrapped;

  public RestMethod(Method wrapped) {
    this.wrapped = wrapped;
    this.defaultAcceptType(Resource.CONTENT_TYPE_JSON);
  }

  @Override
  public RestMethod user(String user) {
    this.wrapped.user(user);
    return this;
  }

  @Override
  public RestMethod password(String password) {
    this.wrapped.password(password);
    return this;
  }

  @Override
  public RestMethod header(String header, String value) {
    this.wrapped.header(header, value);
    return this;
  }

  @Override
  public RestMethod headers(Map<String, String> headers) {
    this.wrapped.headers(headers);
    return this;
  }

  @Override
  public RestMethod text(String data) {
    this.wrapped.text(data);
    return this;
  }

  @Override
  public RestMethod json(JSONValue data) {
    this.wrapped.json(data);
    return this;
  }

  public RestMethod json(JSObject object) {
    this.wrapped.json(object.json());
    return this;
  }

  @Override
  public RestMethod xml(Document data) {
    this.wrapped.xml(data);
    return this;
  }

  @Override
  public RestMethod timeout(int timeout) {
    this.wrapped.timeout(timeout);
    return this;
  }

  @Override
  public RestMethod expect(int... statuses) {
    this.wrapped.expect(statuses);
    return this;
  }

  @Override
  public boolean isExpected(int status) {
    return this.wrapped.isExpected(status);
  }

  @Override
  public void send(RequestCallback callback) throws RequestException {
    this.wrapped.send(callback);
  }

  @Override
  public void send(TextCallback callback) {
    this.wrapped.send(callback);
  }

  @Override
  public void send(JsonCallback callback) {
    this.wrapped.send(callback);
  }
  
  public <J extends JSObject> void send(JSOCallback<J> callback) {
    try {
      send(new RequestNoLogCallback<J>(this, callback) {

        protected J parseResult() throws Exception {
          try {
            return eval(response.getText()).cast();
          } catch (Throwable e) {
            throw new ResponseFormatException("Response was NOT a valid JSON document", e);
          }
        }
      });

      this.builder = this.wrapped.builder;
    } catch (Throwable e) {
      GWT.log("Received http error for: " + builder.getHTTPMethod() + " " + builder.getUrl(), e);
      callback.onFailure(this, e);
    }
  }

  final native JavaScriptObject eval(String json)/*-{
                                                 return $wnd.eval('(' + json + ')');
                                                 }-*/;

  @Override
  public void send(XmlCallback callback) {
    this.wrapped.send(callback);
  }

  @Override
  public <T extends JavaScriptObject> void send(OverlayCallback<T> callback) {
    this.wrapped.send(callback);
  }

  @Override
  public Request getRequest() {
    return this.wrapped.getRequest();
  }

  @Override
  public Response getResponse() {
    return this.wrapped.getResponse();
  }

  @Override
  protected void defaultContentType(String type) {
    this.wrapped.defaultContentType(type);
  }

  @Override
  protected void defaultAcceptType(String type) {
    this.wrapped.defaultAcceptType(type);
  }
  
  public RestMethod accept(String type){
    this.defaultAcceptType(type);
    return this;
  }

  @Override
  public Dispatcher getDispatcher() {
    return this.wrapped.getDispatcher();
  }

  @Override
  public void setDispatcher(Dispatcher dispatcher) {
    this.wrapped.setDispatcher(dispatcher);
  }

  @Override
  public void addData(String key, String value) {
    this.wrapped.addData(key, value);
  }

  @Override
  public Map<String, String> getData() {
    return this.wrapped.getData();
  }
}