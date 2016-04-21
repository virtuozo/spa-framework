package virtuozo.interfaces;

import virtuozo.infra.Elements;
import virtuozo.infra.events.CssChangeEvent;
import virtuozo.infra.events.CssChangeEvent.CssChangeHandler;
import virtuozo.infra.handlers.AttachHandler;
import virtuozo.interfaces.Component;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.AttachEvent;

public class StaticControl extends Component<StaticControl> implements HasText<StaticControl>, HasHtml<StaticControl>, UIInput<StaticControl, String> {
  public static StaticControl create(){
    return new StaticControl();
  }
  
  private StaticControl() {
    super(Elements.p());
    this.css("form-control-static");
    this.onAttach(new AttachHandler() {
      @Override
      protected void onAttach(AttachEvent event) {
        css().remove("form-control");
      }
    });
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

  @Override
  public StaticControl value(String value) {
    return this;
  }

  @Override
  public String value() {
    return null;
  }

  @Override
  public StaticControl onChange(ChangeHandler handler) {
    return this;
  }

  @Override
  public StaticControl enable() {
    return this;
  }

  @Override
  public StaticControl disable() {
    return this;
  }

  @Override
  public boolean disabled() {
    return true;
  }

  @Override
  public StaticControl clear() {
    return this;
  }

  @Override
  public StaticControl tabIndex(int index) {
    return this;
  }
}