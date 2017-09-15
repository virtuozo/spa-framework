package virtuozo.infra;

import java.util.HashMap;
import java.util.Map;

import virtuozo.infra.events.SubscriptionCallback;
import virtuozo.interfaces.HasComponents;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;

public class Historian {
  public static final Historian instance = new Historian();
  
  private HasComponents<?, ?> target;
  
  private Map<String, Place> places = new HashMap<String, Place>();
  
  private Historian() {
    History.addValueChangeHandler(new HistoryHandler());
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
  }
  
  public static Historian get() {
    return instance;
  }
  
  void forwardTo(Place place){
    History.newItem(place.token());
    PlaceChangeEvent.get().publish().with(place).fire();
  }
  
  public Target handle(Place... places){
    if (places == null) {
      throw new IllegalArgumentException("Places should not be null");
    }

    for (Place place : places) {
      this.places.put(place.token(), place);
    }
    return new Target();
  }
  
  public class Target{
    public Forward target(HasComponents<?, ?> target){
      Historian.this.target = target;
      return new Forward();
    }
  }
  
  public class Forward {
    public void forwardTo(Place place){
      PlaceChangeEvent.get().publish().with(place).fire();
    }
  }
  
  Place resolve(String token) {
    if (!this.places.containsKey(token)) {
      return null; // TODO 404
    }

    return this.places.get(token);
  }
  
  class HistoryHandler implements ValueChangeHandler<String>{
    private Presenter<?> last;
    
    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
      if(this.last != null){
        this.last.detach();
      }
      
      Place place = Historian.this.resolve(event.getValue());
      if(place != null) {
        this.last = place.presenter();
        this.last.go(Historian.this.target);
      }
    }
  }
}