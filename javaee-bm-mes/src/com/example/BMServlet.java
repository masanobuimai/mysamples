package com.example;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/bm")
public class BMServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger("bm");

    private ScheduledExecutorService scheduler;
    private Future watchTask;
    private Future controlTask;

    @Override
    @PostConstruct
    public void init() {
        InitialContext ctx = null;
        try {
            ctx = new InitialContext();
            // TomEEの conf/server.properties に以下の設定をしておかないと
            // MESのデフォルトが準備されない
            //   openejb.environment.default=true
            // https://tomee.apache.org/latest/docs/admin/configuration/server.html
            scheduler = (ScheduledExecutorService) ctx.lookup("java:comp/DefaultManagedScheduledExecutorService");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Watch.push();
    }

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (controlTask != null) {
            return;
        }
        log.info("2秒後に制御タスクを起動します。");
        controlTask = scheduler.scheduleAtFixedRate(() -> {
            if (watchTask == null || watchTask != null && watchTask.isDone()) {
                log.info("foo=" + Utils.inject(new Foo()));
                log.info("制御タスクの起動（もしくは再起動）");
                watchTask = scheduler.scheduleWithFixedDelay(() -> {
                    try {
                        log.info("監視タスクを実行します。");
                        while (true) {
                            Optional.ofNullable(Watch.pop())
                                    .ifPresent(v -> System.out.println("Watch.pop() = " + v));
                        }
                    } finally {
                        log.info("予期せぬ理由で監視タスクを終了します。");
                    }
                }, 0, 3, TimeUnit.SECONDS);
            }
        }, 2, 10, TimeUnit.SECONDS);
    }

    @Override
    @PreDestroy
    public void destroy() {
        if (controlTask != null) {
            controlTask.cancel(true);
            log.info("制御タスクを終了しました。");
        }
        if (watchTask != null) {
            try {
                watchTask.get(1, TimeUnit.SECONDS);
                log.info("監視タスクを終了しました。");
            } catch (Exception e) {
                watchTask.cancel(true);
                log.warning("監視タスクを中断しました。");
            }
        }
    }

}
