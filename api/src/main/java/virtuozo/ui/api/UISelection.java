package virtuozo.ui.api;


public interface UISelection<C extends UIComponent, V> extends UIInput<C, V>, HasChangeHandlers<C> {

  C checked(Boolean checked);

  Boolean checked();
}
