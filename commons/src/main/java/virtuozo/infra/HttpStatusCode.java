package virtuozo.infra;

public enum HttpStatusCode{
    ACCEPTED(202),
    BAD_REQUEST(400),
    CONFLICT (409),
    CREATED(201),
    FORBIDDEN (403),
    GONE (410),
    INTERNAL_SERVER_ERROR(500),
    MOVED_PERMANENTLY(303),
    NO_CONTENT (204),
    NOT_ACCEPTABLE (406),
    NOT_FOUND (404),
    NOT_MODIFIED(304),
    OK (200),
    PAGINATION(206),
    PRECONDITION_FAILED(412),
    SEE_OTHER(303),
    SERVICE_UNAVAILABLE(503),
    TEMPORARY_REDIRECT(307),
    TIMEOUT(408),
    UNAUTHORIZED (401),
    NOT_MAPPED(-1),
    UNSUPPORTED_MEDIA_TYPE(415);
    
    private int code;

    private HttpStatusCode(int code) {
      this.code = code;
    }
    
    public int code() {
      return code;
    }
    
    public static HttpStatusCode valueOf(int code){
      for(HttpStatusCode status : values()){
        if(status.code == code){
          return status;
        }
      }
      
      return HttpStatusCode.NOT_MAPPED;
    }
  }