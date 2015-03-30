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

import virtuozo.infra.RestMethod.HttpMethod;

import com.google.gwt.http.client.URL;

public class Rest {

  private PathBuilder path;
  
  public static Rest create(PathBuilder path){
    return new Rest(path);
  }
  
  private Rest(PathBuilder path) {
    this.path = path;
  }
  
  public JsonPMethod jsonp() {
    return new JsonPMethod(this.path);
  }

  public RestMethod delete() {
    return new RestMethod(HttpMethod.DELETE, this.path);
  }

  public RestMethod get() {
    return new RestMethod(HttpMethod.GET, this.path);
  }

  public RestMethod head() {
    return new RestMethod(HttpMethod.HEAD, this.path);
  }

  public RestMethod options() {
    return new RestMethod(HttpMethod.OPTIONS, this.path);
  }

  public RestMethod post() {
    return new RestMethod(HttpMethod.POST, this.path);
  }

  public RestMethod put() {
    return new RestMethod(HttpMethod.PUT, this.path);
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
      String control ="&";
      if (this.query.length() == 0) {
        control = "?";
      }
      this.query.append(control);

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
      if(uri.startsWith("/")){
        uri = uri.substring(1);
      }
      return uri.endsWith("/") ? uri.substring(0, uri.length() - 1) : uri;
    }
  }
}