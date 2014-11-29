package virtuozo.interfaces.api;

import virtuozo.infra.HttpStatusCode;

/**
 * @param <R> 
 *  Response implementation class to fluent api
 * @param <D>
 *  Data type handled by this request
 * 
 * This class is a contract to define a response object returned by a rest service
 */
public interface Response<R extends Response<R, D>, D> {

  <I extends Iterable<D>> I collect();
  
  D get();

  int start();

  int end();

  int total();
  
  HttpStatusCode status();

  String messageCode();
}