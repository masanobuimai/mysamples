package src.com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "UploadServlet", urlPatterns = "/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger("upload");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("file");
        String name = this.getFileName(part);
        String submittedFileName = part.getSubmittedFileName();
        log.info("name = " + name);
        log.info("submittedFileName = " + submittedFileName);
        log.info("----------");
        response.sendRedirect("index.jsp");
    }

    private String getFileName(Part part) {
        String name = null;
        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
            if (dispotion.trim().startsWith("filename")) {
                log.info(">" + dispotion.trim() + "<");
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                name = name.substring(name.lastIndexOf("\\") + 1);
                break;
            }
        }
        return name;
    }
}
