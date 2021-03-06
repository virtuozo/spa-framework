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

import virtuozo.infra.HttpMethod.HttpMethodName;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.URL;

public class HttpClient {

  private Endpoint path;

  public static HttpClient create(Endpoint path) {
    return new HttpClient(path);
  }

  private HttpClient(Endpoint path) {
    this.path = path;
  }

  public JsonPMethod jsonp() {
    return new JsonPMethod(this.path);
  }

  public HttpMethod delete() {
    return new HttpMethod(HttpMethodName.DELETE, this.path);
  }

  public HttpMethod get() {
    return new HttpMethod(HttpMethodName.GET, this.path);
  }

  public HttpMethod head() {
    return new HttpMethod(HttpMethodName.HEAD, this.path);
  }

  public HttpMethod options() {
    return new HttpMethod(HttpMethodName.OPTIONS, this.path);
  }

  public HttpMethod post() {
    return new HttpMethod(HttpMethodName.POST, this.path);
  }

  public HttpMethod put() {
    return new HttpMethod(HttpMethodName.PUT, this.path);
  }

  public static class Endpoint {

    private String baseEndpoint;

    private StringBuffer target;

    private StringBuffer query = new StringBuffer();

    private Endpoint(String baseEndpoint, String uri) {
      init(baseEndpoint, uri);
    }

    private void init(String baseEndpoint, String uri) {
      this.target = new StringBuffer(this.parse(uri));
      this.baseEndpoint = baseEndpoint;
      
      if(this.baseEndpoint == null){
        this.baseEndpoint = this.baseEndpointFromHostPage();
      }
      
      if(this.baseEndpoint == null){
        this.baseEndpoint = GWT.getHostPageBaseURL();
      }
      
      this.baseEndpoint = this.parse(this.baseEndpoint);
    }

    public static Endpoint create(String baseEndpoint, String uri) {
      return new Endpoint(baseEndpoint, uri);
    }
    
    public String baseEndpoint() {
      return baseEndpoint;
    }

    public static Endpoint create(String uri) {
      return new Endpoint(null, uri);
    }

    public Endpoint append(Boolean value) {
      return this.add(value);
    }

    public Endpoint append(Number value) {
      return this.add(value);
    }

    public Endpoint append(String value) {
      return this.add(value);
    }

    public Endpoint append(Object value) {
      return this.add(value);
    }

    public Endpoint addQueryParam(String key, String value) {
      String control = "&";
      if (this.query.length() == 0) {
        control = "?";
      }
      this.query.append(control);

      key = URL.encodeQueryString(key);
      value = URL.encodeQueryString(value);

      this.query.append(key).append("=").append(value);

      return this;
    }

    Endpoint add(Object value) {
      this.target.append("/").append(value);
      return this;
    }

    @Override
    public String toString() {
      return this.target.append(this.query).toString();
      //return this.target.append("/").append(this.query).toString();
    }

    String parse(String uri) {
      if (uri.startsWith("/")) {
        uri = uri.substring(1);
      }
      return uri.endsWith("/") ? uri.substring(0, uri.length() - 1) : uri;
    }
    
    private final native String baseEndpointFromHostPage()/*-{
      return $wnd.baseEndpoint;
    }-*/;
  }
}