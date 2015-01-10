package virtuozo.ui;

import java.util.List;

import virtuozo.ui.ActivationHelper.Behavior;
import virtuozo.ui.events.ActivationEvent;
import virtuozo.ui.events.ActivationEvent.ActivationHandler;
import virtuozo.ui.events.DeactivationEvent;
import virtuozo.ui.events.DeactivationEvent.DeactivationHandler;
import virtuozo.ui.interfaces.HasActivation;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;

public class PanelGroup extends Component<PanelGroup> {
  private ActivationHelper helper = ActivationHelper.create();

  public static PanelGroup create(){
    return new PanelGroup();
  }
  
  private PanelGroup() {
    super(Elements.div());
    this.css("panel-group");
    this.helper.behavior(new Behavior() {
      @Override
      public void doActivation(Element element, List<HasActivation<?>> activationList) {
        for (HasActivation<?> widget : activationList) {
          if (widget.match(element) && !widget.active()) {
            widget.activate().asComponent().fireEvent(new ActivationEvent());
            continue;
          }
          widget.deactivate().asComponent().fireEvent(new DeactivationEvent());
        }
      }
    });
  }
  
  public CollapsePanel addPanel() {
    CollapsePanel panel = new CollapsePanel();
    if(!this.hasChildren()){
      panel.activate();
    }
    this.addChild(panel);
    this.helper.add(panel);
    return panel;
  }

  public class CollapsePanel extends Panel implements HasActivation<CollapsePanel> {
    private Tag<DivElement> collapse = Tag.asDiv().css("panel-collapse", "collapse");

    CollapsePanel() {
      this.addChild(this.collapse);
      this.collapse.add(this.body().detach().hide()).add(this.footer().detach().hide());
    }
    
    @Override
    public ListGroup addListGroup() {
      ListGroup group = super.addListGroup();
      this.collapse.add(group.detach());
      return group;
    }
    
    @Override
    public Table addTable() {
      Table table = super.addTable();
      this.collapse.add(table.detach());
      return table;
    }
    
    @Override
    public CollapsePanel onClick(ClickHandler handler) {
      this.header().on(handler);
      return this;
    }

    @Override
    public CollapsePanel onDoubleClick(DoubleClickHandler handler) {
      this.header().on(handler);
      return this;
    }

    @Override
    public CollapsePanel activate() {
      this.collapse.css("in");
      this.fireEvent(new ActivationEvent());
      return this;
    }

    @Override
    public CollapsePanel onActivate(ActivationHandler handler) {
      this.addHandler(ActivationEvent.TYPE, handler);
      return this;
    }

    @Override
    public CollapsePanel deactivate() {
      this.collapse.css().remove("in");
      this.fireEvent(new DeactivationEvent());
      return this;
    }

    @Override
    public CollapsePanel onDeactivate(DeactivationHandler handler) {
      this.addHandler(DeactivationEvent.TYPE, handler);
      return this;
    }

    @Override
    public boolean active() {
      return this.collapse.css().contains("in");
    }

    @Override
    public boolean match(Element element) {
      return this.header().id().equals(element.getId());
    }
  }
}