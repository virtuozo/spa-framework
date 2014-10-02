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

import hitz.virtuozo.infra.ValidationProcess.ValidationResult.FieldState;
import hitz.virtuozo.infra.api.Validator;
import hitz.virtuozo.ui.api.UIInput;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;

public class ValidationProcess {

  private List<ValidationConstraint<?>> constraints = new ArrayList<ValidationConstraint<?>>();

  private ValidationCallback callback;

  public ValidationProcess onComplete(ValidationCallback callback) {
    this.callback = callback;
    return this;
  }

  public <V> ValidationConstraint<V> constraintFor(UIInput<?, V> input) {
    ValidationConstraint<V> constraint = new ValidationConstraint<V>(input);
    this.add(constraint);

    return constraint;
  }

  public ValidationProcess add(ValidationConstraint<?> constraint) {
    this.constraints.add(constraint);
    return this;
  }

  public boolean validate() {
    return this.validate(Propagation.ALL);
  }

  public boolean validate(Propagation propagation) {
    boolean valid = true;
    ValidationResult result = new ValidationResult();

    for (ValidationConstraint<?> constraint : this.constraints) {
      FieldState field = new FieldState(constraint.validate(), constraint.name);
      result.add(field);

      valid = field.isValid() && valid;
      if (!valid && propagation == Propagation.STOP_AT_ONCE) {
        return false;
      }
    }

    if (!valid && this.callback != null) {
      this.callback.onComplete(result);
    }

    return valid;
  }

  public interface ValidationAction {

    void whenValid();

    void whenInvalid(String message);
  }

  public static class ValidationConstraint<V> {

    private String name;

    private UIInput<?, V> input;

    private final List<Validator<?, V>> validators = new ArrayList<Validator<?, V>>();

    private ValidationAction action;

    public ValidationConstraint(UIInput<?, V> input) {
      this.input = input;
    }

    public ValidationConstraint<V> name(String name) {
      this.name = name;
      return this;
    }

    public ValidationConstraint<V> add(Validator<?, V> validator) {
      this.validators.add(validator);
      return this;
    }

    public ValidationConstraint<V> remove(Validator<?, V> validator) {
      this.validators.remove(validator);
      return this;
    }

    public ValidationConstraint<V> action(ValidationAction action) {
      this.action = action;
      return this;
    }

    public boolean validate() {
      try {
        V value = this.input.value();

        for (Validator<?, V> validator : this.validators) {
          boolean valid = validator.validate(value);
          if (!valid) {
            this.action.whenInvalid(validator.message());
            return false;
          }
        }

        this.action.whenValid();

        return true;
      } catch (RuntimeException e) {
        GWT.log("Error in validation", e);
        return false;
      }
    }
  }

  public static class ValidationResult {

    private List<FieldState> fields = new ArrayList<FieldState>();

    void add(FieldState field) {
      this.fields.add(field);
    }

    public List<FieldState> getFields() {
      return fields;
    }

    public static class FieldState {

      private boolean valid;

      private String name;

      FieldState(boolean valid, String name) {
        super();
        this.valid = valid;
        this.name = name;
      }

      public String getName() {
        return name;
      }

      public boolean isValid() {
        return valid;
      }
    }
  }
  
  public static interface ValidationCallback{
    void onComplete(ValidationResult result);
  }

  public enum Propagation {
    ALL, STOP_AT_ONCE;
  }
}