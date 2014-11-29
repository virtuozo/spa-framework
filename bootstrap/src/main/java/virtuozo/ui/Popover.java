package virtuozo.ui;

import virtuozo.ui.Tag;

import com.google.gwt.dom.client.DivElement;

public class Popover extends FloatPanel<Popover> {

  private Heading title = new Heading(Heading.Level.THREE).css("popover-title");

  private Tag<DivElement> body = Tag.asDiv().css("popover-content");

  private Tag<DivElement> footer = Tag.asDiv().css("popover-footer");

  public Popover() {
    this.css("popover").add(Tag.asDiv().css("arrow")).add(this.title).add(this.body).add(this.footer.hide());
  }

  public Tag<DivElement> body() {
    return this.body;
  }

  public Tag<DivElement> footer() {
    return this.footer.show();
  }

  public Popover title(String title) {
    this.title.text(title);
    return this;
  }
}