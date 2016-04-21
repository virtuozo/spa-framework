package virtuozo.interfaces;

 import java.util.List;

import virtuozo.infra.Elements;
import virtuozo.infra.data.Calendar;
import virtuozo.infra.data.Calendar.Month;
import virtuozo.infra.data.Calendar.WeekDay;
import virtuozo.infra.DateFormat;
import virtuozo.interfaces.Component;
import virtuozo.interfaces.Table;
import virtuozo.interfaces.Table.Body;
import virtuozo.interfaces.Table.Cell;
import virtuozo.interfaces.Table.Header;
import virtuozo.interfaces.Table.Row;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;

public final class MonthPanel extends Component<MonthPanel> {

  private Table table = Table.create().condensed();

  private Selector selector;

  private Days days;

  private Calendar currentDate = Calendar.create();

  private boolean selected;

  public static MonthPanel create(){
    return new MonthPanel();
  }
  
  private MonthPanel() {
    super(Elements.div());
    this.init();
  }

  MonthPanel onPrevious(ClickHandler handler) {
    this.selector.onPrevious(handler);
    return this;
  }

  MonthPanel onNext(ClickHandler handler) {
    this.selector.onNext(handler);
    return this;
  }
  
  MonthPanel onChange(ChangeHandler handler){
    return this.on(handler);
  }

  Calendar current() {
    return this.currentDate;
  }

