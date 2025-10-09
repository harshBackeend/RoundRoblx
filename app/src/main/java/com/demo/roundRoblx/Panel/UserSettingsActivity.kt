package com.demo.roundRoblx.Panel

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.demo.roundRoblx.R
import com.demo.roundRoblx.assignmentData.RoundStructureData
import com.demo.roundRoblx.databinding.ActivityUserSettingsBinding
import com.demo.roundRoblx.various.BeginApplication
import com.demo.roundRoblx.various.HeadLineView
import com.demo.roundRoblx.various.Unique

class UserSettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUserSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        // Show the system bars.
        windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars =
            true

        binding.roundAppBar.roundAppTitle.text = resources.getString(R.string.setting)

        BeginApplication.roundRowResponse.observe(this) {
            if (it?.first != null && !Unique.isRoundEmptyString(it.first!!.round_status) && it.first!!.round_status == "1") {
                binding.buttonLayoutHolder.visibility = View.VISIBLE
                definedInfo(it.first!!)
            } else {
                binding.buttonLayoutHolder.visibility = View.GONE
            }
            triggerPushEvent(it?.first)
            triggerBackEvent(it?.first)
        }

        binding.headLineHolder.roundHeadLine.isSelected = true
        binding.headLineHolder.roundSubLine.isSelected = true
    }

    private fun definedInfo(first: RoundStructureData) {

        if (first.round_big != null) {
            HeadLineView.headLineView(
                this@UserSettingsActivity,
                first,
                binding.headLineHolder
            )
        } else {
            binding.buttonLayoutHolder.visibility = View.GONE
        }
    }

    private fun triggerPushEvent(first: RoundStructureData?) {
        binding.layoutRateApp.setOnClickListener {
            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$packageName")
                    )
                )
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
                    )
                )
            }
        }

        binding.layoutShareApp.setOnClickListener {
            val roundIntent = Intent()
            roundIntent.action = "android.intent.action.SEND"
            roundIntent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out the App at: https://play.google.com/store/apps/details?id=$packageName"
            )
            roundIntent.type = "text/plain"
            startActivity(roundIntent)
        }

        binding.layoutPrivacyPolicyApp.setOnClickListener {
            if (first != null && !Unique.isRoundEmptyString(first.round_pcs)) {
                try {
                    val intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse(first.round_pcs))
                    startActivity(intent)
                } catch (e: Exception) {

                }
            } else {
                Toast.makeText(
                    this@UserSettingsActivity,
                    getString(R.string.please_on_internet),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.roundAppBar.roundPreviousButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun triggerBackEvent(first: RoundStructureData?) {
        val back: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(
                        first.round_status
                    ) && first.round_status == "1" && first.round_small_2.isNotEmpty()
                ) {
                    BeginApplication.showRoundRowTab(
                        this@UserSettingsActivity,
                        first.round_small_2.random().round_main_image?.toUri()
                    )
                }
                finish()
            }

        }
        onBackPressedDispatcher.addCallback(this, back)
    }
}