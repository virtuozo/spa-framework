package virtuozo.infra;

import java.util.HashMap;
import java.util.Map;

import virtuozo.infra.Logger;
import virtuozo.ui.api.HasComponents;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;

public class PlaceRepository {
  public static final PlaceRepository instance = new PlaceRepository();
  
  private Map<String, Place> places = new HashMap<String, Place>();
  
  private HasComponents<?, ?> container;
  
  private PlaceRepository() {
    History.addValueChangeHandler(new HistoryHandler());
    GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
      @Override
      public void onUncaughtException(Throwable e) {
        //TODO handle error notification
      }
    });
  }
  
  public static PlaceRepository get() {
    return instance;
  }
  
  public PlaceRepository put(Place... places){
    if(places == null){
      throw new IllegalArgumentException("Places should not be null");
    }
    
    for(Place place : places) {
      this.places.put(place.token(), place);
    }
    return this;
  }
  
  public Forward forwardTo(final HasComponents<?, ?> container){
    this.container = container;
    
    PlaceChangeEvent.get().subscribe().to(new SubscriptionCallback<Place>() {
      @Override
      public void onPublish(Place subject) {
        Logger.get().info("Place changed: " + subject.token());
        if("".equals(History.getToken())) {
          History.newItem(subject.token());
          return;
        }
        
        History.fireCurrentHistoryState();
      }
    });
    
    return new Forward();
  }
  
  Place resolve(String token){
    if(!this.places.containsKey(token)){
      return null; //TODO 404
    }
    
    return this.places.get(token);
  }
  
  class HistoryHandler implements ValueChangeHandler<String>{
    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
      Place place = PlaceRepository.this.resolve(event.getValue());
      if(place != null){
        place.presenter().go(PlaceRepository.this.container);
      }
    }
  }
  
  public class Forward {
    public void go(Place place){
      PlaceChangeEvent.get().publish().with(place).fire();
    }
  }
}
