package com.demo.roundRoblx.Panel

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
import com.demo.roundRoblx.databinding.ActivityCanvasBinding
import com.demo.roundRoblx.various.BeginApplication
import com.demo.roundRoblx.various.CachedHolder
import com.demo.roundRoblx.various.CanvasDialog
import com.demo.roundRoblx.various.HeadLineView
import com.demo.roundRoblx.various.Unique
import `in`.myinnos.androidscratchcard.ScratchCard
import kotlin.random.Random

class CanvasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCanvasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCanvasBinding.inflate(layoutInflater)
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

        binding.roundAppBar.roundAppTitle.text = resources.getString(R.string.scratch_win)

        BeginApplication.roundRowResponse.observe(this) {
            if (it?.first != null && !Unique.isRoundEmptyString(it.first!!.round_status) && it.first!!.round_status == "1") {
                binding.buttonLayoutHolder.visibility = View.VISIBLE
                definedInfo(it.first!!)
            } else {
                binding.buttonLayoutHolder.visibility = View.GONE
            }
            launchEvent(it?.first)
            triggerPushEvent(it?.first)
            triggerBackEvent(it?.first)
        }

        binding.headLineHolder.roundHeadLine.isSelected = true
        binding.headLineHolder.roundSubLine.isSelected = true

    }

    private fun launchEvent(first: RoundStructureData?) {
        binding.apply {
            roundCanvas.visibility = View.VISIBLE
            roundCanvas.setScratchDrawable(
                ContextCompat.getDrawable(this@CanvasActivity, R.drawable.round_scratch_card)
            )

            roundCanvas.setOnScratchListener { scratchCard, visiblePercent ->
                if (visiblePercent > 0.5) {
                    roundCanvas.visibility = View.GONE
                }

                var roundMoney =
                    Unique.getRoundDataFromLocal(
                        this@CanvasActivity,
                        CachedHolder.CachedKey.round_rank
                    )

                val roundMoneyUpdate = Random.nextInt(60, 300)

                CanvasDialog.viewCanvasDialog(
                    this@CanvasActivity,
                    roundMoneyUpdate.toString(),
                    object : CanvasDialog.CanvasDialogEvent {
                        override fun clamEvent() {
                            if (!Unique.isRoundEmptyString(roundMoney)) {
                                val roundMoneyN: Int = roundMoney!!.toInt()
                                roundMoney = "${roundMoneyN + roundMoneyUpdate}"
                            }

                            Unique.setRoundDataHolder(
                                applicationContext,
                                CachedHolder.CachedKey.round_rank,
                                roundMoney
                            )

                            modifyMoney()
                            if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(
                                    first.round_status
                                ) && first.round_status == "1" && first.round_small_2.isNotEmpty()
                            ) {
                                definedInfo(first)
                                BeginApplication.showRoundRowTab(
                                    this@CanvasActivity,
                                    first.round_small_2.random().round_main_image?.toUri()
                                )
                            }

                            finishAffinity()
                        }

                    })
            }
        }
    }

    private fun triggerPushEvent(first: RoundStructureData?) {
        binding.roundAppBar.roundPreviousButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun definedInfo(first: RoundStructureData) {

        if (first.round_big != null) {
            HeadLineView.headLineView(
                this@CanvasActivity,
                first,
                binding.headLineHolder
            )
        } else {
            binding.buttonLayoutHolder.visibility = View.GONE
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
                        this@CanvasActivity,
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
        binding.roundAppBar.roundMoneyBagLayout.visibility = View.VISIBLE
        modifyMoney()
    }

    private fun modifyMoney() {
        val roundMoney =
            Unique.getRoundDataFromLocal(this@CanvasActivity, CachedHolder.CachedKey.round_rank)
        if (!Unique.isRoundEmptyString(roundMoney)) {
            binding.roundAppBar.roundTextMoney.text = roundMoney
        }
    }
}