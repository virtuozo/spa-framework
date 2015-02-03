package virtuozo.ui;

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
