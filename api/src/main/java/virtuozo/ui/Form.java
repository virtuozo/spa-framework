package virtuozo.ui;

import com.google.gwt.dom.client.FormElement;

@SuppressWarnings("unchecked")
public abstract class Form<F extends Form<F>> extends Component<F> {
  public Form() {
    super(Elements.form());
    this.role("form");
  }

  public F action(String action) {
    this.element().setAction(action);
    return (F) this;
  }

  public F encType(Encoding encoding) {
    this.element().setEnctype(encoding.value());
    return (F) this;
  }

  public F method(Method method) {
    this.element().setMethod(method.name().toLowerCase());
    return (F) this;
  }

  @Override
  public FormElement element() {
    return super.element();
  }

  public static enum Method {
    GET, POST;
  }

  public static enum Encoding {
    MULTIPART("multipart/form-data"), URLENCODED("application/x-www-form-urlencoded");

    private String value;

    private Encoding(String value) {
      this.value = value;
    }

    public String value() {
      return value;
    }
  }
}