package com.example;

import org.apache.commons.lang.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@WebServlet(name = "FooServlet", urlPatterns = "/foo")
public class FooServlet extends HttpServlet {
  private static final Logger log = Logger.getLogger("foo");

  private ExecutorService executorService;

  @PostConstruct
  public void init() {
    log.info(">>>> " + StringUtils.class);
    executorService = Executors.newFixedThreadPool(1);
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) {
    executorService.execute(new Runnable() {
      @Override
      public void run() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        String label = Optional.ofNullable(threadLocal.get()).orElse("");
        label += ":hello";
        log.info(label);
        threadLocal.set(label);
        try {
          TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ignore) {
        }
        log.info("next");
      }
    });
  }

  @PreDestroy
  public void destroy() {
    executorService.shutdown();
  }

}
