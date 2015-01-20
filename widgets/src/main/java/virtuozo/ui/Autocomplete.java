package virtuozo.ui;

import virtuozo.ui.interfaces.UIRenderer;

public class Autocomplete extends TypeAhead<Autocomplete, String> {

  public static Autocomplete create(){
    return new Autocomplete();
  }
  
  public static Autocomplete create(UIRenderer<String> renderer){
    return new Autocomplete().renderer(renderer);
  }
  
  private Autocomplete() {
    super();
  }
  
  @Override
  public Autocomplete value(String value) {
    this.control().value(value);
    return this;
  }

  @Override
  public String value() {
    return this.control().value();
  }
}
