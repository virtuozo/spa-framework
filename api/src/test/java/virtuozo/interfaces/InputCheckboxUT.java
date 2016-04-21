package virtuozo.interfaces;

import virtuozo.infra.Elements;
import virtuozo.interfaces.InputCheckbox;
import virtuozo.suite.APITestCase;

public class InputCheckboxUT extends APITestCase{
  
  public void test() {
    String value = "Value test";
    
    InputCheckbox input = InputCheckbox.create().value(value);
    assertEquals(value, input.value());
    
    input.clear();
    assertEquals("", input.value());
    assertEquals(input.element().getTagName(), Elements.textarea().getTagName());
  }
}
