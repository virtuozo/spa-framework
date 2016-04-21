package virtuozo.interfaces;

import virtuozo.infra.Converter;
import virtuozo.infra.Item;

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
