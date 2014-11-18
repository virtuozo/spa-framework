package hitz.virtuozo.application;

import hitz.virtuozo.ui.Bootstrap;

import com.google.gwt.inject.client.Ginjector;

public interface CoreInjector extends Ginjector {
  Bootstrap bootstrap();
}
