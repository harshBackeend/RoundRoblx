package com.demo.roundRoblx.various

import android.annotation.SuppressLint
import android.app.Application
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.MutableLiveData
import com.demo.roundRoblx.assignmentData.RoundStructureData
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class BeginApplication : Application() {

    private lateinit var roundRowDataBase: FirebaseDatabase

    private lateinit var roundDataBaseReference: DatabaseReference

    companion object {
        var roundRowResponse = MutableLiveData<Pair<RoundStructureData?, Boolean>>()

        fun showRoundRowTab(mContext: Context, uri: Uri?) {
            val roundBuilder = CustomTabsIntent.Builder()
            roundBuilder.setShowTitle(true)
            val roundCustomTabsIntent = roundBuilder.build()

            @SuppressLint("IntentReset")
            val roundBrowserIntent = Intent()
                .setAction(Intent.ACTION_VIEW)
                .addCategory(Intent.CATEGORY_BROWSABLE)
                .setType("text/plain")
                .setData(Uri.fromParts("http", "", null))
            var roundPossibleBrowsers: List<ResolveInfo>
            roundPossibleBrowsers = mContext.packageManager.queryIntentActivities(
                roundBrowserIntent,
                PackageManager.MATCH_DEFAULT_ONLY
            )
            if (roundPossibleBrowsers.isEmpty()) {
                roundPossibleBrowsers = mContext.packageManager.queryIntentActivities(
                    roundBrowserIntent,
                    PackageManager.MATCH_ALL
                )
            }
            try {
                if (roundPossibleBrowsers.isNotEmpty()) {
                    roundCustomTabsIntent.intent.setPackage(roundPossibleBrowsers[0].activityInfo.packageName)
                    roundCustomTabsIntent.launchUrl(mContext, uri!!)
                } else {
                    val roundBrowserIntent2 = Intent(Intent.ACTION_VIEW, uri)
                    mContext.startActivity(roundBrowserIntent2)
                }
            } catch (e: ActivityNotFoundException) {

            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

        roundRowDataBase = FirebaseDatabase.getInstance()
        roundDataBaseReference = roundRowDataBase.getReference("round_row")

    }
}