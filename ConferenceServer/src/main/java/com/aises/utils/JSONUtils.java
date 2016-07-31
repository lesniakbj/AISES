package com.aises.utils;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by Brendan on 7/24/2016.
 *
 * This file has been created to work with the
 * AISES Conference Server. Used for converting
 * responses and requests to and from objects.
 */
public class JSONUtils {
    public static String toJSON(Object obj) {
        return new Gson().toJson(obj);
    }

    public static Object fromJSON(String json, Class clazz) {
        return new Gson().fromJson(json, clazz);
    }

    public static ResponseTransformer JSON() {
        return JSONUtils::toJSON;
    }
}
