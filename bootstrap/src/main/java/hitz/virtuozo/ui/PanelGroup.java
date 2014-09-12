package hitz.virtuozo.ui;

import hitz.virtuozo.infra.api.EventHandler;
import hitz.virtuozo.ui.Elements;
import hitz.virtuozo.ui.Tag;
import hitz.virtuozo.ui.Widget;
import hitz.virtuozo.ui.api.HasActivation;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;

public class PanelGroup extends Widget<PanelGroup> {
  private ActivationHelper helper = new ActivationHelper();

  public PanelGroup() {
    super(Elements.div());
    this.css("panel-group");
  }

  public Panel addPanel(String title) {
    CollapsePanel panel = new CollapsePanel(title);
    if(!this.hasChildren()){
      panel.activate();
    }
    this.addChild(panel);
    this.helper.add(panel);
    return panel;
  }

  class CollapsePanel extends Panel implements HasActivation<CollapsePanel> {
    private Tag<DivElement> collapse = Tag.asDiv().css("panel-collapse", "collapse");

    private Tag<AnchorElement> anchor = Tag.asAnchor().css("collapsed");

    public CollapsePanel(String title) {
      this.header().heading().addChild(this.anchor.html(title));
      this.addChild(this.collapse);
      this.collapse.add(this.body().detach()).add(this.footer().detach().hide());
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
      return this;
    }

    @Override
    public CollapsePanel onActivate(EventHandler<Void> handler) {
      this.addHandler(HasActivation.FireableEvent.ACTIVATE, handler);
      return this;
    }

    @Override
    public CollapsePanel deactivate() {
      this.collapse.css().remove("in");
      return this;
    }

    @Override
    public CollapsePanel onDeactivate(EventHandler<Void> handler) {
      this.addHandler(HasActivation.FireableEvent.DEACTIVATE, handler);
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