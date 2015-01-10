package virtuozo.ui.interfaces;

import virtuozo.ui.interfaces.HasVisibility;
import virtuozo.ui.interfaces.UIComponent;

public interface HasFeedback<H extends HasFeedback<H>> extends UIComponent, HasVisibility<H> {
  H success();

  H warning();

  H error();
}
