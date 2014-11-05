package hitz.virtuozo.ui;

import hitz.virtuozo.infra.HashObject;

public abstract class GridFilter<H extends HashObject> {

  private boolean active;

  public GridFilter<H> activate() {
    this.active = true;
    return this;
  }

  public GridFilter<H> deactivate() {
    this.active = false;
    return this;
  }

  public boolean isActive() {
    return active;
  }

  public abstract boolean filter(H row);
}