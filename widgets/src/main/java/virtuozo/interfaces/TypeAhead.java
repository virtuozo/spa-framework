package virtuozo.interfaces;

import java.util.List;

import virtuozo.infra.Converter;
import virtuozo.infra.Elements;
import virtuozo.infra.Keyboard;
import virtuozo.infra.Logger;
import virtuozo.infra.events.MoveDownEvent;
import virtuozo.infra.events.MoveUpEvent;
import virtuozo.infra.events.SelectionEvent;
import virtuozo.infra.events.ShowEvent;
import virtuozo.infra.events.MoveDownEvent.MoveDownHandler;
import virtuozo.infra.events.MoveUpEvent.MoveUpHandler;
import virtuozo.infra.events.SelectionEvent.SelectionHandler;
import virtuozo.interfaces.Component;
import virtuozo.interfaces.HasActivation;
import virtuozo.interfaces.InputText;
import virtuozo.interfaces.Menu;
import virtuozo.interfaces.UIClass;
import virtuozo.interfaces.UIClasses;
import virtuozo.interfaces.UIInput;
import virtuozo.interfaces.UIRenderer;
import virtuozo.interfaces.Menu.MenuItem;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Timer;

@SuppressWarnings("unchecked")
public abstract class TypeAhead<T extends TypeAhead<T, V>, V> extends Component<T> implements UIInput<T, V> {

  private Menu menu = Menu.create();

  private InputText control = InputText.create().css("form-control");

  private List<V> entries;

  private V selection;

  private int numberOfItems = 10;

  private int minLength = 3;

  private ContentProvider<V> provider;

  private Converter<V, String> converter = new Converter<V, String>() {
    @Override
    public String convert(V value) {
      return value.toString();
    }
  };

  private UIRenderer<V> renderer;

  private SelectionTimer selectionCommand = new SelectionTimer();

  protected TypeAhead() {
    super(Elements.div());
    this.init();
  }

  private void init() {
    super.css("autocomplete");
    this.style().position(Position.RELATIVE);
    this.addChild(this.control).addChild(this.menu.hide());

    this.control().onKeyUp(new Handler() {
      
      @Override
      protected void onKeyUp() {
        String value = TypeAhead.this.control().value();
        if (value.length() >= TypeAhead.this.minLength) {
          TypeAhead.this.load(value);
        }

        if (value.length() < TypeAhead.this.minLength || TypeAhead.this.entries == null || TypeAhead.this.entries.isEmpty()) {
          TypeAhead.this.menu.close();
          TypeAhead.this.entries = null;
        }
      }
    });

    this.control().onBlur(new BlurHandler() {

      @Override
      public void onBlur(BlurEvent event) {
        if (TypeAhead.this.menu.visible() && !TypeAhead.this.menu.hover()) {
          TypeAhead.this.menu.close();
        }
      }
    });
  }
  
  @Override
  public UIClasses css() {
    return this.control.css();
  }
  
  @Override
  public T css(String... classes) {
    this.control.css(classes);
    return (T) this;
  }
  
  @Override
  public T css(UIClass... classes) {
    this.control.css(classes);
    return (T) this;
  }
  
  public T converter(Converter<V, String> converter) {
    this.converter = converter;
    return (T) this;
  }
  
  protected T renderer(UIRenderer<V> renderer) {
    this.renderer = renderer;
    return (T) this;
  }
  
  public T onChange(ChangeHandler handler){
    this.control.onChange(handler);
    return (T) this;
  }

  public T onMoveUp(MoveUpHandler handler) {
    return this.addHandler(MoveUpEvent.TYPE, handler);
  }

  public T onMoveDown(MoveDownHandler handler) {
    return this.addHandler(MoveDownEvent.TYPE, handler);
  }

  public T onSelect(SelectionHandler<T> handler) {
    this.addHandler(SelectionEvent.TYPE, handler);
    return (T) this;
  }

  public T provider(ContentProvider<V> provider) {
    this.provider = provider;
    return (T) this;
  }
  
  public T placeholder(String placeholder){
    this.control.placeholder(placeholder);
    return (T) this;
  }

  public InputText control() {
    return this.control;
  }

