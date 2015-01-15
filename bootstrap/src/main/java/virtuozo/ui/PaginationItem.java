package virtuozo.ui;

import virtuozo.ui.OrderList.ListItem;
import virtuozo.ui.css.State;
import virtuozo.ui.events.ActivationEvent;
import virtuozo.ui.events.ActivationEvent.ActivationHandler;
import virtuozo.ui.events.DeactivationEvent;
import virtuozo.ui.events.DeactivationEvent.DeactivationHandler;
import virtuozo.ui.interfaces.HasClickHandlers;
import virtuozo.ui.interfaces.HasIcon;
import virtuozo.ui.interfaces.HasState;
import virtuozo.ui.interfaces.HasText;
import virtuozo.ui.interfaces.Icon;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;

public class PaginationItem extends Component<PaginationItem> implements HasText<PaginationItem>, HasIcon<PaginationItem>, HasState<PaginationItem>, HasClickHandlers<PaginationItem> {
  private Tag<AnchorElement> link = Tag.asAnchor();

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