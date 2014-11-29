package hitz.virtuozo.ui;

import hitz.virtuozo.ui.api.UIClass;

import com.google.gwt.dom.client.SpanElement;

public class StackedIcon extends Component<StackedIcon>{
    private Tag<SpanElement> regular = Tag.asSpan();
    
    private Tag<SpanElement> larger = Tag.asSpan();
    
    public StackedIcon() {
      super(Elements.span());
      this.css("fa-stack", "fa-lg").addChild(this.larger).addChild(this.regular);
    }
    
    public StackedIcon regular(FontAwesome regular, UIClass... styles){
      this.regular.css().set("fa").append(regular.key()).append("fa-stack-1x").append(styles);
      return this;
    }
    
    public StackedIcon larger(FontAwesome larger, UIClass... styles){
      this.larger.css().set("fa").append(larger.key()).append("fa-stack-2x").append(styles);
      return this;
    }
  }