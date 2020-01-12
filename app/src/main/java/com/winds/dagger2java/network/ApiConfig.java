package com.winds.dagger2java.network;

public class ApiConfig {

    public static String BASE_URL="https://simplifiedcoding.net";
    //Response cache size for OkHTTP - 10 MB
    public static final long MAX_HTTP_CACHE_SIZE = 10 * 1024 * 1024;
    public static final String POSTS_API = "/demos";



    /**
     * all api specific response codes given from server side
     */
    public interface ResponseCodes {

        int SUCCESS = 200;
        int CONFLICT = 400;
        int AUTH_TOKEN_ERROR = 401;
        int FORBIDDEN = 403;
        int NOT_FOUND = 404;
        int VALIDATION=422;
        int TOO_MANY_REQUEST=429;
        int INTERNAL_SERVER= 500;
    }

    /**
     * network timeouts - in seconds
     */

    public interface Timeouts {
        int CONNECT = 30;
        int READ = 60;
        int WRITE = 60;
    }
}
