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
import com.demo.roundRoblx.databinding.ActivityTutorialFullBinding
import com.demo.roundRoblx.various.BeginApplication
import com.demo.roundRoblx.various.CachedHolder
import com.demo.roundRoblx.various.HeadLineView
import com.demo.roundRoblx.various.RecyclerAdapter
import com.demo.roundRoblx.various.Unique

class TutorialFullActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialFullBinding
    private var roundTitle: String? = null
    private var roundSubTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTutorialFullBinding.inflate(layoutInflater)
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

        binding.roundAppBar.roundAppTitle.text = resources.getString(R.string.tips_tricks)

        getInfoFromOtherView()
        setInfoInView()

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

    private fun triggerPushEvent(model: RoundStructureData?) {
        binding.apply {
            roundWordShare.setOnClickListener {
                if (!Unique.isRoundEmptyString(roundTitle) && !Unique.isRoundEmptyString(
                        roundSubTitle
                    )
                ) {
                    val roundSendData: Intent = Intent().setAction(Intent.ACTION_SEND)
                    roundSendData.setType("text/plain")
                    roundSendData.putExtra(
                        Intent.EXTRA_TEXT,
                        "${roundTitle}\n${roundSubTitle}"
                    )
                    startActivity(Intent.createChooser(roundSendData, "Share via"))
                }
            }

            roundAppBar.roundPreviousButton.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun definedInfo(first: RoundStructureData) {

        if (first.round_big != null) {
            HeadLineView.headLineView(
                this@TutorialFullActivity,
                first,
                binding.headLineHolder
            )
        } else {
            binding.buttonLayoutHolder.visibility = View.GONE
        }
    }

    private fun setInfoInView() {
        if (!Unique.isRoundEmptyString(roundTitle) && !Unique.isRoundEmptyString(roundSubTitle)) {
            binding.roundWordTitle.text = roundTitle
            binding.roundWordSubTitle.text = roundSubTitle
        }
    }

    private fun getInfoFromOtherView() {
        try {
            if (intent != null) {
                roundTitle = intent.getStringExtra(CachedHolder.CachedKey.round_DISPLAY_NAME)
                roundSubTitle = intent.getStringExtra(CachedHolder.CachedKey.round_DISPLAY_SUB_NAME)
            }
        } catch (e: Exception) {

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
                        this@TutorialFullActivity,
                        first.round_small_2.random().round_main_image?.toUri()
                    )
                }
                finish()
            }

        }
        onBackPressedDispatcher.addCallback(this, back)
    }
}