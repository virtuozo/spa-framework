package virtuozo.ui;

import virtuozo.infra.ValidationProcess;
import virtuozo.infra.ValidationProcess.Propagation;
import virtuozo.ui.events.ToggleEvent;
import virtuozo.ui.events.ToggleEvent.ToggleHandler;
import virtuozo.ui.interfaces.Assets;
import virtuozo.ui.interfaces.HasFeedback;
import virtuozo.ui.interfaces.Icon;
import virtuozo.ui.interfaces.UIComponent;
import virtuozo.ui.interfaces.UIInput;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.DivElement;

public class RichForm extends Form<RichForm> {
  private Type type;

  private Footer footer = new Footer();

  private ValidationProcess validation = ValidationProcess.create();

  private RichForm(Type type) {
    this.type = type;
    this.css(type.css()).addChild(this.footer);
    if (type.equals(Type.HORIZONTAL)) {
      this.footer.toolbar.css("col-sm-offset-2", "col-sm-10");
    }
  }

  public static RichForm horizontal() {
    return new RichForm(Type.HORIZONTAL);
  }

  public static RichForm vertical() {
    return new RichForm(Type.VERTICAL);
  }

  public <I extends UIInput<?, V>, V> FormGroup<I, V> add(I input) {
    FormGroup<I, V> group = this.type.asGroup(input);
    this.insertChild(group, this.footer);
    this.validation.add(group.constraint());
    return group;
  }

  public boolean validate() {
    return this.validation.validate();
  }

  public boolean validate(Propagation propagation) {
    return this.validation.validate(propagation);
  }

  public Footer footer() {
    return footer;
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

    public VSelectionGroup(I input) {
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
      this.feedback(new Feedback());
      this.feedback().asComponent().incorporate(this);
      this.addChild(this.label().css("control-label")).addChild(input).addChild(this.helpBlock());
    }
  }

  public class Footer extends Component<Footer> {
    private Tag<DivElement> toolbar = Tag.asDiv().css("form-footer");

    private Footer() {
      super(Elements.div());
      this.css("form-group").addChild(this.toolbar);
    }

    public Button addButton() {
      Button button = Button.create();
      this.toolbar.add(button);
      return button;
    }

    public SplitButton addSplitButton() {
      SplitButton button = SplitButton.create();
      this.toolbar.add(button);
      return button;
    }

    public DropButton addDropButton() {
      DropButton button = DropButton.create();
      this.toolbar.add(button);
      return button;
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
      this.css(Styles.SUCCESS).icon(this.assets.successIcon());
      return this;
    }

    @Override
    public Feedback warning() {
      this.css(Styles.WARNING).icon(this.assets.warningIcon());
      return this;
    }

    @Override
    public Feedback error() {
      this.css(Styles.ERROR).icon(this.assets.errorIcon());
      return this;
    }

    private Feedback icon(Icon icon) {
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