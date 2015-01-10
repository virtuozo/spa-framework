package virtuozo.ui;

import virtuozo.ui.interfaces.HasHtml;
import virtuozo.ui.interfaces.HasText;

public class StaticControl extends Component<StaticControl> implements HasText<StaticControl>, HasHtml<StaticControl> {
  public static StaticControl create(){
    return new StaticControl();
  }
  
  private StaticControl() {
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