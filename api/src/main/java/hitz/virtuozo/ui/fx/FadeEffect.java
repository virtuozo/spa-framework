package hitz.virtuozo.ui.fx;

import hitz.virtuozo.ui.api.UIWidget;

class FadeEffect extends Effect<FadeEffect> {

  private Fade run;
  
  public FadeEffect(Fade run, UIWidget target) {
    super(target);
    this.run = run;
  }
  
  @Override
  protected FadeEffect onUpdate(double progress) {
    this.run.apply(this.style(), progress);
    return this;
  }
  
  @Override
  protected FadeEffect onComplete() {
    return this;
  }

  
}
