package virtuozo.ui.interfaces;


public interface HasFeedback<H extends HasFeedback<H>> extends UIComponent, HasVisibility<H> {
  H success();

  H warning();

  H error();
}
