package virtuozo.ui;

import virtuozo.infra.Item;
import virtuozo.infra.api.Converter;
import virtuozo.ui.interfaces.UIRenderer;

public class Combo extends SingleSelect<Combo, Item> {

  public static Combo create(){
    return new Combo();
  }
  
  public static Combo create(UIRenderer<Item> renderer){
    return new Combo().renderer(renderer);
  }
  
  public static Combo create(Converter<Item, String> converter){
    return new Combo().converter(converter);
  }
  
  private Combo() {
    this.converter(new SimpleItemConverter(this));
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