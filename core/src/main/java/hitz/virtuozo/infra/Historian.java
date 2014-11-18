package hitz.virtuozo.infra;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class Historian {
  private @Inject HistoryHandler handler;
  
  @Inject
  void init(){
    History.addValueChangeHandler(this.handler);
  }
  
  public void go(Place place) {
    History.newItem(place.token());
  }
  
  @Singleton
  static class HistoryHandler implements ValueChangeHandler<String>{
    private @Inject PlaceChangeEvent placeEvent;
    
    private @Inject PlaceResolver resolver;
    
    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
      Place place = this.resolver.resolve(event.getValue());
      this.placeEvent.publish().with(place).fire();
    }
  }
}
