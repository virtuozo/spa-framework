package virtuozo.ui.interfaces;



public interface UICheck<C extends UIComponent, V> extends UIInput<C, V>, HasChangeHandlers<C> {

  C check();
  
  C uncheck();

  Boolean checked();
}
