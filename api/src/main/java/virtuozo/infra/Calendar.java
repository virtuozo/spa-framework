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

package virtuozo.infra;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("deprecation")
public class Calendar {

  private Date wrapped;

  public static Calendar create(){
    return new Calendar();
  }
  
  public static Calendar today() {
    return new Calendar().clearTime();
  }

  public static Calendar of(Date date) {
    if (date == null) {
      return null;
    }

    return new Calendar().time(date.getTime());
  }

  public static Calendar of(long time) {
    return new Calendar().time(time);
  }

  private Calendar() {
    this.wrapped = new java.util.Date();
  }

  private Calendar(Date date) {
    this.wrapped = date;
  }
  
  public boolean after(Calendar calendar) {
    return this.wrapped.after(calendar.wrapped);
  }

  public boolean before(Calendar calendar) {
    return this.wrapped.before(calendar.wrapped);
  }

  public Calendar addMilliseconds(long milliseconds) {
    this.wrapped.setTime(this.wrapped.getTime() + milliseconds);
    return this;
  }

  public Calendar addSeconds(int seconds) {
    this.wrapped.setSeconds(this.wrapped.getSeconds() + seconds);
    return this;
  }

  public Calendar addMinutes(int minutes) {
    this.wrapped.setMinutes(this.wrapped.getMinutes() + minutes);
    return this;
  }

  public Calendar addHours(int hours) {
    this.wrapped.setHours(this.wrapped.getHours() + hours);
    return this;
  }

  public Calendar addDays(int days) {
    this.wrapped.setDate(this.wrapped.getDate() + days);
    return this;
  }

  public Calendar addWeeks(int weeks) {
    return this.addDays(weeks * 7);
  }

  public Calendar addMonths(int months) {
    int date = this.wrapped.getDate();

    this.wrapped.setDate(1);
    this.wrapped.setMonth(this.wrapped.getMonth() + months);
    this.wrapped.setDate(Math.min(date, daysInMonth(this.wrapped.getYear(), this.month())));

    return this;
  }

  public Calendar addYears(int years) {
    this.wrapped.setYear(this.wrapped.getYear() + years);
    return this;
  }

  public Calendar seconds(int seconds) {
    this.wrapped.setSeconds(seconds);
    return this;
  }

  public Calendar minutes(int minutes) {
    this.wrapped.setMinutes(minutes);
    return this;
  }

  public Calendar hours(int hours) {
    this.wrapped.setHours(hours);
    return this;
  }

  public Calendar date(int day) {
    this.wrapped.setDate(day);
    return this;
  }

  public Calendar month(int month) {
    this.wrapped.setMonth(month);

    return this;
  }

  public Calendar year(int year) {
    this.wrapped.setYear(year);
    return this;
  }

  public Calendar clearTime() {
    this.wrapped.setHours(0);
    this.wrapped.setMinutes(0);
    this.wrapped.setSeconds(0);

    return this;
  }

  public Calendar moveToFirstDayOfMonth() {
    this.wrapped.setDate(1);
    return this;
  }

  public Calendar moveToLastDayOfMonth() {
    this.wrapped.setDate(Calendar.daysInMonth(this.wrapped.getYear(), this.month()));
    return this;
  }

  public Calendar time(long time) {
    this.wrapped.setTime(time);
    return this;
  }

  public boolean leapYear() {
    return Calendar.leapYear(this.wrapped.getYear());
  }

  public int date() {
    return this.wrapped.getDate();
  }

  public WeekDay day() {
    return WeekDay.values()[this.wrapped.getDay()];
  }

  public int hours() {
    return this.wrapped.getHours();
  }

  public int minutes() {
    return this.wrapped.getMinutes();
  }

  public Month month() {
    return Month.values()[this.wrapped.getMonth()];
  }

  public int seconds() {
    return this.wrapped.getSeconds();
  }

  public long time() {
    return this.wrapped.getTime();
  }

  public int year() {
    return this.wrapped.getYear();
  }

  public Date toDate() {
    return this.wrapped;
  }

  public String toString(DateFormat format) {
    return format.format(this.wrapped);
  }

  public boolean equalsIgnoreTime(Calendar calendar) {
    return this.date() == calendar.date() && this.month().equals(calendar.month()) && this.year() == calendar.year();
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Calendar)) {
      return false;
    }

    Calendar calendar = (Calendar) obj;

    return super.equals(obj) || this.wrapped.equals(calendar.wrapped);
  }

  @Override
  public String toString() {
    return this.wrapped.toString();
  }
  
  public Calendar cloneOf() {
    return new Calendar().time(this.time());
  }

  public static int daysInMonth(int year, Month month) {
    if (month.equals(Month.FEBRUARY) && Calendar.leapYear(year)) {
      return 29;
    }

    return month.numberOfDays;
  }

  public static boolean leapYear(int year) {
    return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
  }

  public static List<String> monthDayNames(WeekDay startWith) {
    return monthDayNames(new Calendar(), startWith);
  }

  public static List<String> monthDayNames(Calendar date, WeekDay startWith) {
    DateFormat daysOfWeekFormat = DateFormat.ABBR_DAY_OF_WEEK;
    List<String> daysOfWeek = new ArrayList<String>();
    Calendar runner = date.cloneOf();

    while (!runner.day().equals(startWith)) {
      runner.addDays(1);
    }

    for (int i = 1; i <= 7; i++) {
      daysOfWeek.add(daysOfWeekFormat.format(runner.wrapped));
      runner.addDays(1);
    }

    return daysOfWeek;
  }
  
  public static final long MILLIS_PER_SECOND = 1000;
  
  public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
  
  public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;
  
  public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;
  
  public static final long MILLIS_PER_WEEK = 7 * MILLIS_PER_DAY;

  public enum WeekDay {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;
  }

  public enum Month {
    JANUARY(31), FEBRUARY(28), MARCH(31), APRIL(30), MAY(31), JUNE(30), JULY(31), AUGUST(31), SEPTEMBER(30), OCTOBER(31), NOVEMBER(30), DECEMBER(31);

    int numberOfDays;

    private Month(int numberOfDays) {
      this.numberOfDays = numberOfDays;
    }

    int numberOfDays() {
      return this.numberOfDays;
    }
  }
}