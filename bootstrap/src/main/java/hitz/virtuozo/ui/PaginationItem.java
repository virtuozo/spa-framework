package hitz.virtuozo.ui;

import hitz.virtuozo.infra.api.EventHandler;
import hitz.virtuozo.infra.api.HasClickHandlers;
import hitz.virtuozo.infra.api.HasText;
import hitz.virtuozo.ui.Tag;
import hitz.virtuozo.ui.Widget;
import hitz.virtuozo.ui.api.HasActivation;
import hitz.virtuozo.ui.api.HasIcon;
import hitz.virtuozo.ui.api.HasState;
import hitz.virtuozo.ui.api.Icon;
import hitz.virtuozo.ui.css.State;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;

public class PaginationItem extends Widget<PaginationItem> implements HasText<PaginationItem>, HasIcon<PaginationItem>, HasState<PaginationItem>, HasClickHandlers<PaginationItem> {
  private Tag<AnchorElement> link = Tag.asAnchor();

  PaginationItem(ListItem item) {
    super(item);
    this.addChild(this.link);
  }

  @Override
  public PaginationItem onClick(ClickHandler handler) {
    this.link.onClick(handler);
    return this;
  }

  @Override
  public PaginationItem onDoubleClick(DoubleClickHandler handler) {
    this.link.onDoubleClick(handler);
    return this;
  }

  @Override
  public String text() {
    return this.link.text();
  }

  @Override
  public PaginationItem text(String text) {
    this.link.text(text);
    return this;
  }

  @Override
  public PaginationItem icon(Icon icon) {
    icon.appendTo(this.link);
    return this;
  }
  
  @Override
  public PaginationItem onActivate(EventHandler<Void> handler) {
    return this.addHandler(HasActivation.FireableEvent.ACTIVATE, handler);
  }
  
  @Override
  public PaginationItem onDeactivate(EventHandler<Void> handler) {
    return this.addHandler(HasActivation.FireableEvent.DEACTIVATE, handler);
  }

  public PaginationItem disable() {
    this.css(State.DISABLED);
    return this;
  }

  public boolean disabled() {
    return this.css().contains(State.DISABLED);
  }

  @Override
  public PaginationItem enable() {
    this.css().remove(State.DISABLED);
    return this;
  }

  @Override
  public PaginationItem deactivate() {
    this.css().remove(State.ACTIVE);
    return this;
  }

  public PaginationItem activate() {
    this.css(State.ACTIVE);
    return this;
  }

  public boolean active() {
    return this.css().contains(State.ACTIVE);
  }
  
  @Override
  public boolean match(Element element) {
    return this.link.id().equals(element.getId());
  }
}