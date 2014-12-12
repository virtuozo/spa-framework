package virtuozo.ui;

import virtuozo.ui.api.HasHtml;

public class InlineText extends Component<InlineText> implements HasHtml<InlineText> {
  public InlineText() {
    super(Elements.p());
  }
  
  public InlineText lineBreak(){
    return this.addChild(Tag.as(Elements.br()));
  }
  
  public InlineText bold(String text){
    return this.addChild(Tag.as(Elements.strong()).text(text));
  }
  
  public InlineText italicized(String text){
    return this.addChild(Tag.as(Elements.em()).text(text));
  }
  
  public InlineText inserted(String text){
    return this.addChild(Tag.as(Elements.create("ins")).text(text));
  }
  
  public InlineText marked(String text){
    return this.addChild(Tag.as(Elements.create("mark")).text(text));
  }
  
  public InlineText deleted(String text){
    return this.addChild(Tag.as(Elements.create("del")).text(text));
  }
  
  public InlineText small(String text){
    return this.addChild(Tag.as(Elements.small()).text(text));
  }
  
  public InlineText strikethrough(String text){
    return this.addChild(Tag.as(Elements.create("s")).text(text));
  }
  
  public InlineText text(String text) {
    return this.addChild(new Text().text(text));
  }
  
  public InlineText underlined(String text){
    return this.addChild(Tag.as(Elements.create("u")).text(text));
  }
  
  @Override
  public String html() {
    return this.element().getInnerHTML();
  }
  
  @Override
  public InlineText html(String html) {
    this.element().setInnerHTML(html);
    return this;
  }
}
