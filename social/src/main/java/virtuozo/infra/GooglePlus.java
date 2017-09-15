package virtuozo.infra;

import virtuozo.infra.HttpClient.Endpoint;

public class GooglePlus {
  private static GooglePlus instance = new GooglePlus();
  
  private GooglePlus() {
    super();
  }
  
  public static GooglePlus get() {
    return instance;
  }
  
  public String shareEndpoint(String url){
    Endpoint endpoint = Endpoint.create("https://plus.google.com/share");
    endpoint.addQueryParam("url", url);
    
    return endpoint.toString();
  }
}