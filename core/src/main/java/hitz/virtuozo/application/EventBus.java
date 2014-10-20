package hitz.virtuozo.application;

import hitz.virtuozo.ui.EventHandlers;

public class EventBus extends EventHandlers {
  private static final EventBus instance = new EventBus();
  
  private EventBus() {}
  
  public static EventBus get() {
    return instance;
  }
}