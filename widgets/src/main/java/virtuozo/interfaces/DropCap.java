package virtuozo.interfaces;

import virtuozo.infra.Elements;

import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.resources.client.ImageResource;

public abstract class DropCap extends Component<DropCap> {
  protected Tag<SpanElement> drop = Tag.asSpan();
  
  private DropCap() {
    super(Elements.p());
  }
  
  public static DropCap create(ImageResource resource){
    return new DropCapImage(resource);
  }
  
  public static DropCap create(String resource){
    return new DropCapImage(resource);
  }
  
  public static DropCap create(){
    return new DropCapText();
  }
  
  public abstract DropCap text(String text);
  
  static class DropCapText extends DropCap{

    @Override
    public DropCap text(String text) {
      this.drop.text(text.substring(0, 1));
      this.element().setInnerHTML(this.drop.html() + text.substring(1));
      return this;
    }
  }
  
  static class DropCapImage extends DropCap{
    private RichImage image = RichImage.create();
    
    DropCapImage(ImageResource resource){
      this.image.source(resource);
    }
    
    DropCapImage(String resource){
      this.image.source(resource);
      this.drop.add(this.image);
    }
    
    @Override
    public DropCap text(String text) {
      this.element().setInnerHTML(this.drop.html() + text);
      return this;
    }
  }
}
//float: left; color: #903; font-size: 75px; line-height: 60px; padding-top: 4px; padding-right: 8px; padding-left: 3px;