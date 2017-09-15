package virtuozo.infra.data;

class CalendarData extends Data<Calendar> {
  CalendarData(Attribute attribute, JSObject data) {
    super(attribute, data);
  }

  @Override
  public Calendar get() {
    return this.get(null);
  }

  @Override
  public Calendar get(Calendar defaultValue) {
    if (this.data().has(this.attribute().name())) {
      return Calendar.of(this.data().getDate(this.attribute().name()));
    }
    return defaultValue;
  }

  @Override
  public void set(Calendar value) {
    this.data().set(this.attribute().name(), value.toDate());
  }
}