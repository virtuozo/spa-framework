package virtuozo.ui;

import java.util.Date;
import java.util.List;

import virtuozo.infra.Calendar;
import virtuozo.infra.Calendar.Month;
import virtuozo.infra.Calendar.WeekDay;
import virtuozo.infra.DateFormat;
import virtuozo.ui.Table.Body;
import virtuozo.ui.Table.Cell;
import virtuozo.ui.Table.Header;
import virtuozo.ui.Table.Row;
import virtuozo.ui.events.SelectionEvent;
import virtuozo.ui.events.SelectionEvent.SelectionHandler;
import virtuozo.ui.interfaces.Assets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;

public final class MonthPanel extends Component<MonthPanel> {

  private Table table = Table.create().condensed();

  private Selector selector;

  private Days days;

  private Calendar currentDate = Calendar.now();

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

  public boolean isSelected() {
    return this.selected;
  }

  public MonthPanel onSelection(SelectionHandler<Date> handler) {
    return this.addHandler(SelectionEvent.TYPE, handler);
  }

  public MonthPanel range(Date start, Date end) {
    return this.range(Calendar.of(start), Calendar.of(end));
  }

  public MonthPanel range(Calendar start, Calendar end) {
    this.days.range.start(start);
    this.days.range.end(end);

    if (start.after(this.currentDate)) {
      this.currentDate = Calendar.clone(start);
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
    this.days.range.currentMonth = this.currentDate.getMonth();

    return this;
  }

  public MonthPanel set(Calendar calendar) {
    if (this.currentDate != null && !this.currentDate.equals(calendar)) {
      this.currentDate = calendar;
      this.fireEvent(new SelectionEvent<Date>(calendar.toDate()));
    }

    this.selector.set(calendar);
    this.days.set(calendar);

    return this;
  }

  public MonthPanel set(Date date) {
    return this.set(Calendar.of(date));
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
                MonthPanel.this.fireEvent(new SelectionEvent<Date>(calendar.toDate()));
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
      if (!this.range.currentMonth.equals(calendar.getMonth())) {
        this.renderMonth(calendar);
        return this;
      }

      // find cell to select
      for (Row row : this.body.children()) {
        Cell cell = row.childAt(calendar.getDay().ordinal());
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
    }

    void renderMonth(Calendar base) {
      Calendar runner = Calendar.clone(base).moveToFirstDayOfMonth();

      this.range.currentMonth = runner.getMonth();

      // Adjust calendar to the first day of week (Sunday)
      while (!runner.getDay().equals(WeekDay.SUNDAY)) {
        runner.addDays(-1);
      }

      for (int week = 0; week < 6; week++) {
        Row row = this.body.childAt(week);
        for (int weekday = 0; weekday < 7; weekday++) {
          Cell cell = row.childAt(weekday).text(String.valueOf(runner.getDate())).attribute("date-time", String.valueOf(runner.getTime()));

          this.decorate(cell, runner);

          runner.addDays(1);
        }
      }
    }

    void decorate(Cell cell, Calendar runner) {
      if (!range.eval(runner)) {
        cell.css("off disabled");
        return;
      }

      cell.css("available in-range");
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

        if (!calendar.getMonth().equals(this.currentMonth)) {
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
      Calendar now = Calendar.now();
      Row controls = header.addRow();

      this.previous = controls.addCell().onMouseDown(this.selection);
      this.assets.previousIcon().attachTo(this.previous);

      this.month = controls.addCell().colspan(5);
      this.set(now);

      this.next = controls.addCell().onMouseDown(this.selection);
      this.assets.nextIcon().attachTo(this.next);

      Row monthDays = header.addRow();

      List<String> monthDayNames = Calendar.getMonthDayNames(now, WeekDay.SUNDAY);
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