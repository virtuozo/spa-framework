package virtuozo.infra;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;

public class Async {
  private static Async instance = new Async();
  
  private Async() {
  }
  
  public static Async get(){
    return instance;
  }
  
  public void execute(final Runnable runner){
    Scheduler.get().scheduleDeferred(new ScheduledCommand() {
      @Override
      public void execute() {
        runner.run();
      }
    });
  }
  
  public void execute(RepeatingCommand command){
    Scheduler.get().scheduleIncremental(command);
  }
  
  public void execute(final Runnable runner, int delayMs){
    Scheduler.get().scheduleFixedDelay(new RepeatingCommand() {
      @Override
      public boolean execute() {
        runner.run();
        return false;
      }
    }, delayMs);
  }
  
  public void schedule(final Runnable runner, int delayMs){
    Scheduler.get().scheduleFixedDelay(new RepeatingCommand() {
      @Override
      public boolean execute() {
        runner.run();
        return true;
      }
    }, delayMs);
  }
  
  /**
   * Schedules a repeating command that is scheduled with a constant
   * periodicity. That is, the command will be invoked every
   * <code>delayMs</code> milliseconds, regardless of how long the previous
   * invocation took to complete.
   * 
   * @param cmd the command to execute
   * @param delayMs the period with which the command is executed
   */
  public void scheduleFixed(final Runnable runner, int delayMs){
    Scheduler.get().scheduleFixedPeriod(new RepeatingCommand() {
      @Override
      public boolean execute() {
        runner.run();
        return true;
      }
    }, delayMs);
  }
}