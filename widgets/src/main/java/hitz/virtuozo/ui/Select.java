package hitz.virtuozo.ui;

import hitz.virtuozo.infra.BrowserEventInterceptor;
import hitz.virtuozo.infra.api.Converter;
import hitz.virtuozo.infra.api.EventHandler;
import hitz.virtuozo.infra.api.EventInterceptor;
import hitz.virtuozo.infra.api.EventType;
import hitz.virtuozo.infra.api.HasClickHandlers;
import hitz.virtuozo.infra.api.HasFocusHandlers;
import hitz.virtuozo.infra.api.HasMouseHandlers;
import hitz.virtuozo.infra.api.HasText;
import hitz.virtuozo.infra.api.HasValue;
import hitz.virtuozo.ui.CssClass;
import hitz.virtuozo.ui.Elements;
import hitz.virtuozo.ui.Event;
import hitz.virtuozo.ui.InputText;
import hitz.virtuozo.ui.StyleChooser;
import hitz.virtuozo.ui.Tag;
import hitz.virtuozo.ui.Widget;
import hitz.virtuozo.ui.api.UIInput;
import hitz.virtuozo.ui.api.UIRenderer;

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

@SuppressWarnings("unchecked")
public abstract class Select<S extends Select<S, E>, E> extends Widget<S> implements UIInput<S, E>, HasFocusHandlers<S> {

  private Tag<DivElement> mask = Tag.asDiv().css("select-drop-mask");

  private Container container = new Container();
  
  private DropDown menu = new DropDown();

  private E selection;
  
  private UIRenderer<E> renderer;
  
  private Converter<E, String> converter = new Converter<E, String>() {
    public String convert(E value) {
      return value.toString();
    };
  };

  public Select() {
    super(Elements.div());
    this.init();
  }

  private void init() {
    this.addChild(this.container).addChild(this.mask).addChild(this.menu);
    this.onCssChange(new EventHandler<Void>() {
      @Override
      public void onEvent(Event<Void> e) {
        String name = "form-control";
        if(Select.this.css().contains(name)){
          Select.this.css().remove(name);
        }
      }
    });
  }
  
  public S converter(Converter<E, String> converter){
    this.converter = converter;
    return (S) this;
  }
  
  public S renderer(UIRenderer<E> renderer){
    this.renderer = renderer;
    return (S) this;
  }
  
  public S focus(){
    this.container.focus();
    return (S) this;
  }
  
