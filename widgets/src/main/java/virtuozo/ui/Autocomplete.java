package virtuozo.ui;

public class Autocomplete extends TypeAhead<Autocomplete, String> {

  public static Autocomplete create(){
    return new Autocomplete();
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
