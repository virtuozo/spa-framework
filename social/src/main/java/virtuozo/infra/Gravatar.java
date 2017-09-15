package virtuozo.infra;

import virtuozo.infra.HttpClient.Endpoint;
import virtuozo.interfaces.Image;

import com.google.gwt.core.client.impl.Md5Digest;
import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Hex;

public class Gravatar {
  private static final Gravatar instance = new Gravatar();
  
  private static final Md5Digest md5 = new Md5Digest();
  
  public static Gravatar get() {
    return instance;
  }
  
  private Gravatar() {
    super();
  }
  
  public Image image(String email){
    return Image.create().source(this.url(email));
  }
  
  public String url(String email){
    Endpoint endpoint = Endpoint.create("http://www.gravatar.com/avatar");
    endpoint.append(new String(Hex.encode(md5.digest(email.toLowerCase().getBytes()))));
    return endpoint.toString();
  }
}