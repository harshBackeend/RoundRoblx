package com.demo.roundRoblx.Panel

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.demo.roundRoblx.R
import com.demo.roundRoblx.assignmentData.RoundStructureData
import com.demo.roundRoblx.databinding.ActivityLetGoBinding
import com.demo.roundRoblx.various.BeginApplication
import com.demo.roundRoblx.various.Unique
import kotlin.jvm.java

class LetGoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLetGoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLetGoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)
        // Show the system bars.
        windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = true

        BeginApplication.roundRowResponse.observe(this) {
            if (it?.first != null && !Unique.isRoundEmptyString(it.first!!.round_status) && it.first!!.round_status == "1") {
                roundSetInfo(it.first!!)
            } else {
                binding.biloImage.visibility = View.GONE
            }
            setPushEvent(it?.first)
        }
    }

    private fun roundSetInfo(first: RoundStructureData) {
        if (first.round_small_2 != null) {
            binding.biloImage.setOnClickListener {
                BeginApplication.showRoundRowTab(
                    this@LetGoActivity,
                    first.round_small_2.random().round_main_image?.toUri()
                )
            }
        }

        if (first.round_small != null) {
            binding.biloImage.visibility = View.VISIBLE
            Glide.with(binding.biloImage)
                .load(first.round_small.random().round_main_image)
                .into(binding.biloImage)
        } else {
            binding.biloImage.visibility = View.GONE
        }
    }

    private fun setPushEvent(first: RoundStructureData?) {
        binding.layoutLetStart.setOnClickListener {
            if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(first.round_status) && first.round_status == "1") {
                startActivity(Intent(this@LetGoActivity, StartActivity::class.java))
                BeginApplication.showRoundRowTab(
                    this@LetGoActivity,
                    first.round_small_2.random().round_main_image?.toUri()
                )
            } else {
                startActivity(Intent(this@LetGoActivity, StartActivity::class.java))
            }
        }

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
                    this@LetGoActivity,
                    getString(R.string.please_on_internet),
                    Toast.LENGTH_SHORT
                ).show()
            }

        }


    }
}