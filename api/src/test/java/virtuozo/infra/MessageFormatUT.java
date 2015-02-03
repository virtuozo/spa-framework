package virtuozo.infra;

import virtuozo.suite.APITestCase;

public class MessageFormatUT extends APITestCase {

  public void testFormat() {
    String expected = "Virtuozo is an opinionated all-in-one SPA framework for Java lovers";
    String template = "Virtuozo is an {0} all-in-one {1} framework for {2} lovers";
    String actual = MessageFormat.format(template, "opinionated", "SPA", "Java");
    
    assertEquals(expected, actual);
  }
  
  public void testFormatWithNumbers() {
    String expected = "13 from 30 pages were found";
    String template = "{0} from {1} pages were found";
    String actual = MessageFormat.format(template, 13, 30);
    
    assertEquals(expected, actual);
  }
}