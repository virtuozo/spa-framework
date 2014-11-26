package hitz.virtuozo.ui;

import hitz.virtuozo.infra.BrowserEventInterceptor;
import hitz.virtuozo.infra.Keyboard;
import hitz.virtuozo.infra.api.Converter;
import hitz.virtuozo.ui.SelectionEvent.SelectionHandler;
import hitz.virtuozo.ui.api.CssChangeEvent;
import hitz.virtuozo.ui.api.CssChangeHandler;
import hitz.virtuozo.ui.api.EventInterceptor;
import hitz.virtuozo.ui.api.HasClickHandlers;
import hitz.virtuozo.ui.api.HasFocusHandlers;
import hitz.virtuozo.ui.api.HasMouseHandlers;
import hitz.virtuozo.ui.api.HasText;
import hitz.virtuozo.ui.api.HasValue;
import hitz.virtuozo.ui.api.UIInput;
import hitz.virtuozo.ui.api.UIRenderer;
import hitz.virtuozo.ui.api.UIComponent;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Offset;

@SuppressWarnings("unchecked")
public abstract class SingleSelect<S extends SingleSelect<S, E>, E> extends Component<S> implements UIInput<S, E>, HasFocusHandlers<S> {

  private Tag<DivElement> mask = Tag.asDiv().css("select-drop-mask");

  private Container container = new Container();

  private DropDown menu = new DropDown();

  private E selection;

  private UIRenderer<E> renderer;

  private List<E> items = new ArrayList<E>();

  private Converter<E, String> converter = new Converter<E, String>() {
    public String convert(E value) {
      return value.toString();
    };
  };

  public SingleSelect() {
    super(Elements.div());
    this.init();
  }

  private void init() {
    this.addChild(this.container).addChild(this.mask).addChild(this.menu);
    this.onCssChange(new CssChangeHandler() {
      @Override
      public void onChange(CssChangeEvent e) {
        String name = "form-control";
        if (SingleSelect.this.css().contains(name)) {
          SingleSelect.this.css().remove(name);
        }
      }
    });
  }

  public S converter(Converter<E, String> converter) {
    this.converter = converter;
    return (S) this;
  }

  public S renderer(UIRenderer<E> renderer) {
    this.renderer = renderer;
    return (S) this;
  }

  public S focus() {
    this.container.focus();
    return (S) this;
  }

  public S blur() {
    this.container.blur();
    return (S) this;
  }

  @Override
  public S onFocus(FocusHandler handler) {
    this.container.onFocus(handler);
    return (S) this;
  }

  @Override
  public S onBlur(BlurHandler handler) {
    this.container.onBlur(handler);
    return (S) this;
  }

  public S onSelection(SelectionHandler<E> handler) {
    this.menu.onSelection(handler);
    return (S) this;
  }

  public S open() {
    this.menu.show();
    return (S) this;
  }

  public S close() {
    this.menu.hide();
    return (S) this;
  }

  public S resetable() {
    this.container.clear.show();
    return (S) this;
  }

  public S minimumInputLength(int length) {
    // this.options.put("minimumInputLength", length);
    return (S) this;
  }

  public S maximumInputLength(int length) {
    this.menu.search.input.maxLength(length);
    return (S) this;
  }

  public S searchable(Matcher<E> matcher) {
    this.menu.search.matcher(matcher).show();
    return (S) this;
  }

  public E getSelection() {
    return selection;
  }

  void select(E value) {
    this.selection = value;
    this.container.selectionHolder.text(this.converter.convert(value));
    this.menu.select(value);
  }

  public S select(int index) {
    this.select(this.menu.items.child(index).value());
    return (S) this;
  }

  public S item(E entry) {
    this.items.add(entry);
    this.menu.items.item(entry);
    return (S) this;
  }

  public S items(Iterable<E> entries) {
    for (E entry : entries) {
      this.item(entry);
    }

    this.select(0);

    return (S) this;
  }

  public E value() {
    return this.selection;
  }

  @Override
  public S value(E value) {
    this.select(value);
    return (S) this;
  }

  public S placeholder(String placeholder) {
    this.menu.items.placeholder(placeholder);
    return this.select(0);
  }

  String placeholder() {
    return this.menu.items.child(0).text();
  }

  @Override
  public S clear() {
    SingleSelect.this.select(0);
    return (S) this;
  }

  @Override
  public S enable() {
    this.container.enable();
    return (S) this;
  }

  @Override
  public S disable() {
    this.container.disable();
    return (S) this;
  }

  @Override
  public boolean disabled() {
    return this.container.disabled();
  }

  class Container extends Component<Container> implements HasFocusHandlers<Container>, HasText<Container> {

    private Tag<AnchorElement> choice = Tag.asAnchor("javascript:void(0)").css("select-choice");

    private Tag<SpanElement> selectionHolder = Tag.asSpan().css("select-chosen");

    private Tag<Element> clear = Tag.as(Elements.abbr()).css("select-search-choice-close").hide();

