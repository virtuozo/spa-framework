package virtuozo.ui;

import virtuozo.ui.Tag;
import virtuozo.ui.api.Direction;
import virtuozo.ui.api.HasText;
import virtuozo.ui.api.ShowEvent;

import com.google.gwt.dom.client.DivElement;

public class Tooltip extends FloatPanel<Tooltip> implements HasText<Tooltip> {

  private final Tag<DivElement> inner = Tag.asDiv().css("tooltip-inner");
  
  public Tooltip() {
    this.add(this.inner).add(Tag.asDiv().css("tooltip-arrow")).css("tooltip").placement(Direction.LEFT);
  }

  @Override
  public String text() {
    return this.inner.text();
  }

  @Override
  public Tooltip text(String text) {
    this.inner.text(text);
    return this;
  }
}