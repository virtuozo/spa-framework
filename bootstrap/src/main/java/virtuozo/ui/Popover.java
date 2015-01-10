package virtuozo.ui;

public class Popover extends FloatPanel<Popover> {

  private Heading title = Heading.three().css("popover-title");

  private Body body = new Body();

  private Popover() {
    this.css("popover").add(Tag.asDiv().css("arrow")).add(this.title).add(this.body);
  }
  
  public static Popover create() {
    return new Popover();
  }

  public Body body() {
    return this.body;
  }

  public Heading heading() {
    return this.title;
  }
  
  public class Body extends Composite<Body> {
    public Body() {
      super(Elements.div());
      this.css("popover-content");
    }
    
    public Text addText(){
      return Text.create().attachTo(this);
    }
  }
}