package comjava;

import java.io.File;
import java.util.Optional;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import comjava.annotiation.RequestMapping;
import comjava.controller.ProductController;
import comjava.controller.TestServlet;
import comjava.core.CustomHttpServlet;
import jakarta.servlet.http.HttpServlet;

public class Main {
    public static final Optional<String> PORT = Optional.ofNullable(System.getenv("PORT"));
    public static final Optional<String> HOSTNAME = Optional.ofNullable(System.getenv("HOSTNAME"));

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("temp");
        tomcat.setPort(Integer.valueOf(PORT.orElse("8280")));
        tomcat.setHostname(HOSTNAME.orElse("localhost"));

        String contextPath = "/";
        String docBase = new File(".").getAbsolutePath();

        Context context = tomcat.addContext(contextPath, docBase);

        // add TestServlet
        HttpServlet servlet = new TestServlet();
        String servletName = "test";
        String urlPattern = "/test";

        tomcat.addServlet(contextPath, servletName, servlet);
        context.addServletMappingDecoded(urlPattern, servletName);

        // add ProductController
        ProductController productController = new ProductController();
        HttpServlet productServlet = new CustomHttpServlet(productController);
        String productServletName = ProductController.class.getName();
        String productUrl = ProductController.class.getDeclaredAnnotation(RequestMapping.class).value();
        tomcat.addServlet(contextPath, productServletName, productServlet);
        context.addServletMappingDecoded(productUrl, productServletName);
        
        // start server
        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();

    }
    
}
