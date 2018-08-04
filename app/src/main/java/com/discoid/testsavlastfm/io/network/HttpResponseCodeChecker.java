package com.discoid.testsavlastfm.io.network;

import java.net.HttpURLConnection;

public class HttpResponseCodeChecker {

    public static boolean isHttpOk(Integer code) {
        if (code == null) return false;
        return code == HttpURLConnection.HTTP_OK;
    }

    public static boolean isA20xCode(Integer code) {
        if (code == null) return false;
        return code >= HttpURLConnection.HTTP_OK && code <= HttpURLConnection.HTTP_PARTIAL;
    }

    public static boolean isHttpForbidden(Integer code) {
        if (code == null) return false;
        return code == HttpURLConnection.HTTP_FORBIDDEN;
    }

    public static boolean isHttpNotFound(Integer code) {
        if (code == null) return false;
        return code == HttpURLConnection.HTTP_NOT_FOUND;
    }

    public static boolean isHttpUnauthorized(Integer code) {
        if (code == null) return false;
        return code == HttpURLConnection.HTTP_UNAUTHORIZED;
    }

    public static boolean isHttpBadRequest(Integer code) {
        if (code == null) return false;
        return code == HttpURLConnection.HTTP_BAD_REQUEST;
    }

    public static boolean isHttpServerError(Integer code) {
        return code != null && code >= HttpURLConnection.HTTP_INTERNAL_ERROR;
    }

    public static boolean isAnHttpError(Integer code) {
        // Greater than or equal to 400
        return code != null && code >= HttpURLConnection.HTTP_BAD_REQUEST;
    }

    public static boolean isHttpAConflict(Integer code) {
        return code != null && code >= HttpURLConnection.HTTP_CONFLICT;
    }

    public static boolean isNotModifed(Integer code) {
        return code != null && code == HttpURLConnection.HTTP_NOT_MODIFIED;
    }
}
