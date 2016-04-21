package virtuozo.interfaces;

import virtuozo.infra.Elements;

import com.google.gwt.dom.client.LegendElement;

public class SimpleForm extends Form<SimpleForm>{
  public static SimpleForm create(){
    return new SimpleForm();
  }
  
  private SimpleForm() {
    super();
  }
  
  public FieldSet addFieldSet(){
    FieldSet fieldSet = new FieldSet();
    this.addChild(fieldSet);
    return fieldSet;
  }
  
  public static class FieldSet extends Composite<FieldSet> implements HasText<FieldSet> {
    private Tag<LegendElement> legend = Tag.asLegend();
    
    private FieldSet() {
      super(Elements.fieldset());
      this.add(this.legend);
    }
    
    @Override
    public String text() {
      return this.legend.text();
    }
    
    @Override
    public FieldSet text(String text) {
      this.legend.text(text);
      return this;
    }
  }
}
