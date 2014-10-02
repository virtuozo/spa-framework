package hitz.virtuozo.ui;

import hitz.virtuozo.infra.api.HasVisibility;
import hitz.virtuozo.infra.api.HideEvent;
import hitz.virtuozo.infra.api.HideEvent.HideHandler;
import hitz.virtuozo.infra.api.ShowEvent;
import hitz.virtuozo.infra.api.ShowEvent.ShowHandler;
import hitz.virtuozo.infra.api.ToggleEvent;
import hitz.virtuozo.infra.api.ToggleEvent.ToggleHandler;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.PopupPanel;

public class Modal implements HasVisibility<Modal> {
  private PopupPanel dialog = new PopupPanel();
  
  private InnerModal innerModal = new InnerModal();
  
  private EventHandlers eventBus = new EventHandlers();
  
  public Modal() {
    this.dialog.setGlassEnabled(true);
    this.dialog.setModal(true);
    this.dialog.setWidget(this.innerModal.holder());
    this.dialog.setGlassStyleName("modal-glass");
    this.dialog.setStyleName("modal-window");
  }
  
  public Modal animate(){
    this.dialog.setAnimationEnabled(true);
    return this;
  }
  
  public Modal autoHide(){
    this.dialog.setAutoHideEnabled(true);
    this.dialog.addCloseHandler(new CloseHandler<PopupPanel>() {
      
      @Override
      public void onClose(CloseEvent<PopupPanel> event) {
        Modal.this.eventBus.fire(new HideEvent());
      }
    });
    return this;
  }
  
  public Modal show(){
    this.dialog.show();
    this.center();
    this.eventBus.fire(new ShowEvent());
    return this;
  }
  
  public Modal hide() {
    this.eventBus.fire(new HideEvent());
    this.dialog.hide();
    return this;
  }
  
  @Override
  public Modal onHide(HideHandler handler) {
    this.eventBus.add(HideEvent.type(), handler);
    return this;
  }

  @Override
  public Modal onShow(ShowHandler handler) {
    this.eventBus.add(ShowEvent.type(), handler);
    return this;
  }

  @Override
  public Modal onToggleVisibility(ToggleHandler handler) {
    this.eventBus.add(ToggleEvent.type(), handler);
    return this;
  }

  @Override
  public Modal toggleVisibility() {
    this.eventBus.fire(new ToggleEvent());
    if(this.visible()){
      return this.hide();
    }
    
    return this.show();
  }
  
  public Modal width(double value, Unit unit){
    this.dialog.getElement().getStyle().setWidth(value, unit);
    return this;
  }
  
  public Modal height(double value, Unit unit){
    this.dialog.getElement().getStyle().setHeight(value, unit);
    return this;
  }

  @Override
  public boolean visible() {
    return this.dialog.isShowing();
  }
  
  public Header header(){
    return this.innerModal.header;
  }
  
  public Body body(){
    return this.innerModal.body;
  }
  
  public Footer footer(){
    return this.innerModal.footer;
  }
  
  private void center(){
    int width = this.dialog.getElement().getOffsetWidth();
    int height = this.dialog.getElement().getOffsetHeight();

    int left = (Window.getClientWidth() - width) >> 1;
    int top = (Window.getClientHeight() - height) >> 1;

    this.dialog.setPopupPosition(Math.max(Window.getScrollLeft() + left, 0), Math.max(Window.getScrollTop() + top, 0));
  }
  
  class InnerModal extends Widget<InnerModal> {
    private Header header = new Header();
    
    private Body body = new Body();
    
    private Footer footer = new Footer();
    
    public InnerModal() {
      super(Elements.div());
      Tag<DivElement> content = Tag.asDiv();
      content.css("modal-content");
      content.addChild(this.header).addChild(this.body).addChild(this.footer);
      this.addChild(content);
    }
  }
  
  public class Header extends Widget<Header>{
    private Button close = new Button();
    
    private Heading heading = new Heading(Heading.Level.FOUR);
    
    public Header() {
      super(Elements.div());
      this.css().set("modal-header");
      this.addChild(this.close).addChild(this.heading);
      this.close.element().setInnerHTML("&times;");
      this.close.onClick(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          Modal.this.hide();
        }
      }).css().set("close");
      this.heading.css().set("modal-title");
    }
    
    public Header text(String text){
      this.heading.text(text);
      return this;
    }
  }
  
  public class Body extends Composite<Body>{
    public Body() {
      super(Elements.div());
      this.css().set("modal-body");
    }
  }
  
  public class Footer extends Composite<Footer>{
    public Footer() {
      super(Elements.div());
      this.css().set("modal-footer");
    }
  }
}
