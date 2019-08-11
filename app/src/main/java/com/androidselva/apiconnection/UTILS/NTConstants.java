package com.androidselva.apiconnection.UTILS;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by selvamani on 17-06-2019.
 */

public class NTConstants {
    static String TAG = NTConstants.class.getName();

    public static boolean checkAvailability(Context context) {
        try {
            ConnectivityManager cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {
                return true;
            }else{
                String reason = "";
                if (netInfo != null){
                    reason = netInfo.getReason();
                }
                LogTrace.i(TAG, "Network not availale : " + reason);
            }
            NetworkInfo i = cm.getActiveNetworkInfo();
            if (i == null)
                return false;
            if (!i.isConnected())
                return false;
            if (!i.isAvailable())
                return false;
        } catch (Exception e) {
            LogTrace.i(TAG, "Error checking network information");
            e.printStackTrace();
        }
        return false;
    }
}
