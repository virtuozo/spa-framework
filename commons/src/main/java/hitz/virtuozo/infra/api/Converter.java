package hitz.virtuozo.infra.api;

public interface Converter<F, T> {
  T convert(F value);
}
