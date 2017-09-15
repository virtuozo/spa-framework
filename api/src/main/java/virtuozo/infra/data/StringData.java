package virtuozo.infra.data;

class StringData extends Data<String> {
  StringData(Attribute attribute, JSObject data) {
    super(attribute, data);
  }

  @Override
  public String get() {
    return this.get(null);
  }
  
  @Override
  public String get(String defaultValue) {
    if (this.data().has(this.attribute().name())) {
      return this.data().getString(this.attribute().name());
    }
    return defaultValue;
  }

  @Override
  public void set(String value) {
    this.data().set(this.attribute().name(), value);
  }
}