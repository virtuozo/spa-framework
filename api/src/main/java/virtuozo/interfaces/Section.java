package virtuozo.interfaces;

import virtuozo.infra.Elements;

public class Section extends Composite<Section> {
  private Section() {
    super(Elements.section());
  }
  
  public static Section create(){
    return new Section();
  }
}