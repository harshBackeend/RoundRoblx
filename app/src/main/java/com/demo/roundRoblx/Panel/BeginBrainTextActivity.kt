package com.demo.roundRoblx.Panel

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.demo.roundRoblx.R
import com.demo.roundRoblx.assignmentData.RoundStructureData
import com.demo.roundRoblx.databinding.ActivityBeginBrainTextBinding
import com.demo.roundRoblx.various.BeginApplication
import com.demo.roundRoblx.various.CachedHolder
import com.demo.roundRoblx.various.HeadLineView
import com.demo.roundRoblx.various.Unique
import com.demo.roundRoblx.various.Unique.getRoundDataFromLocal
import kotlin.jvm.java

class BeginBrainTextActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBeginBrainTextBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBeginBrainTextBinding.inflate(layoutInflater)
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

        binding.roundAppBar.roundAppTitle.text = resources.getString(R.string.robox_quiz)

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
        binding.headLineHolder.roundHeadLine.isSelected = true


    }

    private fun definedInfo(first: RoundStructureData) {
        if (first.round_big != null) {
            HeadLineView.headLineView(
                this@BeginBrainTextActivity,
                first,
                binding.headLineHolder
            )
        } else {
            binding.buttonLayoutHolder.visibility = View.GONE
        }


    }

    private fun triggerPushEvent(first: RoundStructureData?) {
        binding.apply {
            roundStartNow.setOnClickListener {
                startActivity(Intent(this@BeginBrainTextActivity, BrainTestActivity::class.java))
                if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(first.round_status) && first.round_status == "1") {
                    BeginApplication.showRoundRowTab(
                        this@BeginBrainTextActivity,
                        first.round_small_2.random().round_main_image?.toUri()
                    )
                }
            }

            roundAppBar.roundPreviousButton.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

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
                        this@BeginBrainTextActivity,
                        first.round_small_2.random().round_main_image?.toUri()
                    )
                }
                finish()
            }

        }
        onBackPressedDispatcher.addCallback(this, back)
    }

    override fun onResume() {
        super.onResume()
        modifyMoney()
    }

    private fun modifyMoney() {
        val roundMoney =
            getRoundDataFromLocal(this@BeginBrainTextActivity, CachedHolder.CachedKey.round_rank)
        if (!Unique.isRoundEmptyString(roundMoney)) {
            binding.roundLevelNumberShow.text = resources.getString(R.string._5000, roundMoney)



            try {
                val roundMoneyInt = roundMoney!!.toInt()
                val fillRate = (roundMoneyInt / 5000) * 100
                binding.roundProgressBar.progress = fillRate
                if (fillRate == 0) {
                    binding.roundLevelNumber.text = "1"
                } else if (fillRate > 0 && fillRate <= 10) {
                    binding.roundLevelNumber.text = "2"
                } else if (fillRate > 10 && fillRate <= 20) {
                    binding.roundLevelNumber.text = "3"
                } else if (fillRate > 20 && fillRate <= 30) {
                    binding.roundLevelNumber.text = "4"
                } else if (fillRate > 30 && fillRate <= 40) {
                    binding.roundLevelNumber.text = "5"
                } else if (fillRate > 40 && fillRate <= 50) {
                    binding.roundLevelNumber.text = "6"
                } else if (fillRate > 50 && fillRate <= 60) {
                    binding.roundLevelNumber.text = "7"
                } else if (fillRate > 60 && fillRate <= 70) {
                    binding.roundLevelNumber.text = "8"
                } else if (fillRate > 70 && fillRate <= 80) {
                    binding.roundLevelNumber.text = "9"
                } else if (fillRate > 80 && fillRate <= 90) {
                    binding.roundLevelNumber.text = "10"
                }else if (fillRate > 90 && fillRate <= 100) {
                    binding.roundLevelNumber.text = "11"
                }


            } catch (e: Exception) {

            }
        }
    }
}