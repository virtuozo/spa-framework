package virtuozo.interfaces;

import virtuozo.infra.Elements;
import virtuozo.infra.Validator;
import virtuozo.infra.ValidationProcess.ValidationAction;
import virtuozo.infra.ValidationProcess.ValidationConstraint;
import virtuozo.infra.handlers.HasFocusHandlers;
import virtuozo.interfaces.Component;
import virtuozo.interfaces.InputLabel;

import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;

public abstract class FormGroup<I extends UIInput<?, V>, V> extends Component<FormGroup<I, V>> implements HasValue<FormGroup<I, V>, V>{
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
  };
  
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
    this.constraint = ValidationConstraint.create(this.control);
    this.label.to(this.control);
    this.control.asComponent().css("form-control");
  }
  
  ValidationConstraint<V> constraint(){
    return this.constraint;
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
    this.onValidation(new ValidationAction() {
      
      @Override
      public void whenValid() {
        FormGroup.this.feedback().success();
      }
      
      @Override
      public void whenInvalid() {
        FormGroup.this.feedback().error();
      }
    });
    if(this.control instanceof HasFocusHandlers){
      ((HasFocusHandlers<?>) this.control).onBlur(new BlurHandler() {
        
        @Override
        public void onBlur(BlurEvent event) {
          FormGroup.this.validate();
        }
      });
    }
    return this;
  }
  
  boolean validate(){
    return this.constraint.validate();
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
  
  public I control(){
    return this.control;
  }
}