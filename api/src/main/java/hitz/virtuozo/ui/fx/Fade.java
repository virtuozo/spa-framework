package hitz.virtuozo.ui.fx;

import hitz.virtuozo.ui.Style;
import hitz.virtuozo.ui.api.UIWidget;

public enum Fade {
  IN {
    @Override
    void apply(Style target, double progress) {
      target.opacity(progress);
    }
  },
  OUT {
    @Override
    void apply(Style target, double progress) {
      target.opacity(1 - progress);
    }
  };

  abstract void apply(Style target, double progress);
  
  public Effect<?> run(UIWidget target, int duration) {
    return this.asEffect(target).run(duration);
  }
  
  public Effect<?> asEffect(UIWidget target){
    return new FadeEffect(this, target);
  }
}