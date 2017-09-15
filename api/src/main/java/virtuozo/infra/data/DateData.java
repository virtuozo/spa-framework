package virtuozo.infra.data;

import java.util.Date;

import virtuozo.infra.DateFormat;

class DateData extends Data<Date> implements DateDataBinding {
  DateData(Attribute attribute, JSObject data) {
    super(attribute, data);
  }

  @Override
  public Date get() {
    return this.get((Date) null);
  }

  @Override
  public Date get(Date defaultValue) {
    if (this.data().has(this.attribute().name())) {
      return this.data().getDate(this.attribute().name());
    }
    return defaultValue;
  }
  
  @Override
  public Date get(DateFormat format){
    if (this.data().has(this.attribute().name())) {
      return this.data().getDate(this.attribute().name(), format);
    }
    
    return null;
  }
  
  public Date get(Date defaultValue, DateFormat format){
    if (this.data().has(this.attribute().name())) {
      return this.data().getDate(this.attribute().name(), format);
    }
    return defaultValue;
  }

  @Override
  public void set(Date value) {
    this.data().set(this.attribute().name(), value);
  }
}