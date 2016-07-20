package example.cdi;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;

@RequestScoped
public class MyRequest implements Serializable {
  @Inject
  private MyBean myBean;
  
  public MyBean getMyBean() { return myBean; }
}
