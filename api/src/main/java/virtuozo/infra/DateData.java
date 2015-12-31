package virtuozo.infra;

import java.util.Date;

class DateData extends Data<Date> {
  DateData(virtuozo.infra.DataBinding.Attribute attribute, JSObject data) {
    super(attribute, data);
  }

  @Override
  public Date get() {
    return this.get(null);
  }

  @Override
  public Date get(Date defaultValue) {
    if (this.data().has(this.attribute().name())) {
      return this.data().getDate(this.attribute().name());
    }
    return defaultValue;
  }

  @Override
  public void set(Date value) {
    this.data().set(this.attribute().name(), value);
  }
}