  public S blur(){
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

  public S onSelection(EventHandler<E> handler) {
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
    this.container.clear.visible();
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

  //TODO search impl
  S searchable(Matcher matcher) {
    this.menu.search.show();
    return (S) this;
  }

  public E getSelection() {
    return selection;
  }

  void select(E value) {
    this.selection = value;
    this.container.placeholder.text(this.converter.convert(value));
    this.menu.select(value);
  }

  public S select(int index) {
    this.select(this.menu.results.child(index).value());
    return (S) this;
  }
  
  public S item(E entry){
    this.menu.results.item(entry);
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
    this.menu.results.placeholder(placeholder);
    return this.select(0);
  }
  
  String placeholder(){
    return this.menu.results.child(0).text();
  }

  @Override
  public S clear() {
    Select.this.select(0);
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
  
  class Container extends Widget<Container> implements HasFocusHandlers<Container>, HasText<Container> {

    private Tag<AnchorElement> choice = Tag.asAnchor("javascript:void(0)").css("select-choice");

    private Tag<SpanElement> placeholder = Tag.asSpan().css("select-chosen");

    private Tag<Element> clear = Tag.as(Elements.abbr()).css("select-search-choice-close").hide();

    private Tag<DivElement> arrow = Tag.asDiv().add(Tag.as(Elements.create("b")));

    private KeyUpHandler escHandler = new KeyUpHandler() {

      @Override
      public void onKeyUp(KeyUpEvent event) {
        if (KeyCodes.KEY_ESCAPE == event.getNativeEvent().getKeyCode()) {
          Select.this.close();
        }
      }
    };
    
    private ClickHandler closeHandler = new ClickHandler() {
      
      @Override
      public void onClick(ClickEvent event) {
        //if(!ComboBox.this.menu.isHover()){
          Select.this.close();
        //}
      }
    };
    
    public Container() {
      super(Elements.div());
      this.setup();
    }

    private void setup() {
      this.css("select-container").style().width(100, Unit.PCT);
      
      this.choice.onEvent(new EventInterceptor() {
        @Override
        public boolean proceed() {
          return !Select.this.disabled();
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
          Select.this.menu.toggleVisibility();
        }
      });
      
      this.clear.onClick(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          Select.this.clear();
          Select.this.menu.toggleVisibility();
        }
      });
      
      BrowserEventInterceptor.get().onKeyUp(this.escHandler).onClick(this.closeHandler);

      this.choice.add(this.placeholder).add(this.clear).add(this.arrow);
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
      this.placeholder.text(text);
      return this;
    }

    @Override
    public String text() {
      return this.placeholder.text();
    }

    private void activate() {
      this.css(ActivationClass.ACTIVE);
    }
    
    private void enable(){
      this.css().remove(ActivationClass.DISABLED);
    }

    private void disable() {
      this.css(ActivationClass.DISABLED);
    }
    
    private boolean disabled(){
      return this.css().contains(ActivationClass.DISABLED);
    }

    private void deactivate() {
      this.css().remove(ActivationClass.ACTIVE);
    }
  }
  
  static class ActivationClass extends CssClass{
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

  class DropDown extends Widget<DropDown> {

    private Search search = new Search().hide();

    private Items results = new Items();

    public DropDown() {
      super(Elements.div());
      this.css("select-drop").addChild(this.search).addChild(this.results).hide();
    }

    public DropDown onSelection(EventHandler<E> handler) {
      return this.addHandler(Select.FireableEvent.SELECT, handler);
    }

    public DropDown activate() {
      return this.css("select-drop-active");
    }

    public DropDown deactivate() {
      this.css().remove("select-drop-active");
      return this;
    }

    public DropDown show() {
      this.style().width(Select.this.container.outerWidth(), Unit.PX);
      Select.this.container.css("select-dropdown-open").deactivate();

      return super.show();
    }

    public DropDown hide() {
      Select.this.container.css().remove("select-dropdown-open");

      return super.hide();
    }

    void select(E value) {
      this.fireEvent(new Event<E>(Select.FireableEvent.SELECT, value));
      this.hide();
    }
    
    class Search extends Widget<Search> {

      private InputText input = new InputText().css("select-input");

      public Search() {
        super(Elements.div());
        this.css("select-search").addChild(this.input);
      }
    }

    class Items extends Widget<Items> {

      public Items() {
        super(Elements.ul());
        this.init();
      }

      private void init() {
        this.css("select-results");
      }

      public Items placeholder(String text) {
        Item placeholder = new Item().value(text).hide();
        
        if(this.hasChildren()){
          return this.insertChild(placeholder, this.child(0));
        }
        
        return this.addChild(placeholder);
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
    
    class Item extends Widget<Item> implements HasClickHandlers<Item>, HasMouseHandlers<Item>, HasValue<Item, E> {

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
            Select.this.select(Item.this.value);
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

        if(value == null){
          return this;
        }
        
        if (Select.this.renderer != null) {
          this.label.add(Select.this.renderer.render(value));
          return this;
        }
        
        this.label.text(Select.this.converter.convert(value));

        return this;
      }
      
      Item value(String value){
        this.label.text(value);
        return this;
      }
      
      String text(){
        return this.label.text();
      }

      @Override
      public E value() {
        return this.value;
      }
    }
  }
  
  public static interface Matcher {

  }

  enum FireableEvent implements EventType {
    SELECT
  }
}

//<div class="select-container select-dropdown-open select-container-active"
//id="s2id_gwt-uid-2" style="width: 100%;"> <a tabindex="-1" class="select-choice"
//onclick="return false;" href="javascript:void(0)"> <span>PlaceHolder</span> <abbr
//style="display:none;" class="select-search-choice-close"></abbr> </a> <input type="text"
//class="select-focusser select-offscreen" disabled="disabled"> </div> /Options HTML <div
//style="top: 30px; left: 0px; width: 1920px; display: block;"
//class="select-drop select-drop-active" id="select-drop"> <div
//class="select-search select-search-hidden"> <input type="text" class="select-input"
//autocomplete="off"> </div> <ul class="select-results"> <li
//class="select-results-dept-0 select-result select-result-selectable"> <div
//class="select-result-label"><span class="select-match"></span>Value 1</div> </li> <li
//class="select-results-dept-0 select-result select-result-selectable"> <div
//class="select-result-label"><span class="select-match"></span>Value 2</div> </li> <li
//class="select-results-dept-0 select-result select-result-selectable select-highlighted">
//<div class="select-result-label"><span class="select-match"></span>Value 3</div> </li> </ul>
//</div>