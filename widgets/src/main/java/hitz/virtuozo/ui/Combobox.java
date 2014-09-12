package hitz.virtuozo.ui;

import hitz.virtuozo.infra.KeyValue;
import hitz.virtuozo.infra.api.Converter;

public class Combobox extends Select<Combobox, Combobox.Item> {

  public Combobox() {
    this.converter(new Converter<Item, String>() {
      
      @Override
      public String convert(Item value) {
        if(value == null){
          return Combobox.this.placeholder();
        }
        
        return value.value();
      }
    });
  }
  
  public static class Item extends KeyValue<String, String>{
    public Item(String value, String key) {
      this.key(key).value(value);
    }
  }
}