package hitz.virtuozo.ui.api;

import hitz.virtuozo.ui.api.UIComponent;

public interface HasFeedback<H extends HasFeedback<H>> extends UIComponent, HasVisibility<H> {
  H success();

  H warning();

  H error();
}
