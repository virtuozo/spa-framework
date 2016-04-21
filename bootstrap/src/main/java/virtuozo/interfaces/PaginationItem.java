package virtuozo.interfaces;

import virtuozo.infra.event.ActivationEvent;
import virtuozo.infra.event.DeactivationEvent;
import virtuozo.infra.event.ActivationEvent.ActivationHandler;
import virtuozo.infra.event.DeactivationEvent.DeactivationHandler;
import virtuozo.infra.handlers.HasClickHandlers;
import virtuozo.interfaces.Anchor;
import virtuozo.interfaces.Component;
import virtuozo.interfaces.OrderList.ListItem;
import virtuozo.interfaces.css.State;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;

public class PaginationItem extends Component<PaginationItem> implements HasText<PaginationItem>, HasIcon<PaginationItem>, HasState<PaginationItem>, HasClickHandlers<PaginationItem> {
  private Anchor link = Anchor.create();

  private EnablementHelper<PaginationItem> helper;
  
  PaginationItem(ListItem item) {
    super(item);
    this.addChild(this.link);
    this.helper = EnablementHelper.to(this).intercept(this.link);
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
    icon.attachTo(this.link);
    return this;
  }
  
  @Override
  public PaginationItem onActivate(ActivationHandler handler) {
    return this.addHandler(ActivationEvent.TYPE, handler);
  }
  
  @Override
  public PaginationItem onDeactivate(DeactivationHandler handler) {
    return this.addHandler(DeactivationEvent.TYPE, handler);
  }

  public PaginationItem disable() {
    return this.helper.disable();
  }

  public boolean disabled() {
    return this.helper.disabled();
  }

  @Override
  public PaginationItem enable() {
    return this.helper.enable();
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