package hitz.virtuozo.application;

import hitz.virtuozo.ui.Composite;
import hitz.virtuozo.ui.HTML;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;

public class Transition {
  private static final Transition instance = new Transition();

  private PlaceResolver resolver;

  private Transition() {
    History.addValueChangeHandler(new ValueChangeHandler<String>() {
      @Override
      public void onValueChange(ValueChangeEvent<String> event) {
        handle(event.getValue());
      }
    });
  }

  public static Transition get() {
    return instance;
  }

  public Transition resolver(PlaceResolver resolver) {
    this.resolver = resolver;
    return this;
  }

  public Flow target(Composite<?> parent) {
    return new Flow(parent);
  }

  void handle(String token) {
    if (this.resolver == null) {
      throw new RuntimeException("PlaceResolver is null. There is no way to resolve history.");
    }

    if (token == null) {
      return;
    }

    Place place = this.resolver.resolve(token);
    this.target(HTML.body()).go(place);
  }

  public class Flow {
    private Composite<?> parent;

    Flow(Composite<?> parent) {
      this.parent = parent;
    }

    public Flow go(Place place) {
      if ("".equals(History.getToken())) {
        History.newItem(place.name());
        place.presenter().go(this.parent);
        return this;
      }

      History.fireCurrentHistoryState();
      return this;
    }
  }

  public static interface PlaceResolver {
    Place resolve(String token);
  }
}