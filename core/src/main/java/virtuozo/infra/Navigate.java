package virtuozo.infra;

import virtuozo.interfaces.Place;
import virtuozo.ui.interfaces.HasClickHandlers;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class Navigate {
  private Place place;

  public static Navigate to(Place place){
    return new Navigate(place);
  }
  
  private Navigate(Place place) {
    this.place = place;
  }
  
  public void through(HasClickHandlers<?> handler){
    handler.onClick(new ClickHandler() {
      
      @Override
      public void onClick(ClickEvent event) {
        Historian.get().forwardTo(Navigate.this.place);
      }
    });
  }
}