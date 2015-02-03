package virtuozo.infra;

import virtuozo.suite.APITestCase;

public class ItemUT extends APITestCase {

  public void testKey() {
    String expected = "key";
    
    Item item = Item.create(expected, "Virtuozo");
    assertEquals(expected, item.key());
    
    item.key(null);
    assertNull(item.key());
  }

  public void testValue() {
    String expected = "Virtuozo";
    Item item = Item.create("key", expected);
    
    assertEquals(expected, item.value());
    
    item.value(null);
    assertNull(item.value());
  }
}