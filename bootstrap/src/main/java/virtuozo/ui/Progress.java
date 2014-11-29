/**
 * Copyright (C) 2004-2014 the original author or authors. See the notice.md file distributed with
 * this work for additional information regarding copyright ownership.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package virtuozo.ui;

import virtuozo.infra.NumberFormat;
import virtuozo.ui.Component;
import virtuozo.ui.CssClass;
import virtuozo.ui.Elements;
import virtuozo.ui.StyleChooser;
import virtuozo.ui.Tag;

import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style.Unit;

public class Progress extends Component<Progress> {
  private double total = 100;
  
  private double worked;
  
  public Progress() {
    super(Elements.div());
    this.css().set("progress");
  }
  
  public ProgressBar addBar(){
    ProgressBar bar = new ProgressBar();
    this.addChild(bar);
    return bar;
  }
  
  public Progress total(double total) {
    this.total = total;
    return this;
  }
  
  public class ProgressBar extends Component<ProgressBar> {
    private Tag<SpanElement> message = Tag.asSpan();
    
    private LabelFormat format = new DefaultFormat();
    
    private double worked;
    
    public ProgressBar() {
      super(Elements.div());
      this.addChild(this.message).role("progressbar").css().set("progress-bar");
    }
    
    public ProgressBar format(LabelFormat format){
      this.format = format;
      return this.update();
    }
    
    public ProgressBar worked(double amount) {
      if(Progress.this.worked + amount > Progress.this.total) {
        throw new IllegalArgumentException("The sum of progress amount should not be greater than total.");
      }
      
      Progress.this.worked += amount;
      this.worked += amount;
      
      double work = (this.worked / Progress.this.total);
      this.style().width(work * 100d, Unit.PCT);
      
      return this.update();
    }
    
    private ProgressBar update(){
      this.message.html(this.format.format(this.worked, Progress.this.total));
      return this;
    }
  }
  
  static class DefaultFormat implements LabelFormat {
    private NumberFormat format = NumberFormat.PERCENT;
    
    @Override
    public String format(double worked, double total) {
      return this.format.format(worked / total);
    }
  }
  
  public static interface LabelFormat{
    String format(double worked, double total);
  }
  
  public static class BarColor extends CssClass {
    private BarColor(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }

    public static final BarColor SUCCESS = new BarColor("progress-bar-success");
    public static final BarColor INFO = new BarColor("progress-bar-info");
    public static final BarColor WARNING = new BarColor("progress-bar-warning");
    public static final BarColor DANGER = new BarColor("progress-bar-danger");
    private static final StyleChooser STYLES = new StyleChooser(SUCCESS, INFO, WARNING, DANGER);
  }

  public static class ProgressType extends CssClass {
    private ProgressType(String name) {
      super(name);
    }

    @Override
    protected StyleChooser chooser() {
      return STYLES;
    }

    public static final ProgressType DEFAULT = new ProgressType("progress-default");
    public static final ProgressType STRIPED = new ProgressType("progress-striped");
    public static final ProgressType ANIMATED = new ProgressType("progress-striped active");
    private static final StyleChooser STYLES = new StyleChooser(DEFAULT, STRIPED, ANIMATED);
  }
}
