package virtuozo.infra.data;

import virtuozo.infra.data.DataBinding.Attribute;

class BooleanData extends Data<Boolean> {
  BooleanData(virtuozo.infra.data.DataBinding.Attribute attribute, JSObject data) {
    super(attribute, data);
  }

  @Override
  public Boolean get() {
    return this.get(null);
  }

  @Override
  public Boolean get(Boolean defaultValue) {
    if (this.data().has(this.attribute().name())) {
      return this.data().getBoolean(this.attribute().name());
    }
    return defaultValue;
  }

  @Override
  public void set(Boolean value) {
    this.data().set(this.attribute().name(), value);
  }
}