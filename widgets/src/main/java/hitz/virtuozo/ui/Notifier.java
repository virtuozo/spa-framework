package hitz.virtuozo.ui;

import hitz.virtuozo.infra.api.EventHandler;
import hitz.virtuozo.ui.Event;
import hitz.virtuozo.ui.HTML;
import hitz.virtuozo.ui.Tag;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;

public class Notifier {
  private static final Notifier instance = new Notifier();
  
  private Tag<DivElement> container = Tag.asDiv().css("notifier");
  
  private Notifier() {
    HTML.body().add(this.container);
  }

  public static Notifier get() {
    return instance;
  }
  
  public Notification notify(final int duration){
    final Notification notification = new Notification();
    this.container.add(notification);
    
    final Timer timer = new Timer(){
      @Override
      public void run() {
        notification.hide(1000);
        this.cancel();
      }
    };
    
    return notification.onShow(new EventHandler<Void>() {
      
      @Override
      public void onEvent(Event<Void> e) {
        timer.schedule(duration);
      }
    }).onClose(new ClickHandler() {
      
      @Override
      public void onClick(ClickEvent event) {
        notification.hide(1000);
        timer.cancel();
      }
    });
  }
  
}