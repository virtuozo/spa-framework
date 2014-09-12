package hitz.virtuozo.ui;

import hitz.virtuozo.infra.api.HasText;
import hitz.virtuozo.ui.Tag;
import hitz.virtuozo.ui.Text;
import hitz.virtuozo.ui.Widget;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class DropItem extends Widget<DropItem> implements HasText<DropItem> {
  private final Tag<AnchorElement> anchor = Tag.asAnchor();

  private final Text text = new Text();

  private final Menu menu = new Menu();

  DropItem(ListItem item) {
    super(item);
    this.addChild(this.anchor).addChild(this.menu).css("dropdown");
    this.anchor.add(this.text).add(new Caret()).css("dropdown-toggle");
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