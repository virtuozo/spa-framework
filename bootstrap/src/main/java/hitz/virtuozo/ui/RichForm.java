package hitz.virtuozo.ui;

import hitz.virtuozo.infra.api.ToggleEvent;
import hitz.virtuozo.infra.api.ToggleEvent.ToggleHandler;
import hitz.virtuozo.ui.api.HasFeedback;
import hitz.virtuozo.ui.api.UIInput;
import hitz.virtuozo.ui.api.UIWidget;

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
      this.feedback().asWidget().compound(this.container);
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
      this.feedback().asWidget().compound(this);
      this.addChild(this.label().css("control-label")).addChild(input).addChild(this.helpBlock());
    }
  }
  
  static class Feedback extends Widget<Feedback> implements HasFeedback<Feedback> {
    private UIWidget icon;
    
    @Override
    protected Feedback compound(Widget<?> widget) {
      return super.compound(widget).css("has-feedback");
    }
    
    @Override
    public Feedback success() {
      this.css(Styles.SUCCESS).icon(Glyphicon.OK);
      return this;
    }

    @Override
    public Feedback warning() {
      this.css(Styles.WARNING).icon(Glyphicon.WARNING_SIGN);
      return this;
    }

    @Override
    public Feedback error() {
      this.css(Styles.ERROR).icon(Glyphicon.REMOVE);
      return this;
    }
    
    private Feedback icon(Glyphicon icon){
      if(this.icon != null){
        this.icon.asWidget().detach();
      }
      
      this.icon = icon.asWidget().asWidget().css("form-control-feedback");
      
      return this.addChild(this.icon);
    }
    
    @Override
    public Feedback hide() {
      this.css().remove(Styles.SUCCESS.name(), Styles.WARNING.name(), Styles.ERROR.name());
      if(this.icon != null){
        this.icon.asWidget().detach();
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