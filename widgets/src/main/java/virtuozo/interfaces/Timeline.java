package virtuozo.interfaces;

import virtuozo.infra.Elements;
import virtuozo.interfaces.OrderList.ListItem;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;

public class Timeline extends Component<Timeline> {
  private OrderList timeline = OrderList.unordered();
  
  public static Timeline create(){
    return new Timeline();
  }
  
  private Timeline() {
    this.incorporate(this.timeline);
    this.css().set("timeline");
  }
  
  public TimelineGroup addGroup(){
    return new TimelineGroup(this.timeline.addItem());
  }
  
  public class TimelineGroup extends Component<TimelineGroup> {
    private final ListItem group;

    TimelineGroup(ListItem group) {
      this.group = group;
      this.incorporate(this.group).css("time-label");
    }
    
    public TimelineItem addItem(){
      return new TimelineItem(timeline.addItem());
    }
    
  }
  
  public class TimelineItem extends Component<TimelineItem> implements HasIcon<TimelineItem>{
    private final Tag<DivElement> item = Tag.asDiv();
    
    private final Tag<SpanElement> icon = Tag.asSpan();
    
    private final TimeItem time = new TimeItem();
    
    private final RichHeading header = RichHeading.three();
    
    private final Body body = new Body();
    
    private final Footer footer = new Footer();
    
    TimelineItem(ListItem item) {
      this.incorporate(item);
      item.add(this.icon).add(this.item);
      this.item.add(this.time).add(this.header).add(this.body).add(this.footer);
    }
    
    @Override
    public TimelineItem css(String... classes) {
      this.icon.css(classes);
      return this;
    }
    
    @Override
    public TimelineItem css(UIClass... classes) {
      this.icon.css(classes);
      return this;
    }
    
    @Override
    public UIClasses css() {
      return this.icon.css();
    }
    
    @Override
    public TimelineItem icon(Icon icon) {
      this.icon.add(icon.asComponent());
      return this;
    }
    
    public TimeItem time(){
      return this.time;
    }
    
    public RichHeading header(){
      return this.header;
    }
    
    public Body body(){
      return this.body;
    }
    
    public Footer footer(){
      return this.footer;
    }
  }
  
  public class Body extends Composite<Body>{
    Body() {
      super(Elements.div());
      this.css("timeline-body");
    }
  }
  
  public class Footer extends Composite<Footer>{
    Footer() {
      super(Elements.div());
      this.css("timeline-footer");
    }
  }
  
  public class TimeItem extends Component<TimeItem> implements HasIcon<TimeItem>, HasText<TimeItem>{
    private Tag<SpanElement> icon = Tag.asSpan();
    
    private Text text = Text.create();
    
    TimeItem() {
      super(Elements.span());
      this.css("time");
    }

    @Override
    public TimeItem text(String text) {
      this.text.text(text);
      return this;
    }

    @Override
    public String text() {
      return this.text.text();
    }

    @Override
    public TimeItem icon(Icon icon) {
      this.icon.add(icon.asComponent());
      return this;
    }
  }
}