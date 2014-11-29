package hitz.virtuozo.infra;

import java.util.logging.Level;

public class Logger {
  private static final Logger instance = new Logger();
  
  private final java.util.logging.Logger impl = java.util.logging.Logger.getLogger("");

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
  
  public Logger info(Throwable thrown){
    this.impl.log(Level.INFO, thrown.getMessage(), thrown);
    return this;
  }
  
  public Logger info(String message, Throwable thrown){
    this.impl.log(Level.INFO, message, thrown);
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
  
  public Logger error(String message, Throwable thrown){
    this.impl.log(Level.SEVERE, message, thrown);
    return this;
  }
  
  public Logger warning(String message){
    this.impl.log(Level.WARNING, message);
    return this;
  }
  
  public Logger warning(Throwable thrown){
    this.impl.log(Level.WARNING, thrown.getMessage(), thrown);
    return this;
  }
  
  public Logger warning(String message, Throwable thrown){
    this.impl.log(Level.WARNING, message, thrown);
    return this;
  }
}