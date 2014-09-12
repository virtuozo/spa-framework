package hitz.virtuozo.ui;

import hitz.virtuozo.infra.ValidationProcess.ValidationAction;
import hitz.virtuozo.infra.ValidationProcess.ValidationConstraint;
import hitz.virtuozo.infra.api.Validator;
import hitz.virtuozo.ui.Elements;
import hitz.virtuozo.ui.InputLabel;
import hitz.virtuozo.ui.Widget;
import hitz.virtuozo.ui.api.HasFeedback;
import hitz.virtuozo.ui.api.UIInput;

public abstract class FormGroup<I extends UIInput<?, V>, V> extends Widget<FormGroup<I, V>> implements UIInput<FormGroup<I, V>, V>{
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

  public FormGroup(I input, HasFeedback<?> feedback) {
    super(Elements.div());
    this.css("form-group");
    this.control = input;
    this.control.asWidget().css("form-control");
    this.constraint = new ValidationConstraint<V>(this.control);
    this.label.to(this.control);
    this.feedback = feedback;
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