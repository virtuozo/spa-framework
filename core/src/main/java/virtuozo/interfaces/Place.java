package virtuozo.interfaces;

import virtuozo.infra.Presenter;

public interface Place {
  String token();
  
  Presenter<?> presenter();
}