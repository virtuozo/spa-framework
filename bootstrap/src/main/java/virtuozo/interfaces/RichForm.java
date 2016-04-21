package virtuozo.interfaces;

import virtuozo.infra.Clause;
import virtuozo.infra.StyleChooser;
import virtuozo.infra.ValidationProcess;
import virtuozo.infra.ValidationProcess.Propagation;
import virtuozo.infra.events.ToggleEvent;
import virtuozo.infra.events.ToggleEvent.ToggleHandler;
import virtuozo.interfaces.Component;
import virtuozo.interfaces.CssClass;
import virtuozo.interfaces.Form;
import virtuozo.interfaces.InputLabel;
import virtuozo.interfaces.Tag;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.DivElement;

public class RichForm extends Form<RichForm> {
  private Type type;

  private ValidationProcess validation = ValidationProcess.create();

  private RichForm(Type type) {
    this.type = type;
    this.css(type.css());
  }

  public static RichForm horizontal() {
    return new RichForm(Type.HORIZONTAL);
  }

  public static RichForm vertical() {
    return new RichForm(Type.VERTICAL);
  }

  public <I extends UIInput<?, V>, V> FormGroup<I, V> add(I input) {
    FormGroup<I, V> group = this.type.asGroup(input);
    this.addChild(group);
    this.validation.add(group.constraint());
    return group;
  }

  public RichForm clear() {
    Iterable<UIComponent> foundList = this.findAll(new Clause() {
      @Override
      public boolean matches(UIComponent component) {
        return component instanceof Feedback;
      }
    });

    for (UIComponent found : foundList) {
      UIInput<?, ?> input = ((Feedback) found).find(new Clause() {

        @Override
        public boolean matches(UIComponent component) {
          return component instanceof UIInput;
        }
      });

      if (input != null) {
        input.clear();
      }
    }

    return this;
  }

  public boolean validate() {
    return this.validation.validate();
  }

  public boolean validate(Propagation propagation) {
    return this.validation.validate(propagation);
  }

  enum Type {
    HORIZONTAL {
      public String css() {
        return "form-horizontal";
      }

      @Override
      public <I extends UIInput<?, V>, V> FormGroup<I, V> asGroup(I input) {
        if (input instanceof Checkbox || input instanceof RadioButton) {
          return new HSelectionGroup<I, V>(input);
        }
        return new HorizontalFormGroup<I, V>(input);
      }
    },
    VERTICAL {
      @Override
      public String css() {
        return "form-vertical";
      }

      @Override
      public <I extends UIInput<?, V>, V> FormGroup<I, V> asGroup(I input) {
        if (input instanceof Checkbox || input instanceof RadioButton) {
          return new VSelectionGroup<I, V>(input);
        }

        return new VerticalFormGroup<I, V>(input);
      }
    };

    public abstract <I extends UIInput<?, V>, V> FormGroup<I, V> asGroup(I input);

    public abstract String css();
  }

  static class VSelectionGroup<I extends UIInput<?, V>, V> extends FormGroup<I, V> {

    private Tag<DivElement> container = Tag.asDiv();

    public VSelectionGroup(final I input) {
      super(input);
      input.asComponent().css().remove("form-control");
      this.feedback(new Feedback());
      this.addChild(this.label());
      this.addChild(this.container);
      this.feedback().asComponent().incorporate(this.container);
      this.container.add(input.asComponent().detach()).add(this.helpBlock());
    }

    @Override
    public InputLabel label() {
      if (this.control() instanceof Checkbox) {
        return ((Checkbox) this.control()).label();
      }

      return ((RadioButton) this.control()).label();
    }
  }

  static class HSelectionGroup<I extends UIInput<?, V>, V> extends FormGroup<I, V> {

    private Tag<DivElement> container = Tag.asDiv().css("col-sm-10", "col-sm-offset-2");

    public HSelectionGroup(I input) {
      super(input);
      input.asComponent().css().remove("form-control");
      this.feedback(new Feedback());
      this.addChild(this.label());
      this.addChild(this.container);
      this.feedback().asComponent().incorporate(this.container);
      this.container.add(input).add(this.helpBlock());
    }

    @Override
    public InputLabel label() {
      if (this.control() instanceof Checkbox) {
        return ((Checkbox) this.control()).label();
      }

      return ((RadioButton) this.control()).label();
    }
  }

  static class HorizontalFormGroup<I extends UIInput<?, V>, V> extends FormGroup<I, V> {

    private Tag<DivElement> container = Tag.asDiv().css("col-sm-10");

    public HorizontalFormGroup(I input) {
      super(input);
      this.feedback(new Feedback());
      this.addChild(this.label().css("control-label", "col-sm-2")).addChild(this.container);
      this.feedback().asComponent().incorporate(this.container);
      this.container.add(input).add(this.helpBlock());

      if (input instanceof StaticControl) {
        this.label().hide();
      }

      this.label().onToggleVisibility(new ToggleHandler() {
        @Override
        public void onToggle(ToggleEvent e) {
          if (HorizontalFormGroup.this.label().visible()) {
            HorizontalFormGroup.this.container.css().remove("col-sm-offset-2");
            return;
          }
          HorizontalFormGroup.this.container.css().append("col-sm-offset-2");
        }
      });
    }
  }

  static class VerticalFormGroup<I extends UIInput<?, V>, V> extends FormGroup<I, V> {
    public VerticalFormGroup(I input) {
      super(input);
      this.feedback(new Feedback().incorporate(this));
      this.addChild(this.label().css("control-label")).addChild(input).addChild(this.helpBlock());
      if (input instanceof StaticControl) {
        this.label().hide();
      }
    }
  }

  static class Feedback extends Component<Feedback> implements HasFeedback<Feedback> {
    private UIComponent icon;

    private Assets assets = GWT.create(Assets.class);

    @Override
    protected Feedback incorporate(Component<?> widget) {
      return super.incorporate(widget).css("has-feedback");
    }

    @Override
    public Feedback success() {
      this.css(Styles.SUCCESS);
      return this;
    }

    @Override
    public Feedback warning() {
      this.css(Styles.WARNING);
      return this;
    }

    @Override
    public Feedback error() {
      this.css(Styles.ERROR);
      return this;
    }

    public Feedback icon(Icon icon) {
      if (this.icon != null) {
        this.icon.asComponent().detach();
      }

      this.icon = icon.asComponent().asComponent().css("form-control-feedback");

      return this.addChild(this.icon);
    }

    @Override
    public Feedback hide() {
      this.css().remove(Styles.SUCCESS.name(), Styles.WARNING.name(), Styles.ERROR.name());
      if (this.icon != null) {
        this.icon.asComponent().detach();
      }
      return this;
    }

    static class Styles extends CssClass {

      public static final Styles SUCCESS = new Styles("has-success");
      public static final Styles WARNING = new Styles("has-warning");
      public static final Styles ERROR = new Styles("has-error");
      static final StyleChooser STYLES = new StyleChooser(SUCCESS, WARNING, ERROR);

      protected Styles(String name) {
        super(name);
      }

      @Override
      protected StyleChooser chooser() {
        return STYLES;
      }
    }
  }
}