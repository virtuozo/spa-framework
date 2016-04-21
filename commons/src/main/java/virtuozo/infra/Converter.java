package virtuozo.infra;

public interface Converter<F, T> {
  T convert(F value);
}
