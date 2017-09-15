package virtuozo.interfaces;

import virtuozo.infra.Elements;
import virtuozo.interfaces.Component;
import virtuozo.interfaces.Composite;
import virtuozo.interfaces.Tag;
import virtuozo.interfaces.OrderList.ListItem;
import virtuozo.interfaces.css.State;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Timer;

public class Carousel extends Component<Carousel> {
  private OrderList indicators = OrderList.ordered().css("carousel-indicators");

  private Tag<DivElement> slides = Tag.asDiv().css("carousel-inner");

  private Control left = new Control(ControlType.LEFT);

  private Control right = new Control(ControlType.RIGHT);
  
  private Player player = new Player();

  private int selection;

  public static Carousel create(){
    return new Carousel();
  }
  
  private Carousel() {
    super(Elements.div());
    this.css("carousel slide");
    this.addChild(this.indicators).addChild(this.slides).addChild(this.left).addChild(this.right);
    this.left.on(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        previous();
      }
    });
    this.right.on(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        next();
      }
    });
  }
  
  public Player autoPlay(){
    return this.player;
  }

  public Slide addSlide() {
    final Slide slide = new Slide();

    ListItem indicator = this.indicators.addItem().on(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        go(slide);
      }
    });

    if (!this.slides.hasChildren()) {
      slide.style().display(Display.BLOCK);
      indicator.css(State.ACTIVE);
    }

    this.slides.add(slide);

    return slide;
  }

  private void previous() {
    int index = this.selection - 1;
    if (index < 0) {
      index = this.slides.childrenCount() - 1;
    }
    go((Slide) this.slides.childAt(index));
  }

  private void next() {
    int index = this.selection + 1;
    if (index >= this.slides.childrenCount()) {
      index = 0;
    }

    go((Slide) this.slides.childAt(index));
  }

  private void go(Slide slide) {
    int index = 0;
    for (UIComponent child : this.slides.children()) {
      child.asComponent().hide().css().remove(State.ACTIVE);
      this.indicators.childAt(index++).css().remove(State.ACTIVE);
    }

    index = this.slides.indexOf(slide);
    this.indicators.childAt(index).css(State.ACTIVE);
    slide.show().css(State.ACTIVE);
    this.selection = index;
  }

  public class Slide extends Component<Slide> {
    private RichImage image = RichImage.create();

    private Caption caption = new Caption();

    public Slide() {
      super(Elements.div());
      this.css("item").addChild(this.image).addChild(this.caption);
    }

    public Slide image(ImageResource image) {
      this.image.source(image);
      return this;
    }

    public Slide image(String url) {
      this.image.source(url);
      return this;
    }

    public Caption caption() {
      return this.caption;
    }

    public class Caption extends Composite<Caption> {
      private Caption() {
        super(Elements.div());
        this.css("carousel-caption");
      }

      public RichHeading addHeading() {
        return RichHeading.three().attachTo(this);
      }

      public Paragraph addText() {
        return Paragraph.create().attachTo(this);
      }
    }
  }

  class Control extends Component<Control> {

    private Control(ControlType type) {
      super(Elements.a());
      this.css(type.css(), "carousel-control");
      type.icon().attachTo(this);
      this.element().setHref("javascript:void(0)");
    }

    @Override
    public AnchorElement element() {
      return super.element();
    }
  }
  
  public class Player {
    private Timer timer;
    
    public Carousel every(int delay){
      this.timer = new Timer() {
        
        @Override
        public void run() {
          Carousel.this.next();
        }
      };
      
      this.timer.scheduleRepeating(delay);
      return Carousel.this;
    }
    
    public Carousel stop(){
      if(this.timer != null){
        this.timer.cancel();
      }
      
      return Carousel.this;
    }
  }

  enum ControlType {
    LEFT, RIGHT;

    private Assets assets = GWT.create(Assets.class);
    
    String css() {
      return this.name().toLowerCase();
    }

    public Icon icon() {
      if (this.equals(LEFT)) {
        return this.assets.previousIcon();
      }

      return this.assets.nextIcon();
    }
  }
}