package comjava.util;

import com.google.gson.Gson;

public class Util {

    private static Gson gson = new Gson();
   
    public static String toJsonString(Object object) {
        return gson.toJson(object);
    }
}
