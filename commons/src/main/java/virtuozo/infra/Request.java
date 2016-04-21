package virtuozo.infra;

/**
 * @param <R> 
 *  Request implementation class to fluent api
 * @param <D>
 *  Data type handled by this request
 * 
 * This class is a contract to define a request object to send to a rest service
 */
public interface Request<R extends Request<R, D>, D> {
  R data(D data);
  
  R start(int start);
  
  R end(int end);
}
