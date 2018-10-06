package NetworkUtility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkStatus {
    private Context mContext;
    private boolean is_connected=false;
    public NetworkStatus(Context ctx) {
        this.mContext = ctx;
    }
    public boolean isConnectedToNetwork(){
        ConnectivityManager mConnectivityManager=(ConnectivityManager)mContext.getSystemService(mContext.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo=mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo!=null){
            if (mNetworkInfo.isConnectedOrConnecting()){
                is_connected=true;
            }
        }
        if (is_connected){
            if (mNetworkInfo!=null){
                if (mNetworkInfo.getType()==ConnectivityManager.TYPE_WIFI){
                    is_connected=true;
                }
                else if(mNetworkInfo.getType()==ConnectivityManager.TYPE_MOBILE){
                    is_connected=false;
                }
                else{
                    is_connected=false;
                }
            }
            else {
                is_connected=false;
            }
        }
        else
        {
            is_connected=false;
        }
        return is_connected;
    }
}
