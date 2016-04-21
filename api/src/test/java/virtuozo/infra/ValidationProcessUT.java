package virtuozo.infra;

import virtuozo.infra.ValidationProcess.ValidationAction;
import virtuozo.infra.ValidationProcess.ValidationConstraint;
import virtuozo.interfaces.InputText;
import virtuozo.suite.APITestCase;

public class ValidationProcessUT extends APITestCase {

  public void testValidation() {
    ValidationProcess process = ValidationProcess.create();
    InputText input = InputText.create();
    ValidationConstraint<String> constraint = process.constraintFor(input);
    constraint.action(new ValidationAction() {
      @Override
      public void whenValid() {}
      
      @Override
      public void whenInvalid() {}
    });
    constraint.add(EmailValidator.get());
    
    input.value("mail");
    assertFalse(constraint.validate());
    assertFalse(process.validate());
    
    input.value("mail@server");
    assertFalse(constraint.validate());
    assertFalse(process.validate());
    
    input.value("mail@server.");
    assertFalse(constraint.validate());
    assertFalse(process.validate());
    
    input.value("mail@server.c");
    assertFalse(constraint.validate());
    assertFalse(process.validate());
    
    input.value("mail@server.co");
    assertTrue(constraint.validate());
    assertTrue(process.validate());
  }
}