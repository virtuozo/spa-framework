package virtuozo.ui;

import virtuozo.ui.interfaces.Layout;

import com.google.gwt.dom.client.StyleInjector;

public class HeroPageLayout implements Layout {
  private Navbar bar = Navbar.create();
  
  private Header header = new Header();
  
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
  public void attach() {
    HTML.body().addChild(this.bar).addChild(this.header);
    
    StyleInjector.inject("body {margin-top: 50px;} ");
    StyleInjector.inject(".sb-page-header { position: relative; padding: 30px 15px; text-align: center; margin-bottom: 40px; border-width: 0px;}");
    StyleInjector.inject(".sb-page-header h1 { margin-top: 0;}");
    StyleInjector.inject(".sb-page-header p { margin-bottom: 0;}");
    StyleInjector.inject(".sb-page-header p { margin-bottom: 0;}");
    StyleInjector.inject(".sb-page-header .container { position: relative}");
    StyleInjector.inject("@media ( min-width :768px) { .sb-page-header {padding-top: 60px; padding-bottom: 60px; text-align: left;}");
  }

  @Override
  public void detach() {
    HTML.body().detachChildren();
    this.bar.detachChildren();
    this.header.detachChildren();
  }
  
  public Header header(){
    return this.header;
  }
  
  public Container addContainer(){
    return Container.fluid().attachTo(HTML.body());
  }

  public class Header extends Composite<Header> {
    public Header() {
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