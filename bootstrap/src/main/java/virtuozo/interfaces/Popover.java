package virtuozo.interfaces;

import virtuozo.infra.Elements;
import virtuozo.interfaces.Composite;
import virtuozo.interfaces.Tag;
import virtuozo.interfaces.Text;

public class Popover extends FloatPanel<Popover> {

  private RichHeading title = RichHeading.three().css("popover-title");

  private Body body = new Body();

  public static Popover create() {
    return new Popover();
  }
  
  private Popover() {
    this.css("popover").add(Tag.asDiv().css("arrow")).add(this.title).add(this.body);
  }
  
  public Body body() {
    return this.body;
  }

  public RichHeading heading() {
    return this.title;
  }
  
  public class Body extends Composite<Body> {
    private Body() {
      super(Elements.div());
      this.css("popover-content");
    }
    
    public Text addText(){
      return Text.create().attachTo(this);
    }
  }
}