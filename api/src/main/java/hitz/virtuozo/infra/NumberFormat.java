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

import hitz.virtuozo.infra.SimpleValidator;
import hitz.virtuozo.infra.api.Format;

import com.google.gwt.i18n.client.CurrencyList;

public enum NumberFormat implements Format<Number> {
  INTEGER(getInteger()), CURRENCY(com.google.gwt.i18n.client.NumberFormat.getCurrencyFormat()), DECIMAL(com.google.gwt.i18n.client.NumberFormat.getDecimalFormat()), GLOBAL_CURRENCY(
      com.google.gwt.i18n.client.NumberFormat.getGlobalCurrencyFormat()), PERCENT(com.google.gwt.i18n.client.NumberFormat.getPercentFormat()), SIMPLE_PERCENT(new PercentFormat()), SCIENTIFIC(
      com.google.gwt.i18n.client.NumberFormat.getScientificFormat()), SIMPLE_CURRENCY(com.google.gwt.i18n.client.NumberFormat.getSimpleCurrencyFormat());

  private WrappedFormat wrapped;

  private NumberFormat(com.google.gwt.i18n.client.NumberFormat format) {
    this.wrapped = new WrappedFormat(format);
  }

  @Override
  public String format(Number value) {
    return this.wrapped.format(value);
  }

  @Override
  public Number unformat(String value) {
    return this.wrapped.unformat(value);
  }

  @Override
  public String pattern() {
    return this.wrapped.pattern();
  }

  public static Format<Number> custom(String pattern) {
    return new WrappedFormat(com.google.gwt.i18n.client.NumberFormat.getFormat(pattern));
  }

  public static Format<Number> custom(String pattern, int fractionDigits) {
    com.google.gwt.i18n.client.NumberFormat format = com.google.gwt.i18n.client.NumberFormat.getFormat(pattern);
    format.overrideFractionDigits(fractionDigits);

    return new WrappedFormat(format);
  }

  public static Format<Number> custom(String pattern, int minFractionDigits, int maxFractionDigits) {
    com.google.gwt.i18n.client.NumberFormat format = com.google.gwt.i18n.client.NumberFormat.getFormat(pattern);
    format.overrideFractionDigits(minFractionDigits, maxFractionDigits);

    return new WrappedFormat(format);
  }

  static com.google.gwt.i18n.client.NumberFormat getInteger() {
    String pattern = com.google.gwt.i18n.client.NumberFormat.getDecimalFormat().getPattern();
    pattern = pattern.substring(0, pattern.lastIndexOf("."));

    return com.google.gwt.i18n.client.NumberFormat.getFormat(pattern);
  }

  static class WrappedFormat implements Format<Number> {

    private com.google.gwt.i18n.client.NumberFormat wrapped;

    public WrappedFormat(com.google.gwt.i18n.client.NumberFormat wrapped) {
      super();
      this.wrapped = wrapped;
    }

    @Override
    public String format(Number value) {
      return value == null ? null : this.wrapped.format(value);
    }

    @Override
    public Number unformat(String value) {
      return SimpleValidator.isEmptyOrNull(value) ? null : this.wrapped.parse(value);
    }

    @Override
    public String pattern() {
      return this.wrapped.getPattern();
    }
  }

  static class PercentFormat extends com.google.gwt.i18n.client.NumberFormat {

    public PercentFormat() {
      super(defaultNumberConstants.percentPattern(), CurrencyList.get().getDefault(), false);
    }

    @Override
    public String format(double number) {
      return super.format(number / 100d);
    }

    @Override
    public String format(Number number) {
      return this.format(number.doubleValue() / 100d);
    }

    @Override
    public double parse(String text) throws NumberFormatException {

      return super.parse(text) * 100;
    }
  }
}
