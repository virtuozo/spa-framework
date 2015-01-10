package virtuozo.infra;

import com.google.gwt.junit.client.GWTTestCase;

public abstract class APITestCase extends GWTTestCase{

  @Override
  public String getModuleName() {
    return "virtuozo.API";
  }

}
