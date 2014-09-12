package hitz.virtuozo.ui;

import java.util.logging.Level;

public class Logger {
  private static final Logger instance = new Logger();
  
  private final java.util.logging.Logger impl = java.util.logging.Logger.getLogger("GWT Logger");

  public static Logger get() {
    return instance;
  }
  
  private Logger() {
    super();
  }
  
  public Logger info(String message){
    this.impl.log(Level.INFO, message);
    return this;
  }
  
  public Logger config(String message){
    this.impl.log(Level.CONFIG, message);
    return this;
  }
  
  public Logger debug(String message){
    this.impl.log(Level.FINE, message);
    return this;
  }
  
  public Logger error(String message){
    this.impl.log(Level.SEVERE, message);
    return this;
  }
  
  public Logger error(Throwable thrown){
    this.impl.log(Level.SEVERE, thrown.getMessage(), thrown);
    return this;
  }
  
  public Logger warning(String message){
    this.impl.log(Level.WARNING, message);
    return this;
  }
}