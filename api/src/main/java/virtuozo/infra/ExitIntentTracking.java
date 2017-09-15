package virtuozo.infra;

import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.user.client.ui.RootPanel;

public class ExitIntentTracking {
  private static ExitIntentTracking instance = new ExitIntentTracking();
  
  public static ExitIntentTracking get(){
    return instance;
  }
  
  private ExitIntentTracking() {
    super();
  }
  
  public void onExit(final ExitIntentHandler handler){
    RootPanel.get().addDomHandler(new MouseOutHandler() {
      
      @Override
      public void onMouseOut(MouseOutEvent event) {
        handler.handle();
      }
    }, MouseOutEvent.getType());
  }
  
  public static interface ExitIntentHandler{
    void handle();
  }
}