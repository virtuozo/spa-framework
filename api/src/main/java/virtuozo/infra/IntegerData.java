package virtuozo.infra;

class IntegerData extends Data<Integer> {
  IntegerData(virtuozo.infra.DataBinding.Attribute attribute, JSObject data) {
    super(attribute, data);
  }

  @Override
  public Integer get() {
    return this.get(null);
  }

  @Override
  public Integer get(Integer defaultValue) {
    if (this.data().has(this.attribute().name())) {
      return this.data().getInteger(this.attribute().name());
    }
    return defaultValue;
  }

  @Override
  public void set(Integer value) {
    this.data().set(this.attribute().name(), value);
  }
}