package virtuozo.infra;

import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class CastIterable<T, F> implements Iterable<T>, Iterator<T> {
  private final Iterable<F> iterable;
  
  private Iterator<F> iterator;
  
  private TypeCast<T, F> cast = new DefaultCast();

  public CastIterable(Iterable<F> iterable) {
    this.iterable = iterable;
  }
  
  public CastIterable(Iterator<F> iterator) {
    this.iterable = new ArrayList<F>();
    while(iterator.hasNext()){
      ((ArrayList<F>) this.iterable).add(iterator.next());
    }
  }
  
  public CastIterable<T, F> use(TypeCast<T, F> cast){
    this.cast = cast;
    return this;
  }

  @Override
  public Iterator<T> iterator() {
    this.iterator = this.iterable.iterator();
    return this;
  }
  
  @Override
  public boolean hasNext() {
    boolean hasNext = iterator.hasNext();
    if (!hasNext) {
      this.iterator();
    }

    return hasNext;
  }

  @Override
  public T next() {
    return this.cast.castFrom(this.iterator.next());
  }

  @Override
  public void remove() {
    this.iterator.remove();
  }
  
  class DefaultCast implements TypeCast<T, F>{
    @Override
    public T castFrom(F instance) {
      return (T) instance;
    }
  }
  
  public static interface TypeCast<T, F>{
    T castFrom(F instance);
  }
}