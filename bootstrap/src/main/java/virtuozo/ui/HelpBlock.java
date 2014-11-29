package virtuozo.ui;

import virtuozo.ui.Component;
import virtuozo.ui.Elements;
import virtuozo.ui.api.HasHtml;
import virtuozo.ui.api.HasText;

public class HelpBlock extends Component<HelpBlock> implements HasText<HelpBlock>, HasHtml<HelpBlock> {
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