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

package hitz.virtuozo.infra;

import java.util.HashMap;
import java.util.Map;

import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestMethod;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.URL;

public class Rest {

  private final Resource resource;
  
  private final String baseUri = GWT.getHostPageBaseURL();

  public Rest(Map<String, String> headers) {
    this.resource = new RestResource(this.baseUri, headers);
  }

  public Rest(String query, Map<String, String> headers) {
    this.resource = new RestResource(this.baseUri, query, headers);
  }

  public Rest(String query) {
    this.resource = new RestResource(this.baseUri, query);
  }

  public Rest() {
    this.resource = new RestResource(this.baseUri);
  }

  public Rest(PathBuilder builder) {
    this.resource = new RestResource(this.baseUri + builder.toString());
  }

  public RestMethod delete() {
    return new RestMethod(this.resource.delete());
  }

  public RestMethod get() {
    return new RestMethod(this.resource.get());
  }

  public RestMethod head() {
    return new RestMethod(this.resource.head());
  }

  public RestMethod options() {
    return new RestMethod(this.resource.options());
  }

  public RestMethod post() {
    return new RestMethod(this.resource.post());
  }

  public RestMethod put() {
    return new RestMethod(this.resource.put());
  }

  public Rest addHeaderParam(String key, String value) {
    this.resource.getHeaders().put(key, value);
    return this;
  }

  class RestResource extends Resource {

    RestResource(String uri, Map<String, String> headers) {
      super(uri, headers);
    }

    RestResource(String uri, String query, Map<String, String> headers) {
      super(uri, query, headers);
    }

    RestResource(String uri, String query) {
      super(uri, query);
    }

    RestResource(String uri) {
      super(uri);
    }

    @Override
    protected Map<String, String> defaultHeaders() {
      return new HashMap<String, String>();
    }
  }

  public static class PathBuilder {

    private StringBuffer target;

    private StringBuffer query = new StringBuffer();

    private PathBuilder(String uri) {
      this.target = new StringBuffer(this.parse(uri));
    }

    public static PathBuilder get(String uri) {
      return new PathBuilder(uri);
    }

    public PathBuilder append(Boolean value) {
      return this.add(value);
    }

    public PathBuilder append(Number value) {
      return this.add(value);
    }

    public PathBuilder append(String value) {
      return this.add(value);
    }

    public PathBuilder append(Object value) {
      return this.add(value);
    }

    public PathBuilder addQueryParam(String key, String value) {
      if (this.query.length() == 0) {
        this.query.append("?");
      } else {
        this.query.append("&");
      }

      key = URL.encodeQueryString(key);
      value = URL.encodeQueryString(value);

      this.query.append(key).append("=").append(value);

      return this;
    }

    PathBuilder add(Object value) {
      this.target.append("/").append(value);
      return this;
    }

    @Override
    public String toString() {
      return this.target.append(this.query).toString();
    }

    String parse(String uri) {
      return uri.endsWith("/") ? uri.substring(0, uri.length() - 1) : uri;
    }
  }
}