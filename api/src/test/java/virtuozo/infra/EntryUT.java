package virtuozo.infra;

import virtuozo.suite.APITestCase;

public class EntryUT extends APITestCase {

  public void testKey() {
    String expected = "Virtuozo";
    Entry entry = Entry.create().key(expected);
    assertEquals(expected, entry.key());
    
    entry.key(null);
    assertNull(entry.key());
  }

  public void testValue() {
    String expected = "Virtuozo";
    Entry entry = Entry.create().value(expected);
    
    assertEquals(expected, entry.value());
    
    entry.value(null);
    assertNull(entry.key());
  }
}
