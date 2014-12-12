package virtuozo.ui;

import virtuozo.ui.Container.Type;
import virtuozo.ui.api.Layout;

public class BareLayout implements Layout {
  private Navbar navbar = new Navbar();
  
  private Container container = new Container(Type.FLUID);
  
  public Container container() {
    return container;
  }
  
  public Navbar navbar() {
    return navbar;
  }
  
  @Override
  public void attach() {
    HTML.body().addChild(this.navbar).addChild(this.container);
  }
  
  @Override
  public void detach() {
    HTML.body().detachChildren();
    this.navbar.detachChildren();
    this.container.detachChildren();
  }
}