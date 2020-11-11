package com.example;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.naming.InitialContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@WebServlet(name = "BooServlet", urlPatterns = "/boo")
public class BooServlet extends HttpServlet {
  private static final Logger log = Logger.getLogger("boo");

  private ManagedExecutorService executorService;

  @PostConstruct
  public void init() {
    InitialContext ctx = null;
    try {
      ctx = new InitialContext();
      // TomEEの conf/server.properties に以下の設定をしておかないと
      // MESのデフォルトが準備されない
      //   openejb.environment.default=true
      // https://tomee.apache.org/latest/docs/admin/configuration/server.html
      executorService = (ManagedExecutorService) ctx.lookup("java:comp/DefaultManagedExecutorService");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) {
    executorService.execute(() -> {
      ThreadLocal<String> threadLocal = new ThreadLocal<>();
      String label = Optional.ofNullable(threadLocal.get()).orElse("");
      label += ":こんにちは";
      log.info(label);
      threadLocal.set(label);
      try {
        TimeUnit.SECONDS.sleep(5);
      } catch (InterruptedException ignore) {
      }
      log.info("つぎ");
    });
  }

  @PreDestroy
  public void destroy() {
  }

}
