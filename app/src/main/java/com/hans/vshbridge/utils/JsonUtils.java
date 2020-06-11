package com.hans.vshbridge.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class JsonUtils {
    private static Gson gson = new GsonBuilder()
            .create();

    public static synchronized <T> String toJsonString(Object src, TypeToken<T> typeToken) {
        try {
            return gson.toJson(src, typeToken.getType());
        } catch (Exception e) {
            return null;
        }
    }

    public static synchronized String toJsonString(Object src) {
        try {
            return gson.toJson(src);
        } catch (Exception e) {
            return null;
        }
    }

    public static synchronized <T> T fromJsonString(String jsonString, TypeToken<T> typeToken) {
        try {
            return gson.fromJson(jsonString, typeToken.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static synchronized <T> T fromJsonString(String jsonString, Class<T> objClass) {
        try {
            return gson.fromJson(jsonString, objClass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static synchronized <T> T fromJsonString(String jsonString, Type type) {
        try {
            return gson.fromJson(jsonString, type);
        } catch (Exception e) {
            return null;
        }
    }

    public static synchronized <T> T fromJsonString(JsonElement json, Class<T> classOfT) {
        try {
            return gson.fromJson(json, classOfT);
        } catch (Exception e) {
            return null;
        }
    }

}
