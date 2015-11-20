package virtuozo.ui;

import virtuozo.infra.Item;
import virtuozo.infra.api.Converter;

public class SimpleItemConverter implements Converter<Item, String> {
  private SingleSelect<?, Item> select;
  
  public SimpleItemConverter(SingleSelect<?, Item> select) {
    this.select = select;
  }
  
  @Override
  public String convert(Item value) {
    if (value == null) {
      return this.select.placeholder();
    }

    return value.value();
  }
}
