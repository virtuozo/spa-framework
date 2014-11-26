package hitz.virtuozo.ui;

import hitz.virtuozo.ui.api.Assets;
import hitz.virtuozo.ui.api.HasFeedback;
import hitz.virtuozo.ui.api.Icon;
import hitz.virtuozo.ui.api.ToggleEvent;
import hitz.virtuozo.ui.api.UIInput;
import hitz.virtuozo.ui.api.UIComponent;
import hitz.virtuozo.ui.api.ToggleEvent.ToggleHandler;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.DivElement;

public class RichForm extends Form<RichForm> {
  private Type type;
  
  public RichForm(Type type) {
    this.type = type;
    this.css(type.css());
  }
  
  public <I extends UIInput<?, V>, V> FormGroup<I, V> add(I input) {
    FormGroup<I, V> group = this.type.asGroup(input);
    this.addChild(group);
    return group;
  }
  
  public Button addButton(){
    Button button = new Button();
    this.addChild(button);
    return button;
  }
  
  public SplitButton addSplitButton(){
    SplitButton button = new SplitButton();
    this.addChild(button);
    return button;
  }
  
  public DropButton addDropButton(){
    DropButton button = new DropButton();
    this.addChild(button);
    return button;
  }
  
  public enum Type {
    INLINE{
      public String css() {
        return "form-inline";
      }
      
      @Override
      public <I extends UIInput<?, V>, V> FormGroup<I, V> asGroup(I input) {
        return new DefaultFormGroup<I, V>(input);
      }
    }, HORIZONTAL{
      public String css() {
        return "form-horizontal";
      }
      
      @Override
      public <I extends UIInput<?, V>, V> FormGroup<I, V> asGroup(I input) {
        return new HorizontalFormGroup<I, V>(input);
      }
    }, VERTICAL{
      @Override
      public String css() {
        return "form-vertical";
      }
      
      @Override
      public <I extends UIInput<?, V>, V> FormGroup<I, V> asGroup(I input) {
        return new DefaultFormGroup<I, V>(input);
      }
    };
    
    public abstract <I extends UIInput<?, V>, V> FormGroup<I, V> asGroup(I input);
    
    public abstract String css();
  }
  
  static class HorizontalFormGroup<I extends UIInput<?, V>, V> extends FormGroup<I, V>{

    private Tag<DivElement> container = Tag.asDiv().css("col-sm-10", "col-sm-offset-2");
    
    public HorizontalFormGroup(I input) {
      super(input, new Feedback());
      this.addChild(this.label().css("control-label")).addChild(this.container);
      this.feedback().asComponent().incorporate(this.container);
      this.container.add(input).add(this.helpBlock());
      
      this.label().onToggleVisibility(new ToggleHandler() {
        
        @Override
        public void onToggle(ToggleEvent e) {
         if(HorizontalFormGroup.this.label().visible()){
           HorizontalFormGroup.this.container.css().remove("col-sm-offset-2");
           return;
         }
         HorizontalFormGroup.this.container.css().append("col-sm-offset-2");
        }
      });
    }
  }

  static class DefaultFormGroup<I extends UIInput<?, V>, V> extends FormGroup<I, V> {
    public DefaultFormGroup(I input) {
      super(input, new Feedback());
      this.feedback().asComponent().incorporate(this);
      this.addChild(this.label().css("control-label")).addChild(input).addChild(this.helpBlock());
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
    
    private Feedback icon(Icon icon){
      if(this.icon != null){
        this.icon.asComponent().detach();
      }
      
      this.icon = icon.asComponent().asComponent().css("form-control-feedback");
      
      return this.addChild(this.icon);
    }
    
    @Override
    public Feedback hide() {
      this.css().remove(Styles.SUCCESS.name(), Styles.WARNING.name(), Styles.ERROR.name());
      if(this.icon != null){
        this.icon.asComponent().detach();
      }
      return this;
    }
    
    static class Styles extends CssClass{

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