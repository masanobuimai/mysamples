package com.example;

import javax.annotation.PostConstruct;
import javax.naming.InitialContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.logging.Logger;

@WebServlet(name = "DataSourceServlet", urlPatterns = "/ds")
public class DataSourceServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger("foo");

    @PostConstruct
    public void init() {
        try {
            InitialContext ctx = new InitialContext();
            /*
            NamingEnumeration<NameClassPair> list = ctx.list("java:openejb/Resource");
            while (list.hasMoreElements()) {
                NameClassPair pair = list.nextElement();
                log.info("java:openejb/Resource/ -> " + pair.getName());
            }
             */
            DataSource dataSource = (DataSource) ctx.lookup("java:app/jdbc/postds");
            log.info("ds=" + dataSource);
            Connection con = dataSource.getConnection();
            DatabaseMetaData metaData = con.getMetaData();
            log.info("meta=" + metaData.getDatabaseProductName());
            log.info("     " + metaData.getDatabaseProductVersion());
            log.info("     " + metaData.getDriverName());
            log.info("     " + metaData.getURL());
            log.info("     " + metaData.getUserName());
            ResultSet rs = con.createStatement().executeQuery("select * from airlines");
            while (rs.next()) {
                log.info(">>" + rs.getString("airline_full"));
            }
            rs.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

