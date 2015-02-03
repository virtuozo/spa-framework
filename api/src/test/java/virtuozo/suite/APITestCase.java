package virtuozo.suite;

import com.google.gwt.junit.client.GWTTestCase;

public abstract class APITestCase extends GWTTestCase{

  @Override
  public String getModuleName() {
    return "virtuozo.APISuite";
  }

}
