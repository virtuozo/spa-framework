package hitz.virtuozo.ui;

import hitz.virtuozo.ui.OrderList.Type;
import hitz.virtuozo.ui.api.ActivationEvent;
import hitz.virtuozo.ui.api.ActivationEvent.ActivationHandler;
import hitz.virtuozo.ui.api.DeactivationEvent;
import hitz.virtuozo.ui.api.DeactivationEvent.DeactivationHandler;
import hitz.virtuozo.ui.api.HasActivation;
import hitz.virtuozo.ui.api.HasIcon;
import hitz.virtuozo.ui.api.HasText;
import hitz.virtuozo.ui.api.Icon;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickHandler;

public class SideMenu extends Component<SideMenu> {
  private ActivationHelper activationHelper = new ActivationHelper();
  
  private OrderList list = new OrderList(Type.UNORDERED);
  
  public SideMenu() {
    this.incorporate(this.list);
    this.css("sidemenu");
  }
  
  public SideItem addItem(){
    SideItem item = new SideItem(this.list.addItem());
    this.activationHelper.add(item);
    return item;
  }
  
  public class SideItem extends Component<SideItem> implements HasActivation<SideItem>, HasIcon<SideItem>, HasText<SideItem> {
    private Tag<AnchorElement> link = Tag.asAnchor();
    
    public SideItem(ListItem item) {
      super(item);
      this.addChild(this.link);
    }

    @Override
    public SideItem onClick(ClickHandler handler) {
      this.link.onClick(handler);
      return this;
    }

    @Override
    public SideItem onDoubleClick(DoubleClickHandler handler) {
      this.link.onDoubleClick(handler);
      return this;
    }

    @Override
    public SideItem activate() {
      this.link.css("active");
      return this;
    }

    @Override
    public SideItem onActivate(ActivationHandler handler) {
      return this.addHandler(ActivationEvent.TYPE, handler);
    }

    @Override
    public SideItem deactivate() {
      this.link.css().remove("active");
      return this;
    }

    @Override
    public SideItem onDeactivate(DeactivationHandler handler) {
      return this.addHandler(DeactivationEvent.TYPE, handler);
    }

    @Override
    public boolean active() {
      return this.link.css().contains("active");
    }

    @Override
    public boolean match(Element element) {
      return this.link.id().equals(element.getId());
    }

    @Override
    public SideItem icon(Icon icon) {
      icon.appendTo(this.link);
      return this;
    }

    @Override
    public SideItem text(String text) {
      this.link.text(text);
      return this;
    }

    @Override
    public String text() {
      return this.link.text();
    }
    
    public SideMenuItem addMenu(){
      final SideMenuItem menu = new SideMenuItem();
      this.css("menu").addChild(menu);
      this.link.onClick(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          menu.show();
        }
      });
      this.onDeactivate(new DeactivationHandler() {
        
        @Override
        public void onDeactivate(DeactivationEvent event) {
          menu.hide();
        }
      }).onActivate(new ActivationHandler() {
        
        @Override
        public void onActivate(ActivationEvent event) {
          menu.show();
        }
      });
      
      return menu;
    }
  }
  
  public class SideMenuItem extends Component<SideMenu>{
    private OrderList list = new OrderList(Type.UNORDERED);
    
    public SideMenuItem() {
      this.incorporate(this.list);
      this.list.css("menu-item");
    }
    
    public SideItem addItem(){
      SideItem item = new SideItem(this.list.addItem());
      SideMenu.this.activationHelper.add(item);
      return item;
    }
  }
}

