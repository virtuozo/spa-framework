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

package hitz.virtuozo.infra;

import java.util.Date;

@SuppressWarnings("deprecation")
public class Calendar {

  private Date wrapped;

  private DateFormat format;

  private Calendar() {
    this.wrapped = new java.util.Date();
  }

  private Calendar(Date date) {
    this.wrapped = date;
  }

  public static Calendar as(Date date) {
    if (date == null) {
      return null;
    }

    return new Calendar(date);
  }

  public static Calendar date(Date date) {
    if (date == null) {
      return null;
    }

    return new Calendar().setTime(date.getTime());
  }

  public static Calendar time(long time) {
    return new Calendar().setTime(time);
  }

  public static Calendar clone(Calendar clone) {
    if (clone == null) {
      return null;
    }

    return new Calendar().setTime(clone.getTime());
  }

  public static Calendar now() {
    return new Calendar();
  }

  public static Calendar today() {
    return new Calendar().clearTime();
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
    this.wrapped.setDate(Math.min(date, getDaysInMonth(this.wrapped.getYear(), this.getMonth())));

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

  public Calendar day(int day) {
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

  public Calendar format(DateFormat format) {
    this.format = format;
    return this;
  }

  public Calendar moveToDayOfWeek(int dayOfWeek, int pastOrFuture) {
    int diff = (dayOfWeek - this.wrapped.getDay() + 7 * pastOrFuture) % 7;
    return this.addDays((diff == 0) ? diff += 7 * pastOrFuture : diff);
  }

  public Calendar moveToFirstDayOfMonth() {
    this.wrapped.setDate(1);
    return this;
  }

  public Calendar moveToLastDayOfMonth() {
    this.wrapped.setDate(Calendar.getDaysInMonth(this.wrapped.getYear(), this.getMonth()));
    return this;
  }

  public Calendar moveToMonth(int month, int pastOrFuture) {
    int diff = (month - this.wrapped.getMonth() + 12 * pastOrFuture) % 12;
    return this.addMonths((diff == 0) ? diff += 12 * pastOrFuture : diff);
  }

  public Calendar setTime(long time) {
    this.wrapped.setTime(time);
    return this;
  }

  public boolean isLeapYear() {
    return Calendar.isLeapYear(this.wrapped.getYear());
  }

  public DateFormat getFormat() {
    return this.format;
  }

  public int getDate() {
    return this.wrapped.getDate();
  }

  public WeekDay getDay() {
    return WeekDay.values()[this.wrapped.getDay()];
  }

  public int getHours() {
    return this.wrapped.getHours();
  }

  public int getMinutes() {
    return this.wrapped.getMinutes();
  }

  public Month getMonth() {
    return Month.values()[this.wrapped.getMonth()];
  }

  public int getSeconds() {
    return this.wrapped.getSeconds();
  }

  public long getTime() {
    return this.wrapped.getTime();
  }

  public int getYear() {
    return this.wrapped.getYear();
  }

  public java.util.Date toDate() {
    return this.wrapped;
  }

  public String toString(DateFormat format) {
    return format.format(this.wrapped);
  }

  public boolean equalsIgnoreTime(Date date) {
    return this.getDate() == date.getDate() && this.getMonth().equals(date.getMonth()) && this.getYear() == date.getYear();
  }

  public boolean equalsIgnoreTime(Calendar calendar) {
    return this.getDate() == calendar.getDate() && this.getMonth().equals(calendar.getMonth()) && this.getYear() == calendar.getYear();
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
    if (this.format != null) {
      return this.toString(this.format);
    }

    return this.wrapped.toString();
  }

  public static int getDaysInMonth(int year, Month month) {
    if (month.equals(Month.FEBRUARY) && Calendar.isLeapYear(year)) {
      return 29;
    }

    return month.numberOfDays;
  }

  public static boolean isLeapYear(int year) {
    return ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0);
  }

  public static JSONCollection<String> getMonthDayNames(WeekDay startWith) {
    return getMonthDayNames(new Calendar(), startWith);
  }

  public static JSONCollection<String> getMonthDayNames(Calendar date, WeekDay startWith) {
    DateFormat daysOfWeekFormat = DateFormat.DAY_OF_WEEK;
    JSONCollection<String> daysOfWeek = JSON.asStringCollection();
    Calendar runner = Calendar.clone(date);

    while (!runner.getDay().equals(startWith)) {
      runner.addDays(1);
    }

    for (int i = 1; i <= 7; i++) {
      daysOfWeek.add(daysOfWeekFormat.format(runner.wrapped));
      runner.addDays(1);
    }

    return daysOfWeek;
  }

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