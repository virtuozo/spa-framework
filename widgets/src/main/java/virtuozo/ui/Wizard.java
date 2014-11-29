package virtuozo.ui;

import virtuozo.ui.Button;
import virtuozo.ui.Component;
import virtuozo.ui.Composite;
import virtuozo.ui.CssClass;
import virtuozo.ui.Elements;
import virtuozo.ui.Heading;
import virtuozo.ui.PillPanel;
import virtuozo.ui.StyleChooser;
import virtuozo.ui.Tag;
import virtuozo.ui.FinishEvent.FinishHandler;
import virtuozo.ui.Heading.Level;
import virtuozo.ui.PillPanel.Pill;
import virtuozo.ui.api.ActivationEvent;
import virtuozo.ui.api.Assets;
import virtuozo.ui.api.DeactivationEvent;
import virtuozo.ui.api.HasText;
import virtuozo.ui.api.PageChangeEvent;
import virtuozo.ui.api.UIClass;
import virtuozo.ui.api.UIClasses;
import virtuozo.ui.api.UIComponent;
import virtuozo.ui.api.ActivationEvent.ActivationHandler;
import virtuozo.ui.api.DeactivationEvent.DeactivationHandler;
import virtuozo.ui.api.PageChangeEvent.PageChangeHandler;
import virtuozo.ui.css.ButtonColor;
import virtuozo.ui.css.Floating;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class Wizard extends Component<Wizard> {
  private Tag<DivElement> card = Tag.asDiv().css("card wizard-card");
  
  private Heading heading = new Heading(Level.THREE);
  
  private PillPanel navigation = new PillPanel();
  
  private Tag<DivElement> body = Tag.asDiv().css("tab-content");
  
  private Tag<DivElement> footer = Tag.asDiv().css("wizard-footer");
  
  private Button next = new Button().css(Button.Size.SMALL).css(ButtonColor.PRIMARY);
  
  private Button previous = new Button().css(Button.Size.SMALL).css(ButtonColor.PRIMARY).hide();
  
  private Button finish = new Button().css(Button.Size.SMALL).css(ButtonColor.PRIMARY).hide();
  
  private int index;
  
  public Wizard() {
    super(Elements.div());
    this.css("wizard-container").addChild(this.card);
    this.init();
  }
  
  private void init(){
    Tag<DivElement> header = Tag.asDiv().css("wizard-header");
    header.add(this.heading);
    
    Tag<DivElement> right = Tag.asDiv().css(Floating.RIGHT).add(this.next).add(this.finish);
    Tag<DivElement> left = Tag.asDiv().css(Floating.LEFT).add(this.previous);
    this.footer.add(right).add(left).add(Tag.asDiv().css("clearfix"));
    
    this.card.add(header).add(this.navigation).add(this.body).add(footer);
    
    Assets assets = GWT.create(Assets.class);
    this.next.icon(assets.nextIcon()).onClick(new ClickHandler() {
      
      @Override
      public void onClick(ClickEvent event) {
        Wizard.this.next();
      }
    });
    this.previous.icon(assets.previousIcon()).onClick(new ClickHandler() {
      
      @Override
      public void onClick(ClickEvent event) {
        Wizard.this.previous();
      }
    });;
    this.finish.icon(assets.successIcon()).onClick(new ClickHandler() {
      
      @Override
      public void onClick(ClickEvent event) {
        Wizard.this.finish();
      }
    });;
  }
  
  @Override
  public UIClasses css() {
    return this.card.css();
  }
  
  @Override
  public Wizard css(String... classes) {
    this.card.css(classes);
    return this;
  }
  
  @Override
  public Wizard css(UIClass... classes) {
    this.card.css(classes);
    return this;
  }
  
  public Wizard hideControls(){
    this.footer.hide();
    return this;
  }
  
  public Heading heading(){
    return this.heading;
  }
  
  public Wizard next(){
    this.index++;
    
    if(this.index >= this.navigation.childrenCount()){
      this.index = this.navigation.childrenCount() - 1;
    }
    
    return this.go();
  }
  
  public Wizard previous(){
    this.index--;
    if(this.index < 0){
      this.index = 0;
      this.previous.hide();
    }
    
    return this.go();
  }
  
  public Wizard finish(){
    return this.fireEvent(new FinishEvent());
  }
  
  public Wizard onChange(PageChangeHandler handler){
    return this.addHandler(PageChangeEvent.TYPE, handler);
  }
  
  public Wizard onFinish(FinishHandler handler){
    return this.addHandler(FinishEvent.TYPE, handler);
  }
  
  public Step addStep(){
    final Pill pill = this.navigation.addPill();
    Step step = new Step(pill);
    if(this.navigation.childrenCount() == 1) {
      step.activate();
    }
    
    double width = 100d / this.navigation.childrenCount();
    for(UIComponent child : this.navigation.childrenComponents()){
      child.asComponent().style().width(width, Unit.PCT);
    }
    
    this.body.add(step);
    return step;
  }
  
  private Wizard go(){
    for(Step child : this.body.<Step>childrenComponents()){
      child.deactivate();
    }
    
    ((Step) this.body.childAt(this.index)).activate();
    
    this.fireEvent(new PageChangeEvent(this.index));
    
    return this;
  }
  
  public class Step extends Composite<Step> implements HasText<Step> {
    private Pill pill;
    
    private Step(Pill pill) {
      super(Elements.div());
      this.pill = pill;
      this.pill.onActivate(new ActivationHandler() {
        
        @Override
        public void onActivate(ActivationEvent event) {
          Step.this.activate();
        }
      }).onDeactivate(new DeactivationHandler() {
        
        @Override
        public void onDeactivate(DeactivationEvent event) {
          Step.this.deactivate();
        }
      });
      
      this.css("tab-pane");
    }
    
    @Override
    public String text() {
      return this.pill.text();
    }
    
    @Override
    public Step text(String text) {
      this.pill.text(text);
      return this;
    }
    
    private void activate(){
      this.css("active");
    }
    
    private void deactivate(){
      this.css().remove("active");
    }
  }
  
  public static class Color extends CssClass {
    private Color(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }

    public static final Color BLUE = new Color("ct-wizard-blue");

    public static final Color GREEN = new Color("ct-wizard-green");

    public static final Color ORANGE = new Color("ct-wizard-orange");

    public static final Color RED = new Color("ct-wizard-red");

    private static final StyleChooser STYLES = new StyleChooser(BLUE, GREEN, ORANGE, RED);
  }
}