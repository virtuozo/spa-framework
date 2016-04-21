package virtuozo.interfaces;

import virtuozo.infra.StyleChooser;
import virtuozo.infra.events.HideEvent;
import virtuozo.infra.events.ShowEvent;
import virtuozo.infra.events.HideEvent.HideHandler;
import virtuozo.infra.events.ShowEvent.ShowHandler;
import virtuozo.interfaces.CssClass;
import virtuozo.interfaces.Tag;
import virtuozo.suite.APITestCase;

import com.google.gwt.dom.client.DivElement;

/**
 * This test case covers all the base API operations using the Tag component
 */
public class TagUT extends APITestCase{
  
  public void testVisibility() {
    final Tag<DivElement> tag = Tag.asDiv();
    final String hideEvent = "hide event worked";
    final String showEvent = "show event worked";
    
    tag.onHide(new HideHandler() {
      
      @Override
      public void onHide(HideEvent event) {
        tag.text(hideEvent);
      }
    });
    
    tag.hide();
    assertFalse(tag.visible());
    assertEquals(hideEvent, tag.text());
    
    tag.onShow(new ShowHandler() {
      
      @Override
      public void onShow(ShowEvent event) {
        tag.text(showEvent);
      }
    });
    tag.show();
    assertTrue(tag.visible());
    assertEquals(showEvent, tag.text());
  }
  
  public void testId(){
    final Tag<DivElement> tag = Tag.asDiv();
    String id = "virtuozo";
    tag.id(id);
    assertEquals(id, tag.id());
  }
  
  public void testCss(){
    String css1 = "css1";
    String css2 = "css2";
    
    Tag<DivElement> tag = Tag.asDiv();
    tag.css(css1);
    
    assertTrue(tag.css().contains(css1));
    
    tag.css().remove(css1);
    assertFalse(tag.css().contains(css1));
    
    tag.css().append(css1);
    assertTrue(tag.css().contains(css1));
    
    tag.css().set(css2);
    assertFalse(tag.css().contains(css1));
    assertTrue(tag.css().contains(css2));
    
    tag.css(css1);
    assertTrue(tag.css().contains(css1));
    assertTrue(tag.css().contains(css2));
    
    tag.css().remove(css2);
    assertTrue(tag.css().contains(css1));
    assertFalse(tag.css().contains(css2));
    
    tag.css(Css.style1);
    assertTrue(tag.css().contains(Css.style1));
    
    tag.css(Css.style2);
    assertFalse(tag.css().contains(Css.style1));
    assertTrue(tag.css().contains(Css.style2));
    
    tag.css().remove(Css.style2);
    assertFalse(tag.css().contains(Css.style1));
    assertFalse(tag.css().contains(Css.style2));
    
    tag.css().set(Css.style1, Css.style2);
    assertFalse(tag.css().contains(Css.style1));
    assertTrue(tag.css().contains(Css.style2));
  }
  
  static class Css extends CssClass{
    static final Css style1 = new Css("style1");
    
    static final Css style2 = new Css("style2");
    
    static final StyleChooser chooser = new StyleChooser(style1, style2);

    public Css(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return chooser;
    }
  }
}