package com.cschua.myapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;

public class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();

    public static boolean isConnectedToInternet(final Context context) {
        if (context == null) {
            Log.d(TAG, "isConnectedToInternet is false because context is null");
            return false;
        }

        ConnectivityManager cm =
            (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm == null) {
            Log.d(TAG, "isConnectedToInternet is false because ConnectivityManager is null");
            return false;
        }

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());

            return capabilities != null &&
                (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET));

        } else {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        }
    }
}
