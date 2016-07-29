package utils;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by Brendan on 7/24/2016.
 */
public class JSONUtils {
    public static String toJSON(Object obj) {
        return new Gson().toJson(obj);
    }

    public static ResponseTransformer JSON() {
        return JSONUtils::toJSON;
    }
}
