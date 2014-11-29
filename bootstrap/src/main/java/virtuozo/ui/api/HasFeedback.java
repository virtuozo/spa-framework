package virtuozo.ui.api;

import virtuozo.ui.api.HasVisibility;
import virtuozo.ui.api.UIComponent;

public interface HasFeedback<H extends HasFeedback<H>> extends UIComponent, HasVisibility<H> {
  H success();

  H warning();

  H error();
}
