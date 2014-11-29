package virtuozo.infra;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class Hash {
  private Map<Entry, Object> attributes = new HashMap<Entry, Object>();
  
  public Hash set(Entry attribute, Object value){
    this.attributes.put(attribute, value);
    return this;
  }
  
  public <O> O get(Entry attribute){
    return (O) this.attributes.get(attribute);
  }
  
  public static interface Entry{
    String name();
  }
}