package example;

import example.cdi.MyApplication;
import example.cdi.MyBean;
import example.cdi.MyRequest;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.StringWriter;
import java.util.Set;

@Path("EndPoint")
public class CdiEndPoint {
  @Inject
  MyApplication myApplication;
  @Inject
  MyRequest myRequest;

  @GET
  @Path("lookup")
  public String lookup() {
    // 自前でCDI管理Beanをルックアップ
    try {
      InitialContext initial = new InitialContext();

      BeanManager bm = (BeanManager) initial.lookup("java:comp/BeanManager");
      Set<Bean<?>> beans = bm.getBeans(MyApplication.class);
      Bean<?> bean = bm.resolve(beans);
      MyApplication ap = (MyApplication) bm.getReference(bean, MyApplication.class, bm.createCreationalContext(bean));
      MyBean myBean = ap.getMyBean();
      return String.format("%s:%s", myBean.getName(), myBean.getCount());
    } catch (Exception ex) {
      return "Error!!:" + ex.getLocalizedMessage();
    }
  }

  @GET
  @Path("request")
  public String request() {
    // @RequestScopedなインスタンスは，インジェクションする場所が違っても
    // 同じオブジェクトが参照される。
    StringWriter w = new StringWriter();
    w.append("myApplication.myBean=" + myApplication.getMyBean());
    w.append("\n<br>\n");
    w.append("myRequest.myBean=" + myRequest.getMyBean());
    w.append("\n<br>\n");
    return w.toString();
  }

}
