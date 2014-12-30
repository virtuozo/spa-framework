package virtuozo.ui.api;

public interface HasEnablement<T> {
  T enable();
  
  T disable();
  
  boolean disabled();
}
