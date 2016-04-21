package virtuozo.infra.data;

import virtuozo.infra.data.DataBinding.Attribute;

class FloatData extends Data<Float> {
  FloatData(virtuozo.infra.data.DataBinding.Attribute attribute, JSObject data) {
    super(attribute, data);
  }

  @Override
  public Float get() {
    return this.get(null);
  }

  @Override
  public Float get(Float defaultValue) {
    if (this.data().has(this.attribute().name())) {
      return this.data().getFloat(this.attribute().name());
    }
    return defaultValue;
  }

  @Override
  public void set(Float value) {
    this.data().set(this.attribute().name(), value);
  }
}