package virtuozo.ui;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class Notification extends Component<Notification> {
  
  private Tag<DivElement> close = Tag.asDiv().css("notification-close").html("&times;");
  
  private Header header = new Header();
  
  private Body body = new Body();
  
  Notification() {
    super(Elements.div());
    this.css("notification").css(Color.DEFAULT, Size.NORMAL).hide();
    this.addChild(this.close).addChild(this.header).addChild(this.body);
    this.close.onClick(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        Notification.this.detach();
      }
    });
  }
  
  public Notification onClose(ClickHandler handler) {
    this.close.onClick(handler);
    return this;
  }
  
  public Header header() {
    return header;
  }
  
  public Body body() {
    return body;
  }
  
  public class Header extends Composite<Header>{
    public Header() {
      super(Elements.div());
      this.css("notification-title");
    }
    
    public Text addText(){
      return Text.create().attachTo(this);
    }
  }
  
  public class Body extends Composite<Body>{
    public Body() {
      super(Elements.div());
      this.css("notification-message");
    }
    
    public Text addText(){
      return Text.create().attachTo(this);
    }
  }
  
  public static final class Color extends CssClass {

    protected Color(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }

    public static final Color DEFAULT = new Color("btn-default");
    public static final Color DANGER = new Color("btn-danger");
    public static final Color INFO = new Color("btn-info");
    public static final Color SUCCESS = new Color("btn-success");
    public static final Color WARNING = new Color("btn-warning");
    static final StyleChooser STYLES = new StyleChooser(DEFAULT, DANGER, INFO, SUCCESS, WARNING);
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