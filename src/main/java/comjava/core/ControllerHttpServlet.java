package comjava.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import comjava.annotiation.GetMapping;
import comjava.constant.Constant;
import comjava.util.Util;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ControllerHttpServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private Object controller;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.addHeader(Constant.CONTENT_TYPE, Constant.APPLICATION_JSON);
        PrintWriter writer = response.getWriter();
       
        try {
            Object result = null;
            Method[] methods = controller.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if(method.isAnnotationPresent(GetMapping.class)) {
                    result = method.invoke(controller);
                }
            }

            writer.print(Util.toJsonString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
