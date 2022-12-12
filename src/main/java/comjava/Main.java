package comjava;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.ContextConfig;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.StandardRoot;
import comjava.controller.SimpleServlet;
import jakarta.servlet.http.HttpServlet;
import java.io.File;
import java.net.URL;
import java.util.Optional;

public class Main {
    public static final Optional<String> PORT = Optional.ofNullable(System.getenv("PORT"));
    public static final Optional<String> HOSTNAME = Optional.ofNullable(System.getenv("HOSTNAME"));
    
    public static void main( String[] args ) throws LifecycleException
    {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("temp");
        tomcat.setPort(Integer.valueOf(PORT.orElse("8280") ));
        tomcat.setHostname(HOSTNAME.orElse("localhost"));

        String contextPath = "/";
        String docBase = new File(".").getAbsolutePath();

        Context context = tomcat.addContext(contextPath, docBase);

        HttpServlet servlet = new SimpleServlet();
        String servletName = "HelloWorld";
        String urlPattern = "/helloWorld";

        tomcat.addServlet(contextPath, servletName, servlet);      
        context.addServletMappingDecoded(urlPattern, servletName);

        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();

    }  
}