package virtuozo.interfaces;

import virtuozo.infra.Elements;
import virtuozo.interfaces.Component;
import virtuozo.interfaces.Tag;
import virtuozo.interfaces.Text;


public class Code extends Component<Code> implements HasHtml<Code> {
  public static Code create(){
    return new Code();
  }
  
  private Code() {
    super(Elements.p());
  }
  
  public Code lineBreak(){
    return this.addChild(Tag.as(Elements.br()));
  }
  
  public Code inline(String text){
    return this.addChild(Tag.as(Elements.create("code")).text(text));
  }
  
  public Code keyboard(String text){
    return this.addChild(Tag.as(Elements.create("kbd")).text(text));
  }
  
  public Code block(String text){
    return this.addChild(Tag.as(Elements.create("pre")).text(text));
  }
  
  public Code scrollable(String text){
    return this.addChild(Tag.as(Elements.create("pre")).css("pre-scrollable").text(text));
  }
  
  public Code variable(String text){
    return this.addChild(Tag.as(Elements.create("var")).text(text));
  }
  
  public Code output(String text){
    return this.addChild(Tag.as(Elements.create("samp")).text(text));
  }
  
  public Code text(String text){
    return this.addChild(Text.create().text(text));
  }
  
  @Override
  public String html() {
    return this.element().getInnerHTML();
  }
  
  @Override
  public Code html(String html) {
    this.element().setInnerHTML(html);
    return this;
  }
}