  public T visibleItems(int numberOfItems) {
    this.numberOfItems = numberOfItems;
    return (T) this;
  }

  public T triggerAutoComplete(int minLength) {
    this.minLength = minLength;
    return (T) this;
  }

  public T select(int index) {
    return this.select(index, true);
  }

  public T select(int index, boolean fireEvent) {
    this.control().blur();
    return this.select(this.entries.get(index), fireEvent);
  }

  public T select(V entry) {
    return this.select(entry, true);
  }

  public T select(V entry, boolean fireEvent) {
    this.selection = entry;
    this.control().value(this.converter.convert(entry));

    if (fireEvent) {
      this.fireEvent(new SelectionEvent<V>(entry));
    }
    this.menu.close();

    return (T) this;
  }

  public boolean hasSuggestions() {
    return this.menu.visible();
  }

  public V selection() {
    return this.selection;
  }

  public int size() {
    int size = 0;
    if (this.entries != null) {
      size = this.entries.size();
    }

    return size;
  }
  
  @Override
  public T enable() {
    this.control.enable();
    return (T) this;
  }

  @Override
  public T disable() {
    this.control.disable();
    return (T) this;
  }

  @Override
  public boolean disabled() {
    return this.control.disabled();
  }
  
  @Override
  public T clear() {
    this.control.clear();
    return (T) this;
  }
  
  @Override
  public T tabIndex(int index) {
    this.control.tabIndex(index);
    return (T) this;
  }
  
  void load(String value) {
    this.entries = this.provider.provideContent(value, this.numberOfItems);
    this.menu.detachChildren();

    for (final V entry : this.entries) {
      MenuItem item = this.menu.addItem().onClick(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          TypeAhead.this.select(entry);
        }
      }).on(new Handler());

      if (this.renderer != null) {
        item.addChild(this.renderer.render(entry));
        continue;
      }

      item.text(this.converter.convert(entry));
    }

    this.menu.open().style().display(Display.BLOCK);
    this.selectionCommand.schedule(10);
  }

  void up() {
    this.moveTo(-1);
    this.fireEvent(new MoveUpEvent());
  }

  void down() {
    this.moveTo(1);
    this.fireEvent(new MoveDownEvent());
  }

  void moveTo(int direction) {
    if (!this.hasChildren() || this.menu.hover()) {
      return;
    }

    int index = 0;
    if(direction < 0 ) { 
      index = this.menu.childrenCount() - 1;
    }
    
    MenuItem activated = this.menu.find(HasActivation.Clauses.ACTIVE);
    
    if(activated != null){
      index = this.menu.indexOfChild(activated);
      Logger.get().error("idx" + index);
    }
    
    boolean bound = false;
    MenuItem item = this.menu.childAt(index);
    index += direction;
    bound = index >= 0 && index < this.menu.childrenCount();

    Logger.get().error(item.text());
    if (item.active()) {
      item.deactivate();
      if (bound) {
        MenuItem focus = this.menu.childAt(index);
        focus.activate();
        return;
      }
    }
    
    item.activate();
  }

  public static interface ContentProvider<T> {
    List<T> provideContent(String value, int numberOfItems);
  }

  class SelectionTimer extends Timer {

    @Override
    public void run() {
      TypeAhead.this.fireEvent(new ShowEvent());
    }
  }

  class Handler implements KeyUpHandler {

    @Override
    public final void onKeyUp(KeyUpEvent event) {

      if (Keyboard.get().escape(event)) {
        TypeAhead.this.menu.close();
        return;
      }
      
      if (Keyboard.get().enter(event)) {
        MenuItem item = TypeAhead.this.menu.find(HasActivation.Clauses.ACTIVE);
        TypeAhead.this.select(TypeAhead.this.menu.indexOfChild(item));
        return;
      }

      if (Keyboard.get().up(event)) {
        TypeAhead.this.up();
        return;
      }

      if (Keyboard.get().down(event)) {
        TypeAhead.this.down();
        return;
      }

      if (Keyboard.get().nonInputKey(event) && Keyboard.get().erase(event)) {
        return;
      }
      
      this.onKeyUp();
    }
    
    protected void onKeyUp(){
      return;
    }
  }
}