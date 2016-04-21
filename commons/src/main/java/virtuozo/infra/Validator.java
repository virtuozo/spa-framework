package virtuozo.infra;

@SuppressWarnings("unchecked")
public abstract class Validator<V extends Validator<V, T>, T> {

  private boolean nullable = true;

  public V nullable(boolean nullable) {
    this.nullable = nullable;
    return (V) this;
  }

  public boolean validate(T value) {
    if (value == null) {
      return nullable;
    }

    try {
      return this.delegateValidation(value);
    } catch (RuntimeException e) {
      return false;
    }
  }

  protected abstract boolean delegateValidation(T value);
}