/*
 <ul class="sidebar-menu" id="nav-accordion">
              
                  <p class="centered"><a href="profile.html"><img src="assets/img/ui-sam.jpg" class="img-circle" width="60"></a></p>
                  <h5 class="centered">Marcel Newman</h5>
                    
                  <li class="mt">
                      <a class="active" href="index.html">
                          <i class="fa fa-dashboard"></i>
                          <span>Dashboard</span>
                      </a>
                  </li>

                  <li class=".menu dcjq-parent-li">
                      <a href="javascript:;" class="dcjq-parent">
                          <i class="fa fa-desktop"></i>
                          <span>UI Elements</span>
                      <span class="dcjq-icon"></span></a>
                      <ul class="menu-item" style="display: none;">
                          <li><a href="general.html">General</a></li>
                          <li><a href="buttons.html">Buttons</a></li>
                          <li><a href="panels.html">Panels</a></li>
                          <li><a href="google_maps.html">Google Maps</a></li>
                      </ul>
                  </li>

                  <li class=".menu dcjq-parent-li">
                      <a href="javascript:;" class="dcjq-parent">
                          <i class="fa fa-cogs"></i>
                          <span>Components</span>
                      <span class="dcjq-icon"></span></a>
                      <ul class="menu-item" style="display: none;">
                          <li><a href="calendar.html">Calendar</a></li>
                          <li><a href="gallery.html">Gallery</a></li>
                          <li><a href="todo_list.html">Todo List</a></li>
                          <li><a href="dropzone.html">Dropzone File Upload</a></li>
                          <li><a href="inline_editor.html">Inline Editor</a></li>
                          <li><a href="file_upload.html">Multiple File Upload</a></li>
                      </ul>
                  </li>
                  <li class=".menu dcjq-parent-li">
                      <a href="javascript:;" class="dcjq-parent">
                          <i class="fa fa-book"></i>
                          <span>Extra Pages</span>
                      <span class="dcjq-icon"></span></a>
                      <ul class="menu-item" style="display: none;">
                          <li><a href="blank.html">Blank Page</a></li>
                          <li><a href="login.html">Login</a></li>
                          <li><a href="lock_screen.html">Lock Screen</a></li>
                          <li><a href="profile.html">Profile</a></li>
                          <li><a href="invoice.html">Invoice</a></li>
                          <li><a href="pricing_table.html">Pricing Table</a></li>
                          <li><a href="faq.html">FAQ</a></li>
                      </ul>
                  </li>
                  <li class=".menu dcjq-parent-li">
                      <a href="javascript:;" class="dcjq-parent">
                          <i class="fa fa-tasks"></i>
                          <span>Forms</span>
                      <span class="dcjq-icon"></span></a>
                      <ul class="menu-item" style="display: none;">
                          <li><a href="form_component.html">Form Components</a></li>
                          <li><a href="advanced_form_components.html">Advanced Components</a></li>
                          <li><a href="form_validation.html">Form Validation</a></li>
                      </ul>
                  </li>
                  <li class=".menu dcjq-parent-li">
                      <a href="javascript:;" class="dcjq-parent">
                          <i class="fa fa-th"></i>
                          <span>Data Tables</span>
                      <span class="dcjq-icon"></span></a>
                      <ul class="menu-item" style="display: none;">
                          <li><a href="basic_table.html">Basic Table</a></li>
                          <li><a href="responsive_table.html">Responsive Table</a></li>
                          <li><a href="advanced_table.html">Advanced Table</a></li>
                      </ul>
                  </li>
                  <li>
                      <a href="inbox.html">
                          <i class="fa fa-envelope"></i>
                          <span>Mail </span>
                          <span class="label label-theme pull-right mail-info">2</span>
                      </a>
                  </li>
                  <li class=".menu dcjq-parent-li">
                      <a href="javascript:;" class="dcjq-parent">
                          <i class=" fa fa-bar-chart-o"></i>
                          <span>Charts</span>
                      <span class="dcjq-icon"></span></a>
                      <ul class="menu-item" style="display: none;">
                          <li><a href="morris.html">Morris</a></li>
                          <li><a href="chartjs.html">Chartjs</a></li>
                          <li><a href="flot_chart.html">Flot Charts</a></li>
                          <li><a href="xchart.html">xChart</a></li>
                      </ul>
                  </li>
                  <li class=".menu dcjq-parent-li">
                      <a href="javascript:;" class="dcjq-parent">
                          <i class="fa fa-comments-o"></i>
                          <span>Chat Room</span>
                      <span class="dcjq-icon"></span></a>
                      <ul class="menu-item" style="display: none;">
                          <li><a href="lobby.html">Lobby</a></li>
                          <li><a href="chat_room.html"> Chat Room</a></li>
                      </ul>
                  </li>

              </ul> 
 
 */
