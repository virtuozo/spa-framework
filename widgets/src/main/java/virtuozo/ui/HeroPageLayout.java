package virtuozo.ui;

import virtuozo.ui.interfaces.Layout;

import com.google.gwt.dom.client.StyleInjector;

public class HeroPageLayout implements Layout<HeroPageLayout> {
  private Navbar bar = Navbar.create();
  
  private Header header = new Header();
  
  private Container body = Container.fluid();
  
  public static HeroPageLayout create(){
    return new HeroPageLayout();
  }
  
  private HeroPageLayout() {
    super();
  }
  
  public Navbar navbar() {
    return this.bar;
  }

  @Override
  public HeroPageLayout attach() {
    HTML.body().addChild(this.bar).addChild(this.header).addChild(this.body);
    
    StyleInjector.inject("body {margin-top: 50px;} ");
    StyleInjector.inject(".sb-page-header { position: relative; padding: 30px 15px; text-align: center; margin-bottom: 40px; border-width: 0px;}");
    StyleInjector.inject(".sb-page-header h1 { margin-top: 0;}");
    StyleInjector.inject(".sb-page-header p { margin-bottom: 0;}");
    StyleInjector.inject(".sb-page-header p { margin-bottom: 0;}");
    StyleInjector.inject(".sb-page-header .container { position: relative}");
    StyleInjector.inject("@media ( min-width :768px) { .sb-page-header {padding-top: 60px; padding-bottom: 60px; text-align: left;}");
    
    return this;
  }

  @Override
  public HeroPageLayout detach() {
    HTML.body().detachChildren();
    this.bar.detachChildren();
    this.header.detachChildren();
    
    return this;
  }
  
  public Header header(){
    return this.header;
  }
  
  public Container body(){
    return this.body;
  }

  public class Header extends Composite<Header> {
    private Header() {
      super(Elements.div());
      this.css("sb-page-header");
    }

    public Heading addHeading() {
      Heading heading = Heading.one();
      this.add(heading);
      return heading;
    }

    public Paragraph addText() {
      Paragraph text = Paragraph.create();
      this.add(text);
      return text;
    }
  }
}