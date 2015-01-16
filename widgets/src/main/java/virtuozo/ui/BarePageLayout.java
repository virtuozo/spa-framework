package virtuozo.ui;

import virtuozo.ui.interfaces.Layout;

public class BarePageLayout implements Layout<BarePageLayout> {
  private Navbar navbar = Navbar.create();
  
  private Container body = Container.fluid();
  
  public static BarePageLayout create(){
    return new BarePageLayout();
  }
  
  private BarePageLayout() {
    super();
  }
  
  public Container body() {
    return body;
  }
  
  public Navbar navbar() {
    return navbar;
  }
  
  @Override
  public BarePageLayout attach() {
    HTML.body().addChild(this.navbar).addChild(this.body);
    return this;
  }
  
  @Override
  public BarePageLayout detach() {
    HTML.body().detachChildren();
    this.navbar.detachChildren();
    this.body.detachChildren();
    return this;
  }
}