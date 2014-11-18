package hitz.virtuozo.ui;

import hitz.virtuozo.ui.Elements;
import hitz.virtuozo.ui.Component;
import hitz.virtuozo.ui.api.HasHtml;
import hitz.virtuozo.ui.api.HasText;

public class StaticControl extends Component<StaticControl> implements HasText<StaticControl>, HasHtml<StaticControl> {
  public StaticControl() {
    super(Elements.p());
    this.css("form-control-static");
  }
  
  @Override
  public String text() {
    return this.element().getInnerText();
  }
  
  @Override
  public StaticControl text(String text) {
    this.element().setInnerText(text);
    return this;
  }
  
  @Override
  public String html() {
    return this.element().getInnerHTML();
  }
  
  @Override
  public StaticControl html(String html) {
    this.element().setInnerHTML(html);
    return this;
  }
}