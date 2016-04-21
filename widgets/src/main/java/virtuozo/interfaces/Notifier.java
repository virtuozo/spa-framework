package virtuozo.interfaces;

import virtuozo.infra.Elements;
import virtuozo.infra.StyleChooser;
import virtuozo.infra.events.HideEvent;
import virtuozo.infra.events.ShowEvent;
import virtuozo.infra.events.HideEvent.HideHandler;
import virtuozo.infra.events.ShowEvent.ShowHandler;
import virtuozo.interfaces.Component;
import virtuozo.interfaces.Composite;
import virtuozo.interfaces.CssClass;
import virtuozo.interfaces.HTML;
import virtuozo.interfaces.Tag;
import virtuozo.interfaces.Text;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;

public class Notifier {
  private static final Notifier instance = new Notifier();
  
  private Tag<DivElement> container = Tag.asDiv().css("notifier");
  
  public static Notifier get() {
    return instance;
  }
  
  private Notifier() {
    HTML.body().add(this.container);
  }

  public Notification notify(final int duration){
    final Notification notification = new Notification();
    this.container.add(notification);
    
    final Timer timer = new Timer(){
      @Override
      public void run() {
        notification.hide();
        this.cancel();
      }
    };
    
    return notification.onShow(new ShowHandler() {
      
      @Override
      public void onShow(ShowEvent event) {
        timer.schedule(duration);
      }
    }).onClose(new ClickHandler() {
      
      @Override
      public void onClick(ClickEvent event) {
        timer.cancel();
      }
    });
  }
  
  public static class Notification extends Component<Notification> {
    
    private Tag<DivElement> close = Tag.asDiv().css("notification-close").html("&times;");
    
    private Header header = new Header();
    
    private Body body = new Body();
    
    private Notification() {
      super(Elements.div());
      this.css("notification", "alert").css(Color.DEFAULT, Size.NORMAL).hide();
      this.addChild(this.close).addChild(this.header).addChild(this.body);
      this.close.onClick(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          Notification.this.detach();
        }
      });
      this.onHide(new HideHandler() {
        
        @Override
        public void onHide(HideEvent event) {
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
      private Header() {
        super(Elements.div());
        this.css("notification-title");
      }
      
      public Text addText(){
        return Text.create().attachTo(this);
      }
    }
    
    public class Body extends Composite<Body>{
      private Body() {
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
      public static final Color DANGER = new Color("alert-danger");
      public static final Color INFO = new Color("alert-info");
      public static final Color SUCCESS = new Color("alert-success");
      public static final Color WARNING = new Color("alert-warning");
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
}