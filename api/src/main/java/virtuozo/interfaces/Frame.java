package virtuozo.interfaces;

import virtuozo.infra.Elements;

import com.google.gwt.dom.client.IFrameElement;

public class Frame extends Component<Frame> {
  private IFrameElement element;
  
  public static Frame create(){
    return new Frame();
  }
  
  public static Frame create(Ratio ratio){
    return new Frame(ratio);
  }
  
  private Frame() {
    super(Elements.iframe());
    this.element = super.element();
  }
  
  private Frame(Ratio ratio){
    this.incorporate(Tag.asDiv().css(ratio.className()));
    this.element = Elements.iframe();
    this.addChild(Tag.as(this.element));
  }
  
  public Frame border(int border){
    this.element().setFrameBorder(border);
    return this;
  }
  
  public int border(){
    return this.element().getFrameBorder();
  }
  
  public Frame height(String height){
    this.element().setPropertyString("height", height);
    return this;
  }
  
  public Frame marginHeight(int marginHeight){
    this.element().setMarginHeight(marginHeight);
    return this;
  }
  
  public Frame marginWidth(int marginWidth){
    this.element().setMarginWidth(marginWidth);
    return this;
  }
  
  public Frame noResize(boolean noResize){
    this.element().setNoResize(noResize);
    return this;
  }
  
  public boolean noResize(){
    return this.element().isNoResize();
  }
  
  public Frame scrolling(String scrolling){
    this.element().setScrolling(scrolling);
    return this;
  }
  
  public String scrolling(){
    return this.element().getScrolling();
  }
  
  public Frame source(String source){
    this.element().setSrc(source);
    return this;
  }
  
  public String source(){
    return this.element().getSrc();
  }
  
  public Frame width(String width){
    this.element().setPropertyString("width", width);
    return this;
  }
  
  public static enum Ratio{
    FourByThree{
      @Override
      public String className() {
        return "embed-responsive embed-responsive-4by3";
      }
    },
    SixteenByNine{
      @Override
      public String className() {
        return "embed-responsive embed-responsive-16by9";
      }
    };
    
    public abstract String className();
  }
  
  protected IFrameElement element() {
    return this.element;
  }
}