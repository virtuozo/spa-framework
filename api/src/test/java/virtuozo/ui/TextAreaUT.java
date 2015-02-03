package virtuozo.ui;

import virtuozo.suite.APITestCase;

public class TextAreaUT extends APITestCase{
  
  public void test() {
    String value = "Value test";
    
    TextArea input = TextArea.create().value(value);
    assertNotNull(input);
    assertEquals(value, input.value());
    
    input.clear();
    assertEquals("", input.value());
    assertEquals(input.element().getTagName(), Elements.textarea().getTagName());
  }
}
