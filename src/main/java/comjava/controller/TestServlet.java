package comjava.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import comjava.constant.Constant;
import comjava.entity.Product;
import comjava.util.Util;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class TestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.addHeader(Constant.CONTENT_TYPE, Constant.APPLICATION_JSON);
        PrintWriter writer = response.getWriter();
        
        // get body
        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        
        Product product = new Product(1, "product name");
        writer.print(Util.toJsonString(product));
    }
}
