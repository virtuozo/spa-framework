package virtuozo.infra;

import virtuozo.infra.Calendar.Month;
import virtuozo.suite.APITestCase;


public class CalendarUT extends APITestCase {

  public void testAfter() {
    Calendar now = Calendar.create();
    Calendar calendarInFuture = now.cloneOf().addDays(2);
    
    assertTrue(calendarInFuture.after(now));
    assertFalse(now.after(calendarInFuture));
  }

  public void testBefore() {
    Calendar now = Calendar.create();
    Calendar calendarInFuture = now.cloneOf().addDays(2);
    
    assertTrue(now.before(calendarInFuture));
    assertFalse(calendarInFuture.before(now));
  }

  public void testAddMilliseconds() {
    int expected = 1000;
    Calendar now = Calendar.create();
    Calendar calendarInFuture = now.cloneOf().addMilliseconds(expected);
    
    long miliseconds = calendarInFuture.time() - now.time();
    assertEquals(expected, miliseconds);
  }

  public void testAddSeconds() {
    int expected = 10;
    Calendar now = Calendar.create();
    Calendar calendarInFuture = now.cloneOf().addSeconds(10);
    
    long miliseconds = calendarInFuture.time() - now.time();
    assertEquals(expected * Calendar.MILLIS_PER_SECOND, miliseconds);
  }

  public void testAddMinutes() {
    int expected = 10;
    Calendar now = Calendar.create();
    Calendar calendarInFuture = now.cloneOf().addMinutes(expected);
    
    long miliseconds = calendarInFuture.time() - now.time();
    assertEquals(expected * Calendar.MILLIS_PER_MINUTE, miliseconds);
  }

  public void testAddHours() {
    int expected = 10;
    Calendar now = Calendar.create();
    Calendar calendarInFuture = now.cloneOf().addHours(expected);
    
    long miliseconds = calendarInFuture.time() - now.time();
    assertEquals(expected * Calendar.MILLIS_PER_HOUR, miliseconds);
  }

  public void testAddDays() {
    int expected = 10;
    Calendar calendarInFuture = Calendar.create().addDays(expected);
    Calendar now = Calendar.create();
    
    long miliseconds = calendarInFuture.time() - now.time();
    assertEquals(expected * Calendar.MILLIS_PER_DAY, miliseconds);
  }

  public void testAddWeeks() {
    int expected = 1;
    Calendar now = Calendar.create();
    Calendar calendarInFuture = now.cloneOf().addWeeks(expected);
    
    long miliseconds = calendarInFuture.time() - now.time();
    assertEquals(expected * Calendar.MILLIS_PER_WEEK, miliseconds);
  }

  public void testAddMonths() {
    int months = 10;
    Calendar now = Calendar.create();
    Calendar calendarInFuture = now.cloneOf().addMonths(months);
    
    int expected = now.month().ordinal() + months;
    int monthsInYear = Month.values().length;
    
    if(expected > monthsInYear){
      expected = expected - monthsInYear;
    }
    
    assertEquals(expected, calendarInFuture.month().ordinal());
  }

  public void testAddYears() {
    int years = 10;
    Calendar now = Calendar.create();
    Calendar calendarInFuture = now.cloneOf().addYears(years);
    
    int expected = now.year() + years;
    assertEquals(expected, calendarInFuture.year());
  }

  public void testSeconds() {
    int expected = 10;
    Calendar now = Calendar.create();
    now.seconds(expected);
    
    assertEquals(expected, now.seconds());
  }

  public void testMinutes() {
    int expected = 10;
    Calendar now = Calendar.create();
    now.minutes(expected);
    
    assertEquals(expected, now.minutes());
  }

  public void testHours() {
    int expected = 10;
    Calendar now = Calendar.create();
    now.hours(expected);
    
    assertEquals(expected, now.hours());
  }

  public void testDate() {
    int expected = 10;
    Calendar now = Calendar.create();
    now.date(expected);
    
    assertEquals(expected, now.date());
  }

  public void testMonth() {
    int expected = 10;
    Calendar now = Calendar.create();
    now.month(expected);
    
    assertEquals(expected, now.month().ordinal());
  }

  public void testYear() {
    int expected = 2015;
    Calendar now = Calendar.create();
    now.year(expected);
    
    assertEquals(expected, now.year());
  }

  public void testClearTime() {
    Calendar now = Calendar.create().clearTime();
    assertEquals(0, now.seconds());
    assertEquals(0, now.minutes());
    assertEquals(0, now.hours());
  }

  public void testMoveToFirstDayOfMonth() {
    Calendar now = Calendar.create().moveToFirstDayOfMonth();
    assertEquals(1, now.date());
  }

  public void testMoveToLastDayOfMonth() {
    Calendar now = Calendar.create().moveToLastDayOfMonth();
    int expected = Calendar.daysInMonth(now.year(), now.month());
    assertEquals(expected, now.date());
  }

  public void testIsLeapYear() {
    Calendar now = Calendar.create().moveToLastDayOfMonth();
    int expected = Calendar.daysInMonth(now.year(), now.month());
    assertEquals(expected, now.date());
  }

  public void testClone() {
    Calendar now = Calendar.create();
    Calendar cloneOf = now.cloneOf();
    
    assertFalse(now == cloneOf);
    assertTrue(now.equals(cloneOf));
  }
  
  public void testEqualsIgnoreTime(){
    Calendar now = Calendar.create().seconds(10).minutes(1).hours(2);
    Calendar cloneOf = now.cloneOf().seconds(10).minutes(5).hours(1);
    
    assertTrue(now.equalsIgnoreTime(cloneOf));
  }
}