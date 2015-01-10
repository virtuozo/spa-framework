package virtuozo.ui.interfaces;



public interface UISelection<C extends UIComponent, V> extends UIInput<C, V>, HasChangeHandlers<C> {

  C check();
  
  C uncheck();

  Boolean checked();
}
