package virtuozo.interfaces;

public interface HasEnablement<T> {
  T enable();
  
  T disable();
  
  boolean disabled();
}
