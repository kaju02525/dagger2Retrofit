package com.winds.dagger2java.network;

public class Const {

    public static String APP_VERSION="";
    public static String DEVICE_OS="";
    public static String DEVICE_Build="";



    /**
     * all keys for shared preferences must be put here
     */
    public interface SharedPrefs {
        String KEY_TIMESTAMP = "key_timestamp";
        String KEY_DUMMY_OBJECT = "key_dummy_object";
        String KEY_USER_DATA = "key_user_data";
        String KEY_MAINLIST_DATA = "key_mainlist_data";
        String KEY_DEVICE_TOKEN = "key_device_token";
        String KEY_IS_LOGIN = "isLogin";
    }

    /**
     * all keys for bundle/intent extras must be put here
     */
    public interface BundleExtras {
        String DIALOG_TITLE = "dialog_title";
        String ASSET_FILE_PATH = "asset_file_path";
    }

    /**
     * all date time formats must be put here
     */
    public interface DateTimeFormats {
        String COMMON_DATE_TIME_FORMAT = "dd-MM-yyyy hh:mm:ss a";
        String WINDS_COMMON_DATE_TIME_FORMAT = "yyyy-dd-MM hh:mm:ss";
        String DEMO_FORMAT = "dd-MMM-yyyy hh:mm a";
        String BIRTH_DATE_FORMAT = "MMM dd, yyyy";
        String PARTNER_DATE_FORMAT = "dd MMM, yyyy";
        String PARTNER_DATE_INPUT_FORMAT = "MMM dd,yyyy hh:mm a";
    }

    /**
     * all time delays must be put here
     */
    public interface Delays {
        long MIN_TIME_BETWEEN_CLICKS = 200;
        //NEVER DO THIS in actual app, it is just for sample!
        long ARTIFICIAL_LOADER_DELAY = 2000;

        long NORMAL_LOCATION_UPDATE_INTERVAL = 10000;
        long FASTEST_LOCATION_UPDATE_INTERVAL = 8000;
    }

    public static  boolean WALLET_REDIRECT=false;
    public static  boolean BOOKMARK_REDIRECT=false;
    public static  boolean ORDER_REDIRECT=false;
    public static  boolean REFER_REDIRECT=false;
    public static  boolean GENERAL_REDIRECT=false;
    public static  boolean REWARD_REDIRECT=false;
    public static  boolean PROFILE_REDIRECT=false;

}