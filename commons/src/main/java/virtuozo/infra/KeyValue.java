package virtuozo.infra;

public class KeyValue<K, V> {

  private K key;

  private V value;
  
  public static <K, V> KeyValue<K, V> create(){
    return new KeyValue<K, V>();
  }
  
  protected KeyValue() {
    super();
  }
  
  public K key() {
    return key;
  }
  
  public KeyValue<K, V> key(K key) {
    this.key = key;
    return this;
  }

  public V value() {
    return value;
  }

  public KeyValue<K, V> value(V value) {
    this.value = value;
    return this;
  }
}