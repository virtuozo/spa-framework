package virtuozo.infra;

public class Item extends KeyValue<String, String> {
  public static Item create(String value) {
    return new Item(value, value);
  }

  public static Item create(String key, String value) {
    return new Item(key, value);
  }

  private Item(String key, String value) {
    this.key(key).value(value);
  }
}