package virtuozo.infra;

public interface DataBinding<T> {
  T get();
  
  T get(T defaultValue);
  
  void set(T value);
  
  public static interface Attribute{
    String name();
  }
}