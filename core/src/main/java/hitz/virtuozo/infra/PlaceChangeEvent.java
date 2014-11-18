package hitz.virtuozo.infra;


public class PlaceChangeEvent extends Event<Place> {
  
  @Override
  protected String name() {
    return this.getClass().getName();
  }
}