package hitz.virtuozo.infra;

import java.util.HashSet;
import java.util.Set;

public class StatusCodeHandler {
  private final Set<Integer> expectedStatuses = new HashSet<Integer>();
  
  public StatusCodeHandler() {
    expectedStatuses.add(200);
    expectedStatuses.add(201);
    expectedStatuses.add(204);
  }
  
  public StatusCodeHandler any(){
    this.expectedStatuses.clear();
    return this;
  }
  
  public boolean isExpected(int status){
    if(this.expectedStatuses.isEmpty()){
      return true;
    }
    
    return this.expectedStatuses.contains(status);
  }
  
  public StatusCodeHandler expect(int... statuses){
    if (statuses == null || (statuses.length == 1 && statuses[0] < 0)) {
      this.any();
      return this;
    }
    
    this.expectedStatuses.clear();
    for (int status : statuses) {
      this.expectedStatuses.add(status);
    }
    
    return this;
  }
}