package virtuozo.infra;

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