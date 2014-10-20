package hitz.virtuozo.application;

import hitz.virtuozo.ui.HTML;

import com.google.gwt.core.client.EntryPoint;

public abstract class EntryPointBase implements EntryPoint {

  @Override
  public void onModuleLoad() {
    Place place = this.start();
    Transition.get().target(HTML.body()).go(place);
  }

  protected abstract Place start();
}