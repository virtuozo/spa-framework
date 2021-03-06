package virtuozo.interfaces;

import java.util.ArrayList;
import java.util.List;

import virtuozo.infra.BrowserEventInterceptor;
import virtuozo.infra.Clauses;
import virtuozo.infra.Converter;
import virtuozo.infra.Elements;
import virtuozo.infra.EventInterceptor;
import virtuozo.infra.Keyboard;
import virtuozo.infra.StyleChooser;
import virtuozo.infra.events.CssChangeEvent;
import virtuozo.infra.events.CssChangeEvent.CssChangeHandler;
import virtuozo.infra.handlers.HasClickHandlers;
import virtuozo.infra.handlers.HasFocusHandlers;
import virtuozo.infra.handlers.HasMouseHandlers;
import virtuozo.interfaces.Anchor;
import virtuozo.interfaces.Component;
import virtuozo.interfaces.CssClass;
import virtuozo.interfaces.HasText;
import virtuozo.interfaces.HasValue;
import virtuozo.interfaces.InputText;
import virtuozo.interfaces.Tag;
import virtuozo.interfaces.UIComponent;
import virtuozo.interfaces.UIInput;
import virtuozo.interfaces.UIRenderer;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
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
  
  protected SingleSelect() {
    super(Elements.div());
    this.init();
  }

  private void init() {
    this.css("single-select").addChild(this.container).addChild(this.mask).addChild(this.menu);
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

  protected S converter(Converter<E, String> converter) {
    this.converter = converter;
    return (S) this;
  }

  protected S renderer(UIRenderer<E> renderer) {
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

  public S onChange(ChangeHandler handler) {
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
    this.onChange(new ChangeHandler() {
      @Override
      public void onChange(ChangeEvent event) {
        SingleSelect.this.container.clear.show();
      }
    });
    return (S) this;
  }

  public Searchable searchable(Matcher<E> matcher) {
    this.menu.search.matcher(matcher).show();
    return new Searchable();
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
  
  public S add(E entry) {
    this.items.add(entry);
    this.menu.items.item(entry);
    return (S) this;
  }

  public S add(Iterable<E> entries) {
    for (E entry : entries) {
      this.add(entry);
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
    this.select(0);
    this.container.clear.hide();
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
  
  @Override
  public S tabIndex(int index) {
    this.element().setTabIndex(index);
    return (S) this;
  }

  class Container extends Component<Container> implements HasFocusHandlers<Container>, HasText<Container> {

    private Anchor choice = Anchor.create().css("select-choice");

    private Tag<SpanElement> selectionHolder = Tag.asSpan().css("select-chosen");

    private Tag<Element> clear = Tag.as(Elements.abbr()).css("select-search-choice-close").hide();

    private Tag<DivElement> arrow = Tag.asDiv().add(Tag.as(Elements.create("b")));

    private KeyUpHandler escHandler = new KeyUpHandler() {

      @Override
      public void onKeyUp(KeyUpEvent event) {
        if (Keyboard.get().escape(event)) {
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
      this.css("select-container");

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

    public DropDown onSelection(ChangeHandler handler) {
      return this.addHandler(ChangeEvent.getType(), handler);
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

      this.style().width(SingleSelect.this.container.measurement().outerWidth(), Unit.PX);

      SingleSelect.this.container.css("select-dropdown-open").deactivate();
      this.search.input.focus();
      return this;
    }

    public DropDown hide() {
      SingleSelect.this.container.css().remove("select-dropdown-open");

      return super.hide();
    }

    void select(E value) {
      this.fireNativeEvent(Document.get().createChangeEvent());
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

      private InputText input = InputText.create().css("select-input");

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
        if (this.matcher == null) {
          return;
        }

        if (Keyboard.get().enter(event) && this.handleSelection()) {
          return;
        }
        
        Items items = SingleSelect.this.menu.items;

        for(Item child : items.<Item>childrenComponents()){
          boolean match = this.matcher.matches(this.input.value(), child.value());
          child.show();
          if(!match){
            child.hide();
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
  
  public class Searchable {
    public Searchable maximumInputLength(int length) {
      SingleSelect.this.menu.search.input.maxLength(length);
      return this;
    }   
  }

  public static interface Matcher<E> {
    public boolean matches(String search, E value);
  }
}