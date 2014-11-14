package hitz.virtuozo.ui;

import hitz.virtuozo.ui.OrderList.Type;
import hitz.virtuozo.ui.api.Icon;
import hitz.virtuozo.ui.api.UIWidget;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;

public class Carousel extends Widget<Carousel> {
  private OrderList indicators = new OrderList(Type.ORDERED).css("carousel-indicators");

  private Tag<DivElement> slides = Tag.asDiv().css("carousel-inner");

  private Control left = new Control(ControlType.LEFT);

  private Control right = new Control(ControlType.RIGHT);

  private int selection;

  public Carousel() {
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
      indicator.css("active");
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
    for (UIWidget child : this.slides.children()) {
      child.asWidget().hide().css().remove("active");
      this.indicators.childAt(index++).css().remove("active");
    }

    index = this.slides.indexOf(slide);
    this.indicators.childAt(index).css("active");
    slide.show().css("active");
    this.selection = index;
  }

  public class Slide extends Widget<Slide> {
    private Image image = new Image();

    private Caption caption = new Caption();

    public Slide() {
      super(Elements.div());
      this.css("item").addChild(this.image).addChild(this.caption);
    }

    public Slide image(ImageResource image) {
      this.image.src(image);
      return this;
    }

    public Slide image(String url) {
      this.image.src(url);
      return this;
    }

    public Caption caption() {
      return this.caption;
    }

    public class Caption extends Composite<Caption> {
      public Caption() {
        super(Elements.div());
        this.css("carousel-caption");
      }
    }
  }

  class Control extends Widget<Control> {

    public Control(ControlType type) {
      super(Elements.a());
      this.css(type.css(), "carousel-control");
      type.icon().appendTo(this);
      this.element().setHref("#");
    }

    @Override
    public AnchorElement element() {
      return super.element();
    }
  }

  public class SlideAnimation extends Animation {
    private final UIWidget widget;
    private boolean opening;

    public SlideAnimation(UIWidget widget) {
      this.widget = widget;
    }

    @Override
    protected void onComplete() {
      if (!opening) {
        this.widget.hide();
      }
      this.widget.asWidget().style().property("height", "auto");
    }

    @Override
    protected void onStart() {
      super.onStart();
      this.opening = !this.widget.asWidget().visible();

      if (opening) {
        this.widget.asWidget().style().height(0, Unit.PX);
        this.widget.show();
      }
    }

    @Override
    protected void onUpdate(double progress) {
      int scrollHeight = DOM.getElementPropertyInt(this.widget.getElement(), "scrollHeight");
      int height = (int) (progress * scrollHeight);
      if (!opening) {
        height = scrollHeight - height;
      }
      height = Math.max(height, 1);
      DOM.setStyleAttribute(this.widget.getElement(), "height", height + "px");
    }
  }

  enum ControlType {
    LEFT, RIGHT;

    String css() {
      return this.name().toLowerCase();
    }

    Icon icon() {
      if (this.equals(LEFT)) {
        return Glyphicon.CHEVRON_LEFT;
      }

      return Glyphicon.CHEVRON_RIGHT;
    }
  }
}

/*
 * <div id="carousel-example-generic" class="carousel slide" data-ride="carousel"> <!-- Indicators
 * --> <ol class="carousel-indicators"> <li data-target="#carousel-example-generic"
 * data-slide-to="0" class="active"></li> <li data-target="#carousel-example-generic"
 * data-slide-to="1"></li> <li data-target="#carousel-example-generic" data-slide-to="2"></li> </ol>
 * 
 * <!-- Wrapper for slides --> <div class="carousel-inner" role="listbox"> <div class="item active">
 * <img src="..." alt="..."> <div class="carousel-caption"> ... </div> </div> <div class="item">
 * <img src="..." alt="..."> <div class="carousel-caption"> ... </div> </div> ... </div>
 * 
 * <!-- Controls --> <a class="left carousel-control" href="#carousel-example-generic" role="button"
 * data-slide="prev"> <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
 * <span class="sr-only">Previous</span> </a> <a class="right carousel-control"
 * href="#carousel-example-generic" role="button" data-slide="next"> <span
 * class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> <span
 * class="sr-only">Next</span> </a> </div>
 */
