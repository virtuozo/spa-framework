package virtuozo.ui;

import virtuozo.infra.Calendar;
import virtuozo.infra.DateFormat;
import virtuozo.ui.events.CssChangeEvent;
import virtuozo.ui.events.CssChangeEvent.CssChangeHandler;
import virtuozo.ui.interfaces.HasChangeHandlers;
import virtuozo.ui.interfaces.HasFocusHandlers;
import virtuozo.ui.interfaces.Icon;
import virtuozo.ui.interfaces.UIInput;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;

public final class Datepicker extends Component<Datepicker> implements UIInput<Datepicker, Calendar>, HasChangeHandlers<Datepicker>, HasFocusHandlers<Datepicker> {

  private InputGroup input;

  private MonthPanel panel;

  private Tag<DivElement> picker = Tag.asDiv().css("datepicker");

  private DateFormat format;
  
  private Button button = Button.create();

  public static Datepicker create(){
    return new Datepicker(DateFormat.DATE_SHORT);
  }
  
  public static Datepicker create(DateFormat format){
    return new Datepicker(format);
  }
  
  private Datepicker(DateFormat format) {
    super(Elements.div());
    this.css("datepicker-input").init(format);
  }

  private void init(DateFormat format) {
    this.style().position(com.google.gwt.dom.client.Style.Position.RELATIVE);
    this.format = format;

    InputText control = InputText.create();
    DateInputPrevent.create().attachTo(control);
    this.input = new InputGroup(control);
    
    this.addChild(this.input);
    
    this.onCssChange(new CssChangeHandler() {
      @Override
      public void onChange(CssChangeEvent e) {
        String name = "form-control";
        if (Datepicker.this.css().contains(name)) {
          Datepicker.this.css().remove(name);
        }
      }
    });

    this.input.onFocus(new FocusHandler() {

      @Override
      public void onFocus(FocusEvent event) {
        Datepicker.this.show();
      }
    });
    this.input.onBlur(new BlurHandler() {

      @Override
      public void onBlur(BlurEvent event) {
        if (Datepicker.this.panel.selected()) {
          return;
        }

        Datepicker.this.hide();
      }
    });

    this.panel = MonthPanel.create();
    this.panel.onChange(new ChangeHandler() {

      @Override
      public void onChange(ChangeEvent e) {
        Datepicker.this.value(panel.current());
        Datepicker.this.hide();
      }
    }).on(new MouseOutHandler() {
      
      @Override
      public void onMouseOut(MouseOutEvent event) {
        Datepicker.this.input.blur();
      }
    }).onNext(this.doFocus).onPrevious(this.doFocus);

    this.addChild(this.picker.add(this.panel)).hide();
  }
  
  public Datepicker icon(Icon icon) {
    this.button.icon(icon).onClick(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        Datepicker.this.input.focus();
      }
    });

    this.input.append(this.button);

    return this;
  }

  public Datepicker append(Button button) {
    this.input.append(button);
    return this;
  }

  public Datepicker prepend(Button button) {
    this.input.prepend(button);
    return this;
  }

  public UIInput<?, String> input() {
    return this.input;
  }

  @Override
  public Datepicker onChange(ChangeHandler handler) {
    this.panel.onChange(handler);
    return this;
  }

  @Override
  public Datepicker onFocus(FocusHandler handler) {
    this.input.onFocus(handler);
    return this;
  }

  @Override
  public Datepicker onBlur(BlurHandler handler) {
    this.input.onBlur(handler);
    return this;
  }

  public Datepicker range(Calendar start, Calendar end) {
    this.panel.range(start, end);

    if (start.after(this.panel.current())) {
      this.value(start);
    }

    return this;
  }
  
  public Datepicker show() {
    if (this.disabled()) {
      return this;
    }

    this.picker.show();
    return this.positioning();
  }

  public Datepicker hide() {
    this.picker.hide();
    this.panel.selected(false);

    return this;
  }

  @Override
  public Datepicker clear() {
    this.input.clear();
    return this;
  }

  @Override
  public Datepicker enable() {
    this.input.enable();
    this.button.enable();
    return this;
  }

  @Override
  public Datepicker disable() {
    this.input.disable();
    this.button.disable();
    return this;
  }

  @Override
  public boolean disabled() {
    return this.input.disabled();
  }

  @Override
  public Datepicker value(Calendar value) {
    if (value == null) {
      this.input.value(null);
      return this;
    }

    this.panel.set(value);
    this.input.value(this.format.format(value.toDate()));

    return this;
  }

  @Override
  public Calendar value() {
    if (this.input.value().isEmpty()) {
      return null;
    }

    return Calendar.of(this.format.unformat(this.input.value()));
  }

  public Datepicker placeholder(String placeholder) {
    this.input.placeholder(placeholder);
    return this;
  }
  
  @Override
  public Datepicker tabIndex(int index) {
    this.input.tabIndex(index);
    return this;
  }

  private ClickHandler doFocus = new ClickHandler() {

    @Override
    public void onClick(ClickEvent event) {
      Datepicker.this.focus();
    }
  };

  Datepicker positioning() {
    this.picker.style().zIndex(10000).position(Position.ABSOLUTE).top(this.input.offsetHeight(), Unit.PX).left(0, Unit.PX);
    return this;
  }
}