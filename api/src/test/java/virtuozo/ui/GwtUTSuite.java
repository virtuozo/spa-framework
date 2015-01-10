package virtuozo.ui;

import junit.framework.Test;
import junit.framework.TestCase;

import com.google.gwt.junit.tools.GWTTestSuite;

public class GwtUTSuite extends TestCase {

  public static Test suite() {
    GWTTestSuite suite = new GWTTestSuite( "Virtuozo API Tests Suite" );
    suite.addTestSuite(TagUT.class);
    suite.addTestSuite(TextAreaUT.class );
    return suite;
  }
}