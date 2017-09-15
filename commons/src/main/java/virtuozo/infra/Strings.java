package virtuozo.infra;

public class Strings {
  public static String toString(Object[] args){
    StringBuilder msg = new StringBuilder();
    for(Object value : args){
      msg.append(value.toString()).append(",");
    }
    msg.delete(msg.length() - 1, msg.length());
    
    return msg.toString();
  }
  
  public static boolean isEmptyOrNull(String value){
    return value == null || value.isEmpty();
  }
}