package virtuozo.infra;

class DoubleData extends Data<Double> {
  DoubleData(virtuozo.infra.DataBinding.Attribute attribute, JSObject data) {
    super(attribute, data);
  }

  @Override
  public Double get() {
    return this.get(null);
  }

  @Override
  public Double get(Double defaultValue) {
    if (this.data().has(this.attribute().name())) {
      return this.data().getDouble(this.attribute().name());
    }
    return defaultValue;
  }

  @Override
  public void set(Double value) {
    this.data().set(this.attribute().name(), value);
  }
}