package hitz.virtuozo.ui;

import hitz.virtuozo.infra.api.HasHtml;
import hitz.virtuozo.infra.api.HasText;
import hitz.virtuozo.ui.Elements;
import hitz.virtuozo.ui.Widget;

public class HelpBlock extends Widget<HelpBlock> implements HasText<HelpBlock>, HasHtml<HelpBlock> {
  public HelpBlock() {
    super(Elements.p());
    this.css("help-block");
  }
  
  @Override
  public String html() {
    return this.element().getInnerHTML();
  }
  
  @Override
  public HelpBlock html(String html) {
    this.element().setInnerHTML(html);
    return this;
  }
  
  @Override
  public String text() {
    return this.element().getInnerText();
  }
  
  @Override
  public HelpBlock text(String text) {
    this.element().setInnerText(text);
    return this;
  }
}
