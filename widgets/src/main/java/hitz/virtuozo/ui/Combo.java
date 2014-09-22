package hitz.virtuozo.ui;

import hitz.virtuozo.infra.KeyValue;
import hitz.virtuozo.infra.api.Converter;

public class Combo extends SingleSelect<Combo, Combo.Item> {

  public Combo() {
    this.converter(new Converter<Item, String>() {
      
      @Override
      public String convert(Item value) {
        if(value == null){
          return Combo.this.placeholder();
        }
        
        return value.value();
      }
    });
  }
  
  public static class Item extends KeyValue<String, String>{
    public Item(String key, String value) {
      this.key(key).value(value);
    }
  }
  
  public static enum Matchers implements Matcher<Item> {
    ANY(new Matcher<Item>(){
      public boolean matches(String search, Item value) {
        return value.value().toLowerCase().contains(search.toLowerCase());
      }
    }),
    CONTAINS(new Matcher<Item>(){
      public boolean matches(String search, Item value) {
        return value.value().contains(search);
      }
    }), 
    STARTS_WITH(new Matcher<Item>(){
      public boolean matches(String search, Item value) {
        return value.value().startsWith(search);
      }
    });
    
    private Matcher<Item> matcher;
    
    private Matchers(Matcher<Item> matcher) {
      this.matcher = matcher;
    }

    @Override
    public boolean matches(String search, Item value) {
      if(search == null || value == null || value.value() == null){
        return false;
      }
      
      return this.matcher.matches(search, value);
    }
  }
}