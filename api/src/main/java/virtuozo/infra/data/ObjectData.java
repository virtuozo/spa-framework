package virtuozo.infra.data;

import virtuozo.infra.data.DataBinding.Attribute;

class ObjectData<J extends JSObject> extends Data<J> {
  ObjectData(virtuozo.infra.data.DataBinding.Attribute attribute, JSObject data) {
    super(attribute, data);
  }

  @Override
  public J get() {
    return this.get(null);
  }

  @Override
  public J get(J defaultValue) {
    if (this.data().has(this.attribute().name())) {
      return this.data().getJsObject(this.attribute().name());
    }
    return defaultValue;
  }

  @Override
  public void set(J value) {
    this.data().set(this.attribute().name(), value);
  }
}