  private void init() {
    this.css("calendar").addChild(this.table);

    this.selector = new Selector(this.table.header());

    this.days = new Days(this.table.body());

    this.selector.onPrevious(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        MonthPanel.this.previous();
        MonthPanel.this.selected = false;
      }
    });

    this.selector.onNext(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        MonthPanel.this.next();
        MonthPanel.this.selected = false;
      }
    });
  }

  public MonthPanel selected(boolean selected) {
    this.selected = selected;
    return this;
  }

  public boolean selected() {
    return this.selected;
  }

  public MonthPanel range(Calendar start, Calendar end) {
    this.days.range.start(start);
    this.days.range.end(end);

    if (start.after(this.currentDate)) {
      this.currentDate = start.cloneOf();
    }

    this.days.renderMonth(this.currentDate);
    this.selector.set(this.currentDate);

    return this;
  }

  MonthPanel next() {
    return this.runToMonth(1);
  }

  MonthPanel previous() {
    return this.runToMonth(-1);
  }

  private MonthPanel runToMonth(int months) {
    this.currentDate.addMonths(months);

    this.selector.set(this.currentDate);
    this.days.renderMonth(this.currentDate);
    this.days.range.currentMonth = this.currentDate.month();

    return this;
  }

  public MonthPanel set(Calendar calendar) {
    if (this.currentDate != null && !this.currentDate.equals(calendar)) {
      this.currentDate = calendar;
      this.changed();
    }

    this.selector.set(calendar);
    this.days.set(calendar);

    return this;
  }
  
  private void changed(){
    this.fireNativeEvent(Document.get().createChangeEvent());
  }

  class Days {

    private Body body;

    private Cell currentDay;

    private Range range;

    private Days(Body body) {
      this.init(body);
    }

    private void init(Body body) {
      this.range = new Range();
      this.body = body;

      for (int w = 0; w < 6; w++) {
        Row row = body.addRow();
        for (int d = 0; d < 7; d++) {
          final Cell cell = row.addCell();
          cell.on(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
              Calendar calendar = Days.this.toCalendar(cell);
              if (Days.this.range.eval(calendar) && !cell.css().contains("off")) {
                Days.this.selectDate(calendar, cell);
                MonthPanel.this.changed();
              }
            }
          }).on(new MouseDownHandler() {

            @Override
            public void onMouseDown(MouseDownEvent event) {
              MonthPanel.this.selected = true;
            }
          });
        }
      }

      this.renderMonth(MonthPanel.this.currentDate);
    }

    Calendar toCalendar(Cell cell) {
      return Calendar.of(Long.valueOf(cell.getAttribute("date-time")));
    }

    public Days set(Calendar calendar) {
      if (!this.range.currentMonth.equals(calendar.month())) {
        this.renderMonth(calendar);
        return this;
      }

      // find cell to select
      for (Row row : this.body.children()) {
        Cell cell = row.childAt(calendar.day().ordinal());
        Calendar cellCalendar = this.toCalendar(cell);

        if (cellCalendar.equalsIgnoreTime(calendar)) {
          this.selectDate(calendar, cell);
          break;
        }
      }

      return this;
    }

    void selectDate(Calendar calendar, Cell cell) {
      // remove active from last active day
      if (this.currentDay != null) {
        this.currentDay.css().remove("active");
        this.decorate(this.currentDay, this.toCalendar(this.currentDay));
      }

      cell.css("active");
      this.currentDay = cell;
      MonthPanel.this.currentDate = calendar;
    }

    void renderMonth(Calendar base) {
      Calendar runner = base.cloneOf().moveToFirstDayOfMonth();

      this.range.currentMonth = runner.month();

      // Adjust calendar to the first day of week (Sunday)
      while (!runner.day().equals(WeekDay.SUNDAY)) {
        runner.addDays(-1);
      }

      for (int week = 0; week < 6; week++) {
        Row row = this.body.childAt(week);
        for (int weekday = 0; weekday < 7; weekday++) {
          Cell cell = row.childAt(weekday).text(String.valueOf(runner.date())).attribute("date-time", String.valueOf(runner.time()));

          this.decorate(cell, runner);

          runner.addDays(1);
        }
      }
    }

    void decorate(Cell cell, Calendar runner) {
      if (!range.eval(runner)) {
        cell.css().remove("available", "in-range").append("off", "disabled");
        return;
      }

      cell.css().append("available", "in-range").remove("off", "disabled");
    }

    class Range {

      private Calendar start;

      private Calendar end;

      private Month currentMonth;
      
      private Range() {
        super();
      }

      void start(Calendar start) {
        if (start == null) {
          return;
        }
        this.start = start.clearTime();
      }

      void end(Calendar end) {
        if (end == null) {
          return;
        }
        this.end = end.clearTime();
      }

      public boolean eval(Calendar calendar) {
        boolean eval = true;

        if (!calendar.month().equals(this.currentMonth)) {
          return false;
        }

        if (this.start == null && this.end == null) {
          return true;
        }

        if (this.start != null && !(calendar.equalsIgnoreTime(this.start) || calendar.after(this.start))) {
          eval = false;
        }

        if (this.end != null && !(calendar.equalsIgnoreTime(this.end) || calendar.before(this.end))) {
          eval = eval && false;
        }

        return eval;
      }
    }
  }

  class Selector {

    private Cell previous;

    private Cell month;

    private Cell next;

    private DateFormat monthNameFormat = DateFormat.YEAR_MONTH_ABBR;
    
    private Assets assets = GWT.create(Assets.class);

    private Selector(Header header) {
      this.init(header);
    }

    void onPrevious(ClickHandler handler) {
      this.previous.onClick(handler);
    }

    void onNext(ClickHandler handler) {
      this.next.onClick(handler);
    }

    private void init(Header header) {
      Calendar now = Calendar.create();
      Row controls = header.addRow();

      this.previous = controls.addCell().onMouseDown(this.selection);
      this.assets.previousIcon().attachTo(this.previous);

      this.month = controls.addCell().colspan(5);
      this.set(now);

      this.next = controls.addCell().onMouseDown(this.selection);
      this.assets.nextIcon().attachTo(this.next);

      Row monthDays = header.addRow();

      List<String> monthDayNames = Calendar.monthDayNames(now, WeekDay.SUNDAY);
      for (int i = 0; i < monthDayNames.size(); i++) {
        monthDays.addCell().text(monthDayNames.get(i));
      }
    }

    private MouseDownHandler selection = new MouseDownHandler() {

      @Override
      public void onMouseDown(MouseDownEvent event) {
        MonthPanel.this.selected = true;
      }
    };

    Selector set(Calendar calendar) {
      this.month.text(calendar.toString(this.monthNameFormat));
      return this;
    }
  }
}