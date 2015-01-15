package virtuozo.infra;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class CastIterator<T, F> implements Iterator<T> {
  private Iterator<F> iterator;

  public static <T, F> CastIterator<T, F> of(Iterable<F> iterable){
    return new CastIterator<T, F>(iterable.iterator());
  }
  
  public static <T, F> CastIterator<T, F> of(Iterator<F> iterable){
    return new CastIterator<T, F>(iterable);
  }
  
  public CastIterator(Iterator<F> iterator) {
    super();
    this.iterator = iterator;
  }

  @Override
  public boolean hasNext() {
    return iterator.hasNext();
  }

  @Override
  public T next() {
    return (T) this.iterator.next();
  }

  @Override
  public void remove() {
    this.iterator.remove();
  }
}