package virtuozo.ui;

import com.google.gwt.dom.client.StyleInjector;

import virtuozo.ui.Heading.Level;
import virtuozo.ui.api.Layout;

public class HeroPageLayout implements Layout {
  private Navbar bar = new Navbar();
  
  private Header header = new Header();
  
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
    return new Container(Container.Type.FLUID).attachTo(HTML.body());
  }

  public class Header extends Composite<Header> {
    public Header() {
      super(Elements.div());
      this.css("sb-page-header");
    }

    public Heading addHeading() {
      Heading heading = new Heading(Level.ONE);
      this.add(heading);
      return heading;
    }

    public Paragraph addText() {
      Paragraph text = new Paragraph();
      this.add(text);
      return text;
    }
  }
}