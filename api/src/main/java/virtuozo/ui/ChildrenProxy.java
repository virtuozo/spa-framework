package virtuozo.ui;

import java.util.ArrayList;
import java.util.List;

import virtuozo.infra.CastIterable;
import virtuozo.infra.CastIterable.TypeCast;
import virtuozo.ui.events.DetachChildrenEvent;
import virtuozo.ui.events.DetachChildrenEvent.DetachChildrenHandler;
import virtuozo.ui.interfaces.Clause;
import virtuozo.ui.interfaces.UIComponent;

import com.google.gwt.user.client.ui.WidgetHolder;

public class ChildrenProxy {
  
  private WidgetHolder holder;
  
  public void holder(WidgetHolder holder) {
    this.holder = holder;
  }
  
  void onDetachChildren(DetachChildrenHandler handler){
    this.holder.addHandler(handler, DetachChildrenEvent.TYPE);
  }
  
  void detachChildren() {
    if(this.hasChildren()){
      this.holder.fireEvent(new DetachChildrenEvent());
      this.holder.detachChildren();
    }
  }

  void removeChild(UIComponent child) {
    this.holder.remove(child.asComponent().holder());
  }

  void addChild(UIComponent add) {
    this.holder.add(add.asComponent().holder());
  }
  
  void addFirstChild(UIComponent add){
    if(!this.hasChildren()) {
      this.addChild(add);
    }
    
    this.insertChild(add, this.childAt(0));
  }

  void adoptChild(UIComponent child) {
    this.holder.adoptIt(child.asComponent().holder());
  }
  
  void tradeParent(UIComponent parent){
    UIComponent current = this.holder.parent();
    parent.asComponent().holder().adoptIt(this.holder);
    current.asComponent().adoptChild(parent);
  }

  void insertChild(UIComponent add, UIComponent before) {
    this.holder.insert(add.asComponent().holder(), before.asComponent().holder());
  }

  <UI extends UIComponent> Iterable<UI> childrenComponents() {
    return CastIterable.<UI, WidgetHolder>of(this.holder.children()).use(new TypeCast<UI, WidgetHolder>() {
      @Override
      public UI castFrom(WidgetHolder instance) {
        return instance.getReference();
      }
    });
  }
  
  <UI extends UIComponent> UI firstChild(){
    if(this.hasChildren()){
      return this.childAt(0);
    }
    
    return null;
  }
  
  <UI extends UIComponent> UI lastChild(){
    if(this.hasChildren()){
      return this.childAt(this.childrenCount() - 1);
    }
    
    return null;
  }

  <UI extends UIComponent> UI childAt(int index) {
    return this.holder.childAt(index).getReference();
  }
  
  <UI extends UIComponent> UI find(Clause clause){
    for(UIComponent child : this.childrenComponents()){
      if(clause.matches(child)){
        return (UI) child;
      }
    }
    
    return null;
  }
  
  <UI extends UIComponent> Iterable<UI> findAll(Clause clause){
    List<UI> children = new ArrayList<UI>();
    
    for(UIComponent child : this.childrenComponents()){
      if(clause.matches(child)){
        children.add((UI) child);
      }
    }
    
    return children;
  }

  int indexOfChild(UIComponent child) {
    return this.holder.indexOf(child.asComponent().holder());
  }

  int childrenCount() {
    return this.holder.childrenCount();
  }

  boolean hasChildren() {
    return this.holder.hasChildren();
  }
}