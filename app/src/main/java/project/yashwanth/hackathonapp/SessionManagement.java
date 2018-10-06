package project.yashwanth.hackathonapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    Context mContext;
    private static final String PREF_NAME = "apppre";
    private static final String KEY_CONTACT_NO = "contact_no";
    private static final String KEY_IS_LOGGED_IN = "isloggedIn";
    private static final String KEY_FCM_TOKEN = "fcm_token";
    private static final String KEY_USER_ID= "user_id";

    public SessionManagement(Context ctx) {
        this.mContext = ctx;
        mSharedPreferences = mContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }
    public boolean setLoginStatus(boolean status) {
        this.mEditor.putBoolean(KEY_IS_LOGGED_IN, status);
        this.mEditor.commit();
        return true;
    }
    public boolean setUserId(int id) {
        this.mEditor.putInt(KEY_USER_ID,id);
        this.mEditor.commit();
        return true;
    }
    public int getUserId() {
        int id=mSharedPreferences.getInt(KEY_USER_ID,-1);
        return id;
    }
    public void setContactNo(String contactNo) {
        this.mEditor.putString(KEY_CONTACT_NO, contactNo);
        this.mEditor.commit();
    }
    public boolean logoutUser() {
        boolean isdone = false;
        this.mEditor.clear();
        this.mEditor.putBoolean(KEY_IS_LOGGED_IN, false);
        this.mEditor.commit();
        isdone = true;
        return isdone;
    }
    public String getContactNo() {
        String contact_no = mSharedPreferences.getString(KEY_CONTACT_NO, "Not Avalible");
        return contact_no;
    }
    public boolean isLoggedIn() {
        boolean r = mSharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
        return r;
    }
    public void save_fcm_token(String token) {
        this.mEditor.putString(KEY_FCM_TOKEN, token);
        this.mEditor.commit();
    }
    public String return_fcm_token() {
        String token = mSharedPreferences.getString(KEY_FCM_TOKEN, "Token not avalible");
        return token;
    }
}
