package hitz.virtuozo.ui;

import hitz.virtuozo.ui.api.ActivationEvent;
import hitz.virtuozo.ui.api.HasIcon;
import hitz.virtuozo.ui.api.Icon;
import hitz.virtuozo.ui.api.ActivationEvent.ActivationHandler;
import hitz.virtuozo.ui.api.DeactivationEvent;
import hitz.virtuozo.ui.api.DeactivationEvent.DeactivationHandler;
import hitz.virtuozo.ui.api.HasActivation;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;

public class PanelGroup extends Component<PanelGroup> {
  private ActivationHelper helper = new ActivationHelper();

  public PanelGroup() {
    super(Elements.div());
    this.css("panel-group");
  }

  public CollapsePanel addPanel(String title) {
    CollapsePanel panel = new CollapsePanel(title);
    if(!this.hasChildren()){
      panel.activate();
    }
    this.addChild(panel);
    this.helper.add(panel);
    return panel;
  }

  public class CollapsePanel extends Panel implements HasActivation<CollapsePanel>, HasIcon<CollapsePanel> {
    private Tag<DivElement> collapse = Tag.asDiv().css("panel-collapse", "collapse");

    private Tag<AnchorElement> anchor = Tag.asAnchor().css("collapsed");

    public CollapsePanel(String title) {
      this.header().heading().addChild(this.anchor.html(title));
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
    
    public CollapsePanel icon(Icon icon){
      icon.appendTo(this.anchor);
      return this;
    }

    @Override
    public CollapsePanel onClick(ClickHandler handler) {
      this.anchor.onClick(handler);
      return this;
    }

    @Override
    public CollapsePanel onDoubleClick(DoubleClickHandler handler) {
      this.anchor.onDoubleClick(handler);
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
      return this.anchor.id().equals(element.getId());
    }
  }
}