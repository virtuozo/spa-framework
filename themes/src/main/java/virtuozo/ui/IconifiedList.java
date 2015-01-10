package virtuozo.ui;

import virtuozo.ui.IconifiedList.IconifiedListItem;
import virtuozo.ui.interfaces.HasText;

public class IconifiedList extends Parent<IconifiedList, IconifiedListItem> {
  public static IconifiedList create(){
    return new IconifiedList();
  }
  
  private IconifiedList() {
    super(Elements.ul());
    this.css("fa-ul");
  }

  public IconifiedListItem addItem() {
    IconifiedListItem item = new IconifiedListItem();
    this.add(item);
    return item;
  }

  public static class IconifiedListItem extends Composite<IconifiedListItem> implements HasText<IconifiedListItem> {
    private Text text = Text.create();

    private IconifiedListItem() {
      super(Elements.li());
      this.add(this.text);
    }

    public IconifiedListItem icon(FontAwesome icon) {
      this.add(icon.asComponent().asComponent().css("fa-li"));
      return this;
    }

    @Override
    public String text() {
      return this.text.text();
    }

    @Override
    public IconifiedListItem text(String text) {
      this.text.text(text);
      return this;
    }
  }
}