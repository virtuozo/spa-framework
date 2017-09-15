package virtuozo.infra.data;

import com.google.gwt.core.client.JsArray;

class ArrayData<J extends JSObject> extends Data<JsArray<J>> {
  ArrayData(Attribute attribute, JSObject data) {
    super(attribute, data);
  }

  @Override
  public JsArray<J> get() {
    return this.get(null);
  }

  @Override
  public JsArray<J> get(JsArray<J> defaultValue) {
    if (this.data().has(this.attribute().name())) {
      return this.data().getArray(this.attribute().name());
    }
    return defaultValue;
  }

  @Override
  public void set(JsArray<J> value) {
    this.data().set(this.attribute().name(), value);
  }
}