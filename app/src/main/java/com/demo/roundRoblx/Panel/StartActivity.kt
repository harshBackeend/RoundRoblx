package com.demo.roundRoblx.Panel

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.demo.roundRoblx.R
import com.demo.roundRoblx.assignmentData.RoundStructureData
import com.demo.roundRoblx.databinding.ActivityStartBinding
import com.demo.roundRoblx.various.BeginApplication
import com.demo.roundRoblx.various.HeadLineView
import com.demo.roundRoblx.various.Unique

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding
    private var roundImageInfo: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityStartBinding.inflate(layoutInflater)
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

        roundShowInfo(roundImageInfo)

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

        binding.headLineHolder.roundSubLine.isSelected = true

    }

    private fun roundShowInfo(roundImageInfo: Int) {
        binding.apply {
            when (roundImageInfo) {
                1 -> {
                    imageRoundStart.setImageDrawable(
                        ContextCompat.getDrawable(this@StartActivity, R.drawable.round_start_1)
                    )
                    roundMainHeadLine.text = getString(R.string.round_start_title_1)
                }

                2 -> {
                    imageRoundStart.setImageDrawable(
                        ContextCompat.getDrawable(this@StartActivity, R.drawable.round_start_2)
                    )
                    roundMainHeadLine.text = getString(R.string.round_start_title_2)
                }

                3 -> {
                    imageRoundStart.setImageDrawable(
                        ContextCompat.getDrawable(this@StartActivity, R.drawable.round_start_3)
                    )
                    roundMainHeadLine.text = getString(R.string.round_start_title_3)
                }

                4 -> {
                    imageRoundStart.setImageDrawable(
                        ContextCompat.getDrawable(this@StartActivity, R.drawable.round_start_4)
                    )
                    roundMainHeadLine.text = getString(R.string.round_start_title_4)
                }
            }
        }
    }

    private fun definedInfo(first: RoundStructureData) {

        if (first.round_big != null) {
            HeadLineView.headLineView(this@StartActivity, first, binding.headLineHolder)
        } else {
            binding.buttonLayoutHolder.visibility = View.GONE
        }
    }

    private fun triggerPushEvent(first: RoundStructureData?) {
        binding.buttonNext.setOnClickListener {
            when (roundImageInfo) {
                1 -> {
                    roundImageInfo = 2
                    if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(first.round_status) && first.round_status == "1") {
                        definedInfo(first)
                        BeginApplication.showRoundRowTab(
                            this@StartActivity,
                            first.round_small_2.random().round_main_image?.toUri()
                        )
                    }
                    roundShowInfo(roundImageInfo)
                    binding.holderScroll.scrollTo(0, 0)
                }
                2 -> {
                    roundImageInfo = 3
                    if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(first.round_status) && first.round_status == "1") {
                        definedInfo(first)
                        BeginApplication.showRoundRowTab(
                            this@StartActivity,
                            first.round_small_2.random().round_main_image?.toUri()
                        )
                    }
                    roundShowInfo(roundImageInfo)
                    binding.holderScroll.scrollTo(0, 0)
                }
                3 -> {
                    if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(first.round_status) && first.round_status == "1") {
                        definedInfo(first)
                        BeginApplication.showRoundRowTab(
                            this@StartActivity,
                            first.round_small_2.random().round_main_image?.toUri()
                        )
                    }
                    roundImageInfo = 4
                    roundShowInfo(roundImageInfo)
                    binding.holderScroll.scrollTo(0, 0)
                }
                else -> {
                    startActivity(Intent(this@StartActivity, WorkSpaceActivity::class.java))
                    if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(first.round_status) && first.round_status == "1") {
                        BeginApplication.showRoundRowTab(
                            this@StartActivity,
                            first.round_small_2.random().round_main_image?.toUri()
                        )
                    }
                    finish()
                }
            }
        }
    }

    private fun triggerBackEvent(first: RoundStructureData?) {
        val back: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (roundImageInfo == 4) {
                    roundImageInfo   = 3
                    roundShowInfo(roundImageInfo )
                    binding.holderScroll.scrollTo(0, 0)
                } else if (roundImageInfo    == 3) {
                    roundImageInfo   = 2
                    roundShowInfo(roundImageInfo )
                    binding.holderScroll.scrollTo(0, 0)
                } else if (roundImageInfo    == 2) {
                    roundImageInfo   = 1
                    roundShowInfo(roundImageInfo )
                    binding.holderScroll.scrollTo(0, 0)
                } else {
                    ExitDialog.viewExitDialog(this@StartActivity, object : ExitDialog.DialogEvent {
                        override fun exit() {
                            finishAffinity()
                        }

                        override fun rate() {
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

                    })
                }
            }

        }

        onBackPressedDispatcher.addCallback(this, back)
    }
}