package virtuozo.infra;

import virtuozo.infra.HttpClient.Endpoint;

public class Twitter {
  private static final Twitter instance = new Twitter();
  
  public Twitter() {
    super();
  }
  
  public static Twitter get() {
    return instance;
  }
  
  public String shareEndpoint(String text, String url){
    Endpoint endpoint = Endpoint.create("https://twitter.com/share");
    endpoint.addQueryParam("text", text);
    endpoint.addQueryParam("url", url);
    
    return endpoint.toString();
  }
}