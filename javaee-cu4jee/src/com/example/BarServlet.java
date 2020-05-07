package com.example;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@WebServlet(name = "BarServlet", urlPatterns = "/bar")
public class BarServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger("bar");

    @Resource
    private ManagedExecutorService executorService;

    @PostConstruct
    public void init() {
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Runnable command = new Runnable() {
            @Override
            public void run() {
                ThreadLocal<String> threadLocal = new ThreadLocal<>();
                String label = Optional.ofNullable(threadLocal.get()).orElse("");
                label += ":HELLO";
                log.info(label);
                threadLocal.set(label);
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException ignore) {
                }
                log.info("NEXT");
            }
        };
        executorService.execute(command);
    }

    @PreDestroy
    public void destroy() {
    }

}
