package virtuozo.infra;

import virtuozo.infra.interfaces.Place;

class PlaceChangeEvent extends Event<Place> {
  public static final PlaceChangeEvent instance = new PlaceChangeEvent();
  
  public static PlaceChangeEvent get() {
    return instance;
  }
  
  private PlaceChangeEvent() {
    super();
  }
  
  @Override
  protected String name() {
    return this.getClass().getName();
  }
}