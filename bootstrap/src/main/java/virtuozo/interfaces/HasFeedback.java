package virtuozo.interfaces;

import virtuozo.interfaces.HasVisibility;
import virtuozo.interfaces.UIComponent;


public interface HasFeedback<H extends HasFeedback<H>> extends UIComponent, HasVisibility<H> {
  H success();

  H warning();

  H error();
}
