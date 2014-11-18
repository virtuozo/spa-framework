package hitz.virtuozo.ui;

import hitz.virtuozo.infra.Keyboard;
import hitz.virtuozo.infra.api.Converter;
import hitz.virtuozo.ui.Menu.MenuItem;
import hitz.virtuozo.ui.MoveDownEvent.MoveDownHandler;
import hitz.virtuozo.ui.MoveUpEvent.MoveUpHandler;
import hitz.virtuozo.ui.SelectionEvent.SelectionHandler;
import hitz.virtuozo.ui.api.ShowEvent;
import hitz.virtuozo.ui.api.UIInput;
import hitz.virtuozo.ui.api.UIRenderer;
import hitz.virtuozo.ui.api.UIComponent;

import java.util.List;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Timer;

@SuppressWarnings("unchecked")
public abstract class TypeAhead<T extends TypeAhead<T, V>, V> extends Component<T> implements UIInput<T, V> {

  private Menu menu = new Menu();

  private InputText input = new InputText();

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

  private UIRenderer<V> renderer = new UIRenderer<V>() {
    @Override
    public UIComponent render(V value) {
      String text = TypeAhead.this.converter.convert(value);
      return TypeAhead.this.menu.addItem().text(text);
    }
  };

  private SelectionTimer selectionCommand = new SelectionTimer();

  public TypeAhead() {
    super(Elements.div());
    this.init();
  }

  private void init() {
    this.style().position(Position.RELATIVE);
    this.addChild(this.input).addChild(this.menu.hide());

    this.input().onKeyUp(new Handler() {

      @Override
      void onKeyUp(int keyCode, char charCode) {
        String value = TypeAhead.this.input().value();
        if (value.length() >= TypeAhead.this.minLength) {
          TypeAhead.this.load(value);
        }

        if (value.length() < TypeAhead.this.minLength || TypeAhead.this.entries == null || TypeAhead.this.entries.isEmpty()) {
          TypeAhead.this.menu.close();
          TypeAhead.this.entries = null;
        }
      }
    });

    this.input().onBlur(new BlurHandler() {

      @Override
      public void onBlur(BlurEvent event) {
        if (TypeAhead.this.menu.visible() && !TypeAhead.this.menu.hover()) {
          TypeAhead.this.menu.close();
        }
      }
    });
  }
  
  public T converter(Converter<V, String> converter) {
    this.converter = converter;
    return (T) this;
  }
  
  public T renderer(UIRenderer<V> renderer) {
    this.renderer = renderer;
    return (T) this;
  }

  public T onMoveUp(MoveUpHandler handler) {
    return this.addHandler(MoveUpEvent.type(), handler);
  }

  public T onMoveDown(MoveDownHandler handler) {
    return this.addHandler(MoveDownEvent.type(), handler);
  }

  public T onSelect(SelectionHandler<T> handler) {
    this.addHandler(SelectionEvent.type(), handler);
    return (T) this;
  }

  public T provider(ContentProvider<V> provider) {
    this.provider = provider;
    return (T) this;
  }

  public InputText input() {
    return this.input;
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
    this.input().blur();
    return this.select(this.entries.get(index), fireEvent);
  }

  public T select(V entry) {
    return this.select(entry, true);
  }

  public T select(V entry, boolean fireEvent) {
    this.selection = entry;
    this.input().value(this.converter.convert(entry));

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

    this.menu.open();
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

    int idx = direction > 0 ? 0 : this.menu.childrenCount() - 1;
    MenuItem target = this.childAt(idx);
    boolean bound = false;

    do {
      MenuItem item = this.menu.childAt(idx);
      idx += direction;
      bound = idx >= 0 && idx < this.childrenCount();

      if (item.active()) {
        item.deactivate();
        if (bound) {
          MenuItem focus = this.menu.childAt(idx);
          focus.activate();
          return;
        }
        break;
      }
    } while (bound);

    target.activate();
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
      int keyCode = event.getNativeEvent().getKeyCode();

      if (KeyCodes.KEY_ESCAPE == keyCode) {
        TypeAhead.this.menu.close();
        return;
      }

      if (KeyCodes.KEY_UP == keyCode) {
        TypeAhead.this.up();
        return;
      }

      if (KeyCodes.KEY_DOWN == keyCode) {
        TypeAhead.this.down();
        return;
      }

      if (Keyboard.get().controlKey(keyCode) && keyCode != KeyCodes.KEY_BACKSPACE) {
        return;
      }

      this.onKeyUp(keyCode, (char) event.getNativeEvent().getCharCode());
    }

    void onKeyUp(int keyCode, char charCode) {
    }
  }
}