package virtuozo.infra;

import virtuozo.infra.HttpClient;
import virtuozo.infra.HttpClient.Endpoint;
import virtuozo.infra.data.Calendar;
import virtuozo.infra.data.DataBinding;
import virtuozo.infra.data.DataBinding.Attribute;
import virtuozo.infra.data.JSObject;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class MailChimpConsumer {
  private Endpoint endpoint = Endpoint.create("http://github.us13.list-manage.com/subscribe/post-json");
  
  public static MailChimpConsumer create(String user, String id){
    return new MailChimpConsumer(user, id);
  }
  
  private MailChimpConsumer(String user, String id) {
    this.init(user, id);
  }
  
  private void init(String user, String id){
    this.endpoint.addQueryParam("u", user);
    this.endpoint.addQueryParam("id", id);
    
    String userId = new StringBuilder("b_").append(user).append("_").append(id).toString();
    this.endpoint.addQueryParam(userId, "");
  }
  
  public void post(AsyncCallback<MailChimpResponse> callback, Value... values){
    if(values == null){
      return;
    }
    
    this.endpoint.addQueryParam("_", String.valueOf(Calendar.today().time()));
    for(Value value : values){
      this.endpoint.addQueryParam(value.field.name(), value.get());
    }
    
    HttpClient.create(this.endpoint).jsonp().callbackParam("c").send(callback);
  }
  
  public static class MailChimpResponse extends JSObject {
    protected MailChimpResponse() {}
    
    public final DataBinding<String> message() {
      return this.bindAsString(Schema.msg);
    }
    
    public final DataBinding<String> result() {
      return this.bindAsString(Schema.result);
    }
    
    static enum Schema implements Attribute {
      result,
      msg;
    }
  }
  
  public static class Value {
    private final Field field;
    
    private String value;
    
    public static Value create(Field field){
      return new Value(field);
    }
    
    private Value(Field field) {
      this.field = field;
    }
    
    public Field field() {
      return field;
    }

    public String get() {
      return value;
    }

    public void set(String value) {
      this.value = value;
    }
  }
  
  public static enum Field{
    FNAME, EMAIL;
  }
}