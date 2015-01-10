package virtuozo.ui;

import virtuozo.ui.interfaces.Layout;

public class BareLayout implements Layout {
  private Navbar navbar = Navbar.create();
  
  private Container container = Container.fluid();
  
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