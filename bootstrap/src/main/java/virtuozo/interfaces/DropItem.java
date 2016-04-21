package virtuozo.interfaces;

import virtuozo.interfaces.Anchor;
import virtuozo.interfaces.Component;
import virtuozo.interfaces.Text;
import virtuozo.interfaces.OrderList.ListItem;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class DropItem extends Component<DropItem> implements HasText<DropItem> {
  private final Anchor anchor = Anchor.create();

  private final Text text = Text.create();

  private final Menu menu = Menu.create();

  DropItem(ListItem item) {
    super(item);
    this.addChild(this.anchor).addChild(this.menu).css("dropdown");
    this.anchor.add(this.text).add(Caret.create()).css("dropdown-toggle");
    this.anchor.onClick(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        DropItem.this.menu.toggle();
      }
    });
  }

  public Menu menu() {
    return this.menu;
  }

  @Override
  public DropItem text(String text) {
    this.text.text(text);
    return this;
  }

  @Override
  public String text() {
    return this.text.text();
  }
}