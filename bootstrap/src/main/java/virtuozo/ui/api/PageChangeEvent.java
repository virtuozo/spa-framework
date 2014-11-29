package virtuozo.ui.api;

import virtuozo.ui.api.PageChangeEvent.PageChangeHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class PageChangeEvent extends GwtEvent<PageChangeHandler>{
  public static final Type<PageChangeHandler> TYPE = new Type<PageChangeHandler>();
  
  private Integer page;
  
  public PageChangeEvent(Integer page) {
    super();
    this.page = page;
  }

  @Override
  public Type<PageChangeHandler> getAssociatedType() {
    return TYPE;
  }
  
  protected void dispatch(PageChangeHandler handler) {
    handler.onPageChange(this);
  }
  
  public Integer page() {
    return page;
  }
  
  public static interface PageChangeHandler extends EventHandler{
    void onPageChange(PageChangeEvent event);
  }
}
