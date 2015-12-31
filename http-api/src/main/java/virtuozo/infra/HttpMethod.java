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

import java.util.Map;
import java.util.Map.Entry;

import virtuozo.infra.HttpClient.PathBuilder;
import virtuozo.infra.api.AsyncException;
import virtuozo.infra.api.JSOCallback;
import virtuozo.infra.api.JsonCallback;
import virtuozo.infra.api.TextCallback;
import virtuozo.infra.api.XmlCallback;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;

public class HttpMethod {

  private RequestBuilder builder;

  private StatusCodeHandler statusCodeHandler = new StatusCodeHandler();

  public static enum HttpMethodName {
    HEAD, GET, PUT, POST, DELETE, OPTIONS;
  }

  public static enum MediaType {
    TEXT("text/plain"), JSON("application/json"), XML("application/xml"), RSS("application/rss+xml"), ATOM("application/atom+xml");

    private String type;

    private MediaType(String type) {
      this.type = type;
    }

    public String type() {
      return type;
    }
  }

  public static enum Headers {
    ACCEPT("Accept"), CONTENT_TYPE("Content-Type");

    private String key;

    private Headers(String key) {
      this.key = key;
    }

    public String key() {
      return key;
    }
  }

  class MethodRequestBuilder extends RequestBuilder {
    public MethodRequestBuilder(String method, String url) {
      super(method, url);
      this.setHeader("X-HTTP-Method-Override", method);
    }
  }

  public HttpMethod(HttpMethodName method, PathBuilder path) {
    StringBuilder baseUri = new StringBuilder(GWT.getHostPageBaseURL());
    this.builder = new MethodRequestBuilder(method.name(), baseUri.append(path).toString());
    this.defaultAcceptType(MediaType.JSON);
  }

  public HttpMethod user(String user) {
    this.builder.setUser(user);
    return this;
  }

  public HttpMethod password(String password) {
    this.builder.setPassword(password);
    return this;
  }

  public HttpMethod header(String header, String value) {
    this.builder.setHeader(header, value);
    return this;
  }

  public HttpMethod headers(Map<String, String> headers) {
    if (headers != null) {
      for (Entry<String, String> entry : headers.entrySet()) {
        builder.setHeader(entry.getKey(), entry.getValue());
      }
    }
    return this;
  }

  public HttpMethod text(String data) {
    defaultContentType(MediaType.TEXT);
    this.builder.setRequestData(data);
    return this;
  }

  public HttpMethod json(JSONValue data) {
    defaultContentType(MediaType.JSON);
    this.builder.setRequestData(data.toString());
    return this;
  }

  public HttpMethod json(JSObject object) {
    return this.json(object.json());
  }

  public HttpMethod xml(Document data) {
    defaultContentType(MediaType.XML);
    builder.setRequestData(data.toString());
    return this;
  }

  public HttpMethod timeout(int timeout) {
    this.builder.setTimeoutMillis(timeout);
    return this;
  }

  public HttpMethod expect(int... statuses) {
    this.statusCodeHandler.expect(statuses);
    return this;
  }

  public boolean isExpected(int status) {
    return this.statusCodeHandler.isExpected(status);
  }

  public void send(TextCallback callback) {
    defaultAcceptType(MediaType.TEXT);
    this.send(new CallbackProxy<String>(this, callback) {
      protected String parse(String content) {
        return content;
      }
    });
  }

  public void send(JsonCallback callback) {
    this.defaultAcceptType(MediaType.JSON);
    this.send(new CallbackProxy<JSONValue>(this, callback) {
      protected JSONValue parse(String content) {
        return JSONParser.parseStrict(content);
      }
    });
  }

  public void send(XmlCallback callback) {
    this.defaultAcceptType(MediaType.XML);
    this.send(new CallbackProxy<Document>(this, callback) {
      protected Document parse(String content) {
        return XMLParser.parse(content);
      }
    });
  }

  public <J extends JavaScriptObject> void send(JSOCallback<J> callback) {
    this.defaultAcceptType(MediaType.JSON);
    this.send(new CallbackProxy<J>(this, callback) {
      @SuppressWarnings("unchecked")
      protected J parse(String content) {
        try {
          JSONValue val = JSONParser.parseStrict(content);
          if (val.isObject() != null) {
            return (J) val.isObject().getJavaScriptObject();
          } 
          if (val.isArray() != null) {
            return (J) val.isArray().getJavaScriptObject();
          }
          throw new AsyncException("Response was not a JSON object");
        } catch (Exception e) {
          throw new AsyncException("Response was not a valid JSON document", e);
        }
      }
    });
  }
  
  void send(RequestCallback callback) {
    Logger.get().debug("Sending http request: " + builder.getHTTPMethod() + " " + builder.getUrl() + " ,timeout:" + builder.getTimeoutMillis());

    String content = builder.getRequestData();
    if (content != null && content.length() > 0) {
      Logger.get().debug("Request body:" + content);
    }

    Request request = null;
    try {
      request = this.builder.sendRequest(content, callback);
    } catch (RequestException e) {
      GWT.log("Received http error for: " + builder.getHTTPMethod() + " " + builder.getUrl(), e);
      callback.onError(request, e);
    }
  }

  protected HttpMethod defaultContentType(MediaType type) {
    this.header(Headers.CONTENT_TYPE.key(), type.type());
    return this;
  }

  protected HttpMethod defaultAcceptType(MediaType type) {
    this.header(Headers.ACCEPT.key(), type.type());
    return this;
  }

  public HttpMethod accept(MediaType type) {
    this.defaultAcceptType(type);
    return this;
  }
}