package virtuozo.suite;

import junit.framework.Test;
import junit.framework.TestCase;
import virtuozo.infra.CalendarUT;
import virtuozo.infra.ItemUT;
import virtuozo.infra.MessageFormatUT;
import virtuozo.infra.ValidationProcessUT;
import virtuozo.ui.TagUT;
import virtuozo.ui.TextAreaUT;

import com.google.gwt.junit.tools.GWTTestSuite;

public class GwtUTSuite extends TestCase {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite( "Virtuozo Tests Suite - API" );
    //infra tests
    suite.addTestSuite(CalendarUT.class);
    suite.addTestSuite(ItemUT.class);
    suite.addTestSuite(MessageFormatUT.class);
    suite.addTestSuite(ValidationProcessUT.class);
    //ui testes
    suite.addTestSuite(TagUT.class);
    suite.addTestSuite(TextAreaUT.class );
    return suite;
  }
}