package virtuozo.ui;

import virtuozo.ui.PillPanel.Pill;
import virtuozo.ui.css.ButtonColor;
import virtuozo.ui.css.Floating;
import virtuozo.ui.css.State;
import virtuozo.ui.events.ActivationEvent;
import virtuozo.ui.events.FinishEvent;
import virtuozo.ui.events.ActivationEvent.ActivationHandler;
import virtuozo.ui.events.DeactivationEvent;
import virtuozo.ui.events.DeactivationEvent.DeactivationHandler;
import virtuozo.ui.events.FinishEvent.FinishHandler;
import virtuozo.ui.events.PageChangeEvent;
import virtuozo.ui.events.PageChangeEvent.PageChangeHandler;
import virtuozo.ui.events.SimpleEventInterceptor;
import virtuozo.ui.interfaces.Assets;
import virtuozo.ui.interfaces.HasText;
import virtuozo.ui.interfaces.UIClass;
import virtuozo.ui.interfaces.UIClasses;
import virtuozo.ui.interfaces.UIComponent;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class Wizard extends Component<Wizard> {
  private Tag<DivElement> card = Tag.asDiv().css("card wizard-card");

  private Heading heading = Heading.three();

  private PillPanel navigation = PillPanel.create();

  private Tag<DivElement> body = Tag.asDiv().css("tab-content");

  private Tag<DivElement> footer = Tag.asDiv().css("wizard-footer");

  private Button next = Button.create().css(Button.Size.SMALL).hide();

  private Button previous = Button.create().css(Button.Size.SMALL).hide();

  private Button finish = Button.create().css(Button.Size.SMALL).hide();

  private SimpleEventInterceptor interceptor = SimpleEventInterceptor.create().off();

  private Color color;

  private int index;

  public static Wizard create(){
    return new Wizard();
  }
  
  private Wizard() {
    super(Elements.div());
    this.css(Color.DEFAULT).css("wizard-container").addChild(this.card);
    this.init();
  }

  private void init() {
    Tag<DivElement> header = Tag.asDiv().css("wizard-header");
    header.add(this.heading);

    Tag<DivElement> right = Tag.asDiv().css(Floating.RIGHT).add(this.next).add(this.finish);
    Tag<DivElement> left = Tag.asDiv().css(Floating.LEFT).add(this.previous);
    this.footer.add(right).add(left).add(Tag.asDiv().css("clearfix"));

    this.card.add(header).add(this.navigation).add(this.body).add(footer);

    Assets assets = GWT.create(Assets.class);
    this.next.icon(assets.nextIcon()).onClick(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        Wizard.this.next();
      }
    });
    this.previous.icon(assets.previousIcon()).onClick(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        Wizard.this.previous();
      }
    });;
    this.finish.icon(assets.successIcon()).onClick(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        Wizard.this.finish();
      }
    });;
  }

  public Wizard css(Color color) {
    this.color = color;
    this.color.visit(this.previous).visit(this.next).visit(this.finish);

    for (UIComponent child : this.navigation.childrenComponents()) {
      Pill pill = (Pill) child;
      if (pill.active()) {
        pill.activate();
      }
    }

    return this;
  }

  @Override
  public UIClasses css() {
    return this.card.css();
  }

  @Override
  public Wizard css(String... classes) {
    this.card.css(classes);
    return this;
  }

  @Override
  public Wizard css(UIClass... classes) {
    this.card.css(classes);
    return this;
  }

  public Wizard hideControls() {
    this.interceptor.on();
    this.footer.hide();
    return this;
  }

  public Heading heading() {
    return this.heading;
  }

  public Wizard next() {
    this.index++;

    if (this.index >= this.navigation.childrenCount() - 1) {
      this.index = this.navigation.childrenCount() - 1;
      this.next.hide();
      this.finish.show();
      this.previous.show();
    }

    return this.go();
  }

  public Wizard previous() {
    this.index--;
    if (this.index <= 0) {
      this.index = 0;
      this.previous.hide();
      this.finish.hide();
      this.next.show();
    }

    return this.go();
  }

  public Wizard finish() {
    return this.fireEvent(new FinishEvent());
  }

  public Wizard onChange(PageChangeHandler handler) {
    return this.addHandler(PageChangeEvent.TYPE, handler);
  }

  public Wizard onFinish(FinishHandler handler) {
    return this.addHandler(FinishEvent.TYPE, handler);
  }

  public Step addStep() {
    final Pill pill = this.navigation.addPill();
    pill.childAt(1).asComponent().onEvent(this.interceptor);
    this.next.show();
    this.finish.hide();
    Step step = new Step(pill);
    this.body.add(step);

    if (this.navigation.childrenCount() == 1) {
      step.activate();
    }

    double width = 100d / this.navigation.childrenCount();
    for (UIComponent child : this.navigation.childrenComponents()) {
      child.asComponent().style().width(width, Unit.PCT);
    }

    return step;
  }

  private Wizard go() {
    for (Step child : this.body.<Step> childrenComponents()) {
      child.deactivate();
    }

    ((Step) this.body.childAt(this.index)).activate();

    this.fireEvent(new PageChangeEvent(this.index));

    return this;
  }

  public class Step extends Composite<Step> implements HasText<Step> {
    private Pill pill;

    private Step(Pill pill) {
      super(Elements.div());
      this.pill = pill;
      this.pill.onActivate(new ActivationHandler() {

        @Override
        public void onActivate(ActivationEvent event) {
          Step.this.css(State.ACTIVE);
          Step.this.pill.css(Wizard.this.color.translate()).childAt(1).asComponent().css(Wizard.this.color.translate());
        }
      }).onDeactivate(new DeactivationHandler() {

        @Override
        public void onDeactivate(DeactivationEvent event) {
          Step.this.css().remove(State.ACTIVE);
          for (Color color : Color.values()) {
            Step.this.pill.css().remove(color.translate());
            Step.this.pill.childAt(1).asComponent().css().remove(color.translate());
          }
        }
      });

      this.css("tab-pane");
    }

    @Override
    public String text() {
      return this.pill.text();
    }

    @Override
    public Step text(String text) {
      this.pill.text(text);
      return this;
    }

    private void activate() {
      this.pill.activate();
    }

    private void deactivate() {
      this.pill.deactivate();
    }
  }

  public static enum Color {
    DEFAULT {
      @Override
      UIClass translate() {
        return ButtonColor.PRIMARY;
      }
    },
    INFO {
      @Override
      UIClass translate() {
        return ButtonColor.INFO;
      }
    },
    SUCCESS {
      @Override
      UIClass translate() {
        return ButtonColor.SUCCESS;
      }
    },
    WARNING {
      @Override
      UIClass translate() {
        return ButtonColor.WARNING;
      }
    },
    DANGER {
      @Override
      UIClass translate() {
        return ButtonColor.DANGER;
      }
    };

    Color visit(Button button) {
      button.css(this.translate());
      return this;
    }

    abstract UIClass translate();
  }
}