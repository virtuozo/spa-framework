package hitz.virtuozo.ui;

import hitz.virtuozo.ui.CssClass;
import hitz.virtuozo.ui.Elements;
import hitz.virtuozo.ui.StyleChooser;
import hitz.virtuozo.ui.Tag;
import hitz.virtuozo.ui.Widget;
import hitz.virtuozo.ui.api.UIWidget;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickHandler;

public class Notification extends Widget<Notification> {
  
  private Tag<DivElement> close = Tag.asDiv().css("notification-close").html("&times;");
  
  private Tag<DivElement> title = Tag.asDiv().css("notification-title");
  
  private Tag<DivElement> message = Tag.asDiv().css("notification-message");
  
  Notification() {
    super(Elements.div());
    this.css("notification").css(Color.DEFAULT, Size.NORMAL).hide();
    this.addChild(this.close).addChild(this.title).addChild(this.message);
  }
  
  public Notification onClose(ClickHandler handler) {
    this.close.onClick(handler);
    return this;
  }
  
  @Override
  public Notification title(String title) {
    this.title.text(title);
    return this;
  }
  
  public Notification message(String message){
    this.message.text(message);
    return this;
  }
  
  public Notification message(UIWidget message){
    this.message.add(message);
    return this;
  }
  
  @Override
  public Notification show(int duration) {
    return super.show(duration);
  }
  
  public Notification hide(int duration) {
    return super.hide(duration);
  }
  
  public static final class Color extends CssClass {

    protected Color(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }

    public static final Color DEFAULT = new Color("notification-default");
    public static final Color ERROR = new Color("notification-error");
    public static final Color NOTICE = new Color("notification-notice");
    public static final Color WARNING = new Color("notification-warning");
    static final StyleChooser STYLES = new StyleChooser(DEFAULT, ERROR, NOTICE, WARNING);
  }

  public static final class Size extends CssClass {

    protected Size(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }

    public static final Size SMALL = new Size("notification-small");
    public static final Size NORMAL = new Size("notification-medium");
    public static final Size LARGE = new Size("notification-large");
    static final StyleChooser STYLES = new StyleChooser(SMALL, NORMAL, LARGE);
  }
}