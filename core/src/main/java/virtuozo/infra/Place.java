package virtuozo.infra;


public interface Place {
  String token();
  
  Presenter<?> presenter();
}