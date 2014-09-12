package hitz.virtuozo.ui.api;

import hitz.virtuozo.infra.api.HasVisibility;
import hitz.virtuozo.ui.api.UIWidget;

public interface HasFeedback<H extends HasFeedback<H>> extends UIWidget, HasVisibility<H> {
  H success();

  H warning();

  H error();
}
