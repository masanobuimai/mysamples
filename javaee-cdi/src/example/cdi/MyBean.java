package example.cdi;

import javax.enterprise.context.RequestScoped;
import java.io.Serializable;


@RequestScoped
public class MyBean implements Serializable {
  private String name = "IMAI";
  private int count = 1;

  public String getName() {
    return name;
  }

  public int getCount() {
    return count++;
  }

}
