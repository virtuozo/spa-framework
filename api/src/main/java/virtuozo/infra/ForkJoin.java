package virtuozo.infra;

public class ForkJoin {
  private int numberOfProcess;

  private int joined;

  private TaskCompleteHandler handler;

  private ForkJoin(int numberOfProcess) {
    this.numberOfProcess = numberOfProcess;
  }

  public static ForkJoin create(int numberOfProcess) {
    return new ForkJoin(numberOfProcess);
  }

  public ForkJoin join() {
    this.joined++;
    if(this.numberOfProcess == this.joined){
      this.handler.onComplete();
    }
    return this;
  }

  public ForkJoin onComplete(TaskCompleteHandler handler) {
    this.handler = handler;
    return this;
  }

  public static interface TaskCompleteHandler {
    void onComplete();
  }
}