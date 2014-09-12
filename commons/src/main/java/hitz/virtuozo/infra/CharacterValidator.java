package hitz.virtuozo.infra;

import hitz.virtuozo.infra.api.Validator;

public class CharacterValidator extends Validator<CharacterValidator, String> {

  private String charList;

  private Permission permission;

  public CharacterValidator(String charList, Permission permission) {
    this.charList = charList;
    this.permission = permission;
  }

  @Override
  protected boolean delegateValidation(String value) {
    return this.permission.validate(this.charList, value);
  }

  public enum Permission {
    ALLOWED {

      @Override
      boolean validate(char c, String value) {
        return value.indexOf(c) > -1;
      }
    },
    FORBIDDEN {

      boolean validate(char c, String value) {
        return value.indexOf(c) == -1;
      }
    };

    boolean validate(String list, CharSequence value) {
      char[] sequence = list.toCharArray();
      String sValue = value.toString();

      boolean valid = true;

      for (char c : sequence) {
        valid = valid && this.validate(c, sValue);
      }

      return valid;
    }

    abstract boolean validate(char c, String value);
  }
}