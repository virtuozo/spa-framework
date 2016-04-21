package virtuozo.infra;


class PlaceChangeEvent extends Event<Place> {
  private static final PlaceChangeEvent instance = new PlaceChangeEvent();
  
  public static PlaceChangeEvent get() {
    return instance;
  }
  
  private PlaceChangeEvent() {
    super();
  }
}