package virtuozo.infra;

import virtuozo.infra.HttpClient.Endpoint;

public class Facebook {
  private static final Facebook instance = new Facebook();
  
  private Facebook() {
    super();
  }
  
  public static Facebook get() {
    return instance;
  }
  
  public String shareEndpoint(String url){
    Endpoint endpoint = Endpoint.create("https://www.facebook.com/sharer/sharer.php");
    endpoint.addQueryParam("u", url);
    return endpoint.toString();
  }
}