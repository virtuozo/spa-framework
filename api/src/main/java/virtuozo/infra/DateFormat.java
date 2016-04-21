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

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;

public enum DateFormat implements Format<Date> {
  ABBR_DAY_OF_WEEK(DateTimeFormat.getFormat("ccccc")),
  DATE_FULL(DateTimeFormat.getFormat(PredefinedFormat.DATE_FULL)), 
  DATE_LONG(DateTimeFormat.getFormat(PredefinedFormat.DATE_LONG)), 
  DATE_MEDIUM(DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM)), 
  DATE_SHORT(DateTimeFormat.getFormat(PredefinedFormat.DATE_SHORT)),
  DATE_TIME_FULL(DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_FULL)), 
  DATE_TIME_LONG(DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_LONG)), 
  DATE_TIME_MEDIUM(DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_MEDIUM)), 
  DATE_TIME_SHORT(DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_SHORT)),
  DAY(DateTimeFormat.getFormat(PredefinedFormat.DAY)), 
  HOUR24_MINUTE(DateTimeFormat.getFormat(PredefinedFormat.HOUR24_MINUTE)), 
  HOUR24_MINUTE_SECOND(DateTimeFormat.getFormat(PredefinedFormat.HOUR24_MINUTE_SECOND)),
  MINUTE_SECOND(DateTimeFormat.getFormat(PredefinedFormat.MINUTE_SECOND)),
  MONTH(DateTimeFormat.getFormat(PredefinedFormat.MONTH)), 
  MONTH_ABBR(DateTimeFormat.getFormat(PredefinedFormat.MONTH_ABBR)), 
  MONTH_ABBR_DAY(DateTimeFormat.getFormat(PredefinedFormat.MONTH_ABBR_DAY)), 
  MONTH_DAY(DateTimeFormat.getFormat(PredefinedFormat.MONTH_DAY)), 
  MONTH_NUM_DAY(DateTimeFormat.getFormat(PredefinedFormat.MONTH_NUM_DAY)), 
  MONTH_WEEKDAY_DAY(DateTimeFormat.getFormat(PredefinedFormat.MONTH_WEEKDAY_DAY)),
  TIME_FULL(DateTimeFormat.getFormat(PredefinedFormat.TIME_FULL)), 
  TIME_LONG(DateTimeFormat.getFormat(PredefinedFormat.TIME_LONG)), 
  TIME_MEDIUM(DateTimeFormat.getFormat(PredefinedFormat.TIME_MEDIUM)), 
  TIME_SHORT(DateTimeFormat.getFormat(PredefinedFormat.TIME_SHORT)),
  YEAR(DateTimeFormat.getFormat(PredefinedFormat.YEAR)), 
  YEAR_MONTH(DateTimeFormat.getFormat(PredefinedFormat.YEAR_MONTH)), 
  YEAR_MONTH_ABBR(DateTimeFormat.getFormat(PredefinedFormat.YEAR_MONTH_ABBR)), 
  YEAR_MONTH_ABBR_DAY(DateTimeFormat.getFormat(PredefinedFormat.YEAR_MONTH_ABBR_DAY)), 
  YEAR_MONTH_DAY(DateTimeFormat.getFormat(PredefinedFormat.YEAR_MONTH_DAY)), 
  YEAR_MONTH_NUM(DateTimeFormat.getFormat(PredefinedFormat.YEAR_MONTH_NUM)), 
  YEAR_MONTH_NUM_DAY(DateTimeFormat.getFormat(PredefinedFormat.YEAR_MONTH_NUM_DAY)), 
  YEAR_MONTH_WEEKDAY_DAY(DateTimeFormat.getFormat(PredefinedFormat.YEAR_MONTH_WEEKDAY_DAY)), 
  YEAR_QUARTER(DateTimeFormat.getFormat(PredefinedFormat.YEAR_QUARTER)), 
  YEAR_QUARTER_ABBR(DateTimeFormat.getFormat(PredefinedFormat.YEAR_QUARTER_ABBR)),
  ISO_8601(DateTimeFormat.getFormat(PredefinedFormat.ISO_8601));

  private final DateTimeFormat wrapped;

  private DateFormat(DateTimeFormat wrapped) {
    this.wrapped = wrapped;
  }

  public Date unformat(String value) {
    return SimpleValidator.isEmptyOrNull(value) ? null : this.wrapped.parseStrict(value);
  }

  public String format(Date value) {
    return value == null ? null : this.wrapped.format(value);
  }

  public String pattern() {
    return this.wrapped.getPattern();
  }
}