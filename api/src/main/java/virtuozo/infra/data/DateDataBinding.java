package virtuozo.infra.data;

import java.util.Date;

import virtuozo.infra.DateFormat;

public interface DateDataBinding extends DataBinding<Date> {
  Date get(DateFormat format);
}