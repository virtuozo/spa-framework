package hitz.virtuozo.ui;

import hitz.virtuozo.infra.SimpleValidator;
import hitz.virtuozo.ui.api.Assets;
import hitz.virtuozo.ui.api.Icon;
import hitz.virtuozo.ui.api.UIInput;
import hitz.virtuozo.ui.css.ButtonColor;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public final class InputFile extends Component<InputFile> implements UIInput<InputFile, String> {
  private Assets assets = GWT.create(Assets.class);
  
  private File file = new File();

  private InputGroup input = new InputGroup(new InputText());

  private Button submit = new Button();

  private Button reset = new Button().css(ButtonColor.DANGER).hide();

  public InputFile() {
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
        file.open();
      }
    });

    this.reset.onClick(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        clear();
      }
    });

    this.file.onChange(new ChangeHandler() {

      @Override
      public void onChange(ChangeEvent event) {
        InputFile.this.input.value(InputFile.this.file.value());
        if (!SimpleValidator.isEmptyOrNull(InputFile.this.file.value())) {
          InputFile.this.reset.hide();
        }
      }
    });
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

    public File() {
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
}