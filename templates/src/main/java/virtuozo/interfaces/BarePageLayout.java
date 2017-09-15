package virtuozo.interfaces;

public class BarePageLayout implements Layout<BarePageLayout> {
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
  
  @Override
  public BarePageLayout attach() {
    HTML.body().addChild(this.body);
    return this;
  }
  
  @Override
  public BarePageLayout detach() {
    HTML.body().detachChildren();
    this.body.detachChildren();
    return this;
  }
}