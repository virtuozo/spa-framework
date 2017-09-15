package virtuozo.interfaces;

import virtuozo.interfaces.Container;
import virtuozo.interfaces.HTML;
import virtuozo.interfaces.Layout;
import virtuozo.interfaces.Navbar;

public class NavbarPageLayout implements Layout<NavbarPageLayout> {
  private Navbar navbar = Navbar.create();
  
  private Container body = Container.fluid();
  
  public static NavbarPageLayout create(){
    return new NavbarPageLayout();
  }
  
  private NavbarPageLayout() {
    super();
  }
  
  public Container body() {
    return body;
  }
  
  public Navbar navbar() {
    return navbar;
  }
  
  @Override
  public NavbarPageLayout attach() {
    HTML.body().addChild(this.navbar).addChild(this.body);
    return this;
  }
  
  @Override
  public NavbarPageLayout detach() {
    HTML.body().detachChildren();
    this.navbar.detachChildren();
    this.body.detachChildren();
    return this;
  }
}