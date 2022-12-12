package comjava.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import comjava.constant.Constant;
import comjava.util.Util;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomHttpServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private Object controller;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.addHeader(Constant.CONTENT_TYPE, Constant.APPLICATION_JSON);
        PrintWriter writer = response.getWriter();
        
//        Method[] methods = controller.getClass().getDeclaredMethods();
//        for (Method method : methods) {
//            
//        }
        
       
        try {
            Method method = controller.getClass().getDeclaredMethod("getProductDetail");
            Object object = method.invoke(controller);
            writer.print(Util.toJsonString(object));
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
