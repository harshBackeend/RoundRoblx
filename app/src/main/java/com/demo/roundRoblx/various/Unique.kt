package com.demo.roundRoblx.various

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.view.View
import android.view.inputmethod.InputMethodManager

object Unique {

//    companion object {

        fun isRoundEmptyString(s: String?): Boolean {
            return s == null || s.trim() == "" || s.isEmpty() || s == "null" || s.length == 0
        }

        fun Context.isRoundConnectingToInternet(): Boolean {
            val connectivityManager =
                getSystemService(Application.CONNECTIVITY_SERVICE) as ConnectivityManager
            val network: Network? = connectivityManager.activeNetwork
            if (network != null) {
                val capabilities = connectivityManager.getNetworkCapabilities(network)
                return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_CELLULAR
                ))
            }
            return false
        }

        fun getRoundDataFromLocal(activity: Context, valueKey: String?): String? {
            val roundDataHolder =
                activity.getSharedPreferences(
                    CachedHolder.CachedKey.round_mainKey,
                    Context.MODE_PRIVATE
                )
            return roundDataHolder.getString(valueKey, "")
        }

        fun setRoundDataHolder(activity: Context, valueKey: String?, value: String?) {
            val roundDataHolder = activity.getSharedPreferences(
                CachedHolder.CachedKey.round_mainKey,
                Context.MODE_PRIVATE
            )
            val roundDataAdd = roundDataHolder.edit()
            roundDataAdd.putString(valueKey, value)
            roundDataAdd.apply()
        }

        fun View.roundDismissKeyboardFrom(activity: Activity) {
            val round_m =
                activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            round_m.hideSoftInputFromWindow(this.windowToken, 0)
        }
//    }


}