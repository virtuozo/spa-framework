package virtuozo.interfaces;

import virtuozo.infra.Elements;
import virtuozo.interfaces.Composite;
import virtuozo.interfaces.HasText;
import virtuozo.interfaces.Parent;
import virtuozo.interfaces.Text;
import virtuozo.interfaces.IconifiedList.IconifiedListItem;

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