    private Tag<DivElement> arrow = Tag.asDiv().add(Tag.as(Elements.create("b")));

    private KeyUpHandler escHandler = new KeyUpHandler() {

      @Override
      public void onKeyUp(KeyUpEvent event) {
        if (KeyCodes.KEY_ESCAPE == event.getNativeEvent().getKeyCode()) {
          SingleSelect.this.close();
        }
      }
    };

    private CloseHandler closeHandler = new CloseHandler();

    public Container() {
      super(Elements.div());
      this.setup();
    }

    private void setup() {
      this.css("select-container").style().width(100, Unit.PCT);

      this.choice.onEvent(new EventInterceptor() {
        
        @Override
        public boolean shouldFire(Event event) {
          return !SingleSelect.this.disabled();
        }
      }).onFocus(new FocusHandler() {

        @Override
        public void onFocus(FocusEvent event) {
          Container.this.activate();
        }
      }).onBlur(new BlurHandler() {

        @Override
        public void onBlur(BlurEvent event) {
          Container.this.deactivate();
        }
      }).onClick(new ClickHandler() {

        @Override
        public void onClick(ClickEvent event) {
          Container.this.focus();
          SingleSelect.this.menu.toggleVisibility();
        }
      });

      this.clear.onClick(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          SingleSelect.this.clear();
          SingleSelect.this.menu.toggleVisibility();
        }
      });

      BrowserEventInterceptor.get().onKeyUp(this.escHandler).onClick(this.closeHandler);

