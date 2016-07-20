package example.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ApplicationScoped
public class MyApplication implements Serializable {
  @Inject
  private MyBean myBean;
  
  public MyBean getMyBean() { return myBean; }
}
