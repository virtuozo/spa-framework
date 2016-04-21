package virtuozo.infra.data;


abstract class Data<T> implements DataBinding<T> {
  private Attribute attribute;

  private JSObject data;

  public Data(Attribute attribute, JSObject data) {
    this.attribute = attribute;
    this.data = data;
  }
  
  protected Attribute attribute() {
    return attribute;
  }
  
  protected JSObject data() {
    return data;
  }
}