      this.choice.add(this.selectionHolder).add(this.clear).add(this.arrow);
      this.addChild(this.choice);
    }

    @Override
    public Container detach() {
      BrowserEventInterceptor.get().remove(KeyUpEvent.getType(), this.escHandler).remove(ClickEvent.getType(), this.closeHandler);
      return super.detach();
    }

    @Override
    public Container onBlur(BlurHandler handler) {
      this.choice.onBlur(handler);
      return this;
    }

    @Override
    public Container onFocus(FocusHandler handler) {
      this.choice.onFocus(handler);
      return this;
    }

    public Container blur() {
      this.deactivate();
      this.element().blur();
      return this;
    }

    public Container focus() {
      this.activate();
      this.element().focus();
      return this;
    }

    @Override
    public Container text(String text) {
      this.selectionHolder.text(text);
      return this;
    }

    @Override
    public String text() {
      return this.selectionHolder.text();
    }

    private void activate() {
      this.css(ActivationClass.ACTIVE);
    }

    private void enable() {
      this.css().remove(ActivationClass.DISABLED);
    }

    private void disable() {
      this.css(ActivationClass.DISABLED);
    }

    private boolean disabled() {
      return this.css().contains(ActivationClass.DISABLED);
    }

    private void deactivate() {
      this.css().remove(ActivationClass.ACTIVE);
    }
  }

  static class ActivationClass extends CssClass {
    private ActivationClass(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }

    static final ActivationClass ACTIVE = new ActivationClass("select-container-active");
    static final ActivationClass DISABLED = new ActivationClass("select-container-disabled");
    static final StyleChooser STYLES = new StyleChooser(ACTIVE, DISABLED);
  }

  class DropDown extends Component<DropDown> {

    private Search search = new Search().hide();

    private Items items = new Items();

    public DropDown() {
      super(Elements.div());
      this.css("select-drop").addChild(this.search).addChild(this.items).hide();
    }

    public DropDown onSelection(SelectionHandler<E> handler) {
      return this.addHandler(SelectionEvent.type(), handler);
    }

    public DropDown activate() {
      return this.css("select-drop-active");
    }

    public DropDown deactivate() {
      this.css().remove("select-drop-active");
      return this;
    }

    public DropDown show() {
      super.show();

      int parentTop = SingleSelect.this.parent().asComponent().top();
      Offset offset = SingleSelect.this.container.choice.offset();
      int height = SingleSelect.this.container.choice.offsetHeight();

      int top = (offset.top() - parentTop) + height;
      this.style().width(SingleSelect.this.container.outerWidth(), Unit.PX).top(top, Unit.PX);

      SingleSelect.this.container.css("select-dropdown-open").deactivate();
      this.search.input.focus();
      return this;
    }

    public DropDown hide() {
      SingleSelect.this.container.css().remove("select-dropdown-open");

      return super.hide();
    }

    void select(E value) {
      this.fireEvent(new SelectionEvent<E>(value));
      this.search.input.clear();
      for (UIComponent child : this.items.childrenComponents()) {
        child.asComponent().show();
      }
      Item first = this.items.childAt(0);
      if (first.css().contains("select-placeholder")) {
        first.hide();
      }
      // placeholder fix
      this.hide();
    }

    class Search extends Component<Search> {

      private InputText input = new InputText().css("select-input");

      private Matcher<E> matcher;

      public Search() {
        super(Elements.div());
        this.css("select-search").addChild(this.input);
        this.init();
      }

      private void init() {
        this.input.onKeyUp(new KeyUpHandler() {
          @Override
          public void onKeyUp(KeyUpEvent event) {
            handle(event);
          }
        }).onFocus(new FocusHandler() {
          @Override
          public void onFocus(FocusEvent event) {
            SingleSelect.this.container.closeHandler.disable();
          }
        }).onBlur(new BlurHandler() {
          @Override
          public void onBlur(BlurEvent event) {
            SingleSelect.this.container.closeHandler.enable();
          }
        });
      }

      public Search matcher(Matcher<E> matcher) {
        this.matcher = matcher;
        return this;
      }

      private void handle(KeyUpEvent event) {
        if (Keyboard.get().nonInputKey(event) || this.matcher == null) {
          return;
        }

        Items items = SingleSelect.this.menu.items;

        if (Keyboard.get().enter(event) && this.handleSelection()) {
          return;
        }

        for (E entry : SingleSelect.this.items) {
          boolean match = this.matcher.matches(this.input.value(), entry);
          Item item = items.find(entry);
          if (item == null) {
            continue;
          }
          item.show();
          if (!match) {
            item.hide();
          }
        }
      }

      private boolean handleSelection() {
        Items items = SingleSelect.this.menu.items;
        if (this.input.value().isEmpty()) {
          return false;
        }

        Item first = items.find(Clauses.VISIBLE);
        if (first != null) {
          SingleSelect.this.select(first.value);
          return true;
        }

        return false;
      }
    }

    class Items extends Component<Items> {

      private Item placeholder = new Item().css("select-placeholder").hide();

      public Items() {
        super(Elements.ul());
        this.init();
      }

      private void init() {
        this.css("select-results").addChild(placeholder);
      }

      public Items placeholder(String text) {
        this.placeholder.value(text).hide();

        if (this.hasChildren()) {
          return this.insertChild(placeholder, this.child(0));
        }

        return this;
      }

      public Item find(E entry) {
        for (UIComponent child : this.childrenComponents()) {
          Item item = (Item) child;
          if (item.value().equals(entry)) {
            return item;
          }
        }

        return null;
      }

      public Item item(E entry) {
        Item item = new Item().value(entry);
        this.addChild(item);
        return item;
      }

      Item child(int index) {
        return (Item) this.childAt(index);
      }
    }

    class Item extends Component<Item> implements HasClickHandlers<Item>, HasMouseHandlers<Item>, HasValue<Item, E> {

      private Tag<DivElement> label = Tag.asDiv().css("select-result-label");

      private E value;

      public Item() {
        super(Elements.li());
        this.init();
      }

      private void init() {
        this.css("select-results-dept-0 select-result select-result-selectable");
        this.addChild(this.label);

        this.onClick(new ClickHandler() {

          @Override
          public void onClick(ClickEvent event) {
            SingleSelect.this.select(Item.this.value);
          }
        }).onMouseOver(new MouseOverHandler() {

          @Override
          public void onMouseOver(MouseOverEvent event) {
            Item.this.css("select-highlighted");
          }
        }).onMouseOut(new MouseOutHandler() {

          @Override
          public void onMouseOut(MouseOutEvent event) {
            Item.this.css().remove("select-highlighted");
          }
        });
      }

      @Override
      public Item onClick(ClickHandler handler) {
        return this.on(handler);
      }

      @Override
      public Item onDoubleClick(DoubleClickHandler handler) {
        return this.on(handler);
      }

      @Override
      public Item onMouseDown(MouseDownHandler handler) {
        return this.on(handler);
      }

      @Override
      public Item onMouseMove(MouseMoveHandler handler) {
        return this.on(handler);
      }

      @Override
      public Item onMouseOut(MouseOutHandler handler) {
        return this.on(handler);
      }

      @Override
      public Item onMouseOver(MouseOverHandler handler) {
        return this.on(handler);
      }

      @Override
      public Item onMouseUp(MouseUpHandler handler) {
        return this.on(handler);
      }

      @Override
      public Item onMouseWheel(MouseWheelHandler handler) {
        return this.on(handler);
      }

      @Override
      public Item value(E value) {
        this.value = value;

        if (value == null) {
          return this;
        }

        if (SingleSelect.this.renderer != null) {
          this.label.add(SingleSelect.this.renderer.render(value));
          return this;
        }

        this.label.text(SingleSelect.this.converter.convert(value));

        return this;
      }

      Item value(String value) {
        this.label.text(value);
        return this;
      }

      String text() {
        return this.label.text();
      }

      @Override
      public E value() {
        return this.value;
      }
    }
  }

  public class CloseHandler implements ClickHandler {
    private boolean enable = true;

    @Override
    public void onClick(ClickEvent event) {
      if (this.enable) {
        SingleSelect.this.close();
      }
    }

    public CloseHandler enable() {
      this.enable = true;
      return this;
    }

    public CloseHandler disable() {
      this.enable = false;
      return this;
    }
  }

  public static interface Matcher<E> {
    public boolean matches(String search, E value);
  }
}