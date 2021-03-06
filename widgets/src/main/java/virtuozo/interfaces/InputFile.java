package virtuozo.interfaces;

import virtuozo.infra.Elements;
import virtuozo.infra.SimpleValidator;
import virtuozo.interfaces.Button;
import virtuozo.interfaces.Component;
import virtuozo.interfaces.Icon;
import virtuozo.interfaces.Input;
import virtuozo.interfaces.InputGroup;
import virtuozo.interfaces.InputText;
import virtuozo.interfaces.UIClass;
import virtuozo.interfaces.UIClasses;
import virtuozo.interfaces.UIInput;
import virtuozo.interfaces.css.ButtonColor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public final class InputFile extends Component<InputFile> implements UIInput<InputFile, String> {
  private Assets assets = GWT.create(Assets.class);
  
  private File file = new File();

  private InputGroup input = InputGroup.create(InputText.create());

  private RichButton submit = RichButton.create();

  private RichButton reset = RichButton.create().css(ButtonColor.DANGER).hide();

  public static InputFile create(){
    return new InputFile();
  }
  
  private InputFile() {
    super(Elements.div());
    this.reset.icon(this.assets.clearIcon());
    this.init();
  }

  private void init() {
    this.addChild(this.file).addChild(this.input);
    this.input.append(this.submit).append(this.reset);
    this.submit.icon(this.assets.uploadIcon()).css(ButtonColor.INFO).onClick(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        InputFile.this.file.open();
      }
    });

    this.reset.onClick(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        InputFile.this.reset();
      }
    });

    this.onChange(new ChangeHandler() {

      @Override
      public void onChange(ChangeEvent event) {
        InputFile.this.value(InputFile.this.file.value());
        toggleButtons();
        if (SimpleValidator.isEmptyOrNull(InputFile.this.value())) {
          toggleButtons();
        }
      }
    });
  }
  
  private void reset(){
    this.clear();
    this.submit.show();
  }
  
  private void toggleButtons(){
    InputFile.this.reset.toggleVisibility();
    InputFile.this.submit.toggleVisibility();
  }
  
  @Override
  public InputFile onChange(ChangeHandler handler) {
    this.file.onChange(handler);
    return this;
  }
  
  @Override
  public UIClasses css() {
    return this.input.css();
  }
  
  @Override
  public InputFile css(String... classes) {
    this.input.css(classes);
    return this;
  }
  
  @Override
  public InputFile css(UIClass... classes) {
    this.input.css(classes);
    return this;
  }

  public InputFile submitText(String text) {
    this.submit.text(text);
    return this;
  }

  public InputFile submitColor(ButtonColor color) {
    this.submit.css(color);
    return this;
  }

  public InputFile resetText(String text) {
    this.reset.text(text);
    return this;
  }

  public InputFile resetColor(ButtonColor color) {
    this.submit.css(color);
    return this;
  }

  @Override
  public InputFile value(String value) {
    this.input.value(value);
    return this;
  }

  @Override
  public String value() {
    return this.input.value();
  }

  @Override
  public InputFile clear() {
    this.input.clear();
    this.reset.hide();
    return this;
  }

  public InputFile icon(Icon icon) {
    this.submit.icon(icon);
    return this;
  }

  public InputFile placeholder(String placeholder) {
    this.input.placeholder(placeholder);
    return this;
  }

  class File extends Input<File> {

    private File() {
      super(Elements.file());
      this.hide();
    }

    public File open() {
      this.element().click();
      return this;
    }

    @Override
    public File clear() {
      return this.value("");
    }

    @Override
    public File value(String value) {
      this.element().setValue(value);
      return this;
    }

    @Override
    public String value() {
      return this.element().getValue();
    }
  }

  @Override
  public InputFile disable() {
    this.input.disable();
    return this;
  }
  
  @Override
  public boolean disabled() {
    return this.input.disabled();
  }

  @Override
  public InputFile enable() {
    this.input.enable();
    return this;
  }
  
  @Override
  public InputFile tabIndex(int index) {
    this.input.tabIndex(index);
    return this;
  }
}