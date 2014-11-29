package hitz.virtuozo.ui;

import hitz.virtuozo.ui.IconifiedList.IconifiedListItem;
import hitz.virtuozo.ui.api.HasText;

public class IconifiedList extends Parent<IconifiedList, IconifiedListItem> {
    public IconifiedList() {
      super(Elements.ul());
      this.css("fa-ul");
    }
    
    public IconifiedListItem addItem(){
      IconifiedListItem item = new IconifiedListItem();
      this.add(item);
      return item;
    }
    
    public static class IconifiedListItem extends Composite<IconifiedListItem> implements HasText<IconifiedListItem> {
      private IconifiedListItem() {
        super(Elements.li());
        this.css("fa-li");
      }
      
      public IconifiedListItem icon(FontAwesome icon){
        icon.appendTo(this);
        return this;
      }
      
      @Override
      public String text() {
        return this.element().getInnerText();
      }
      
      @Override
      public IconifiedListItem text(String text) {
        this.element().setInnerText(text);
        return this;
      }
    }
  }