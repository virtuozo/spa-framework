package virtuozo.ui;

import virtuozo.infra.ValidationProcess.ValidationAction;
import virtuozo.infra.ValidationProcess.ValidationConstraint;
import virtuozo.infra.api.Validator;
import virtuozo.ui.api.HasFeedback;
import virtuozo.ui.api.UIInput;

public abstract class FormGroup<I extends UIInput<?, V>, V> extends Component<FormGroup<I, V>> implements UIInput<FormGroup<I, V>, V>{
  private I control;

  private final ValidationConstraint<V> constraint;

  private final InputLabel label = new InputLabel(){
    public InputLabel show() {
      this.css().remove("sr-only");
      return this;
    }
    
    public InputLabel hide() {
      this.css("sr-only");
      return this;
    };
  }.hide();
  
  private final HelpBlock help = new HelpBlock(){
    public HelpBlock show() {
      this.css().remove("sr-only");
      return this;
    }
    
    public HelpBlock hide() {
      this.css("sr-only");
      return this;
    };
  }.hide();
  
  private HasFeedback<?> feedback;

  public FormGroup(I input) {
    super(Elements.div());
    this.css("form-group");
    this.control = input;
    this.control.asComponent().css("form-control");
    this.constraint = new ValidationConstraint<V>(this.control);
    this.label.to(this.control);
  }
  
  public FormGroup<I, V> feedback(HasFeedback<?> feedback){
    this.feedback = feedback;
    return this;
  }
  
  public FormGroup<I, V> onValidation(ValidationAction action){
    this.constraint.action(action);
    return this;
  }
  
  public FormGroup<I, V> addValidator(Validator<?, V> validator){
    this.constraint.add(validator);
    return this;
  }

  public InputLabel label() {
    return this.label;
  }
  
  public HelpBlock helpBlock(){
    return this.help;
  }
  
  public HasFeedback<?> feedback() {
    return this.feedback;
  }

  @Override
  public FormGroup<I, V> value(V value) {
    this.control.value(value);
    return this;
  }

  @Override
  public V value() {
    return this.control.value();
  }

  @Override
  public FormGroup<I, V> clear() {
    this.control.clear();
    return this;
  }

  @Override
  public FormGroup<I, V> disable() {
    this.control.disable();
    return this;
  }

  @Override
  public FormGroup<I, V> enable() {
    this.control.enable();
    return this;
  }

  @Override
  public boolean disabled() {
    return this.control.disabled();
  }
  
  public I control(){
    return this.control;
  }
}