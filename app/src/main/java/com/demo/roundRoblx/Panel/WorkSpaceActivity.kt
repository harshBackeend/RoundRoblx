package com.demo.roundRoblx.Panel

import android.R.attr.visibility
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
import com.demo.roundRoblx.databinding.ActivityWorkSpaceBinding
import com.demo.roundRoblx.various.BeginApplication
import com.demo.roundRoblx.various.CachedHolder
import com.demo.roundRoblx.various.CloseDialog
import com.demo.roundRoblx.various.HeadLineView
import com.demo.roundRoblx.various.Unique
import kotlin.jvm.java

class WorkSpaceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWorkSpaceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWorkSpaceBinding.inflate(layoutInflater)
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

        BeginApplication.roundRowResponse.observe(this) {
            if (it?.first != null && !Unique.isRoundEmptyString(it.first!!.round_status) && it.first!!.round_status == "1") {
                binding.roundFirestLayoutHolder.visibility = View.VISIBLE
                binding.roundSecundLayoutHolder.visibility = View.VISIBLE
                definedInfo(it.first!!)
            } else {
                binding.roundFirestLayoutHolder.visibility = View.GONE
                binding.roundSecundLayoutHolder.visibility = View.GONE
            }
            triggerPushEvent(it?.first)
            triggerBackEvent(it?.first)
        }

        binding.roundFirestHeadLineHolder.roundSubLine.isSelected = true
        binding.roundFirestHeadLineHolder.roundSubLine.isSelected = true
        binding.roundFirestHeadLineHolder.roundHeadLine.isSelected = true
        binding.roundSecundHeadLineHolder.roundHeadLine.isSelected = true

    }

    private fun definedInfo(first: RoundStructureData) {

        if (first.round_big != null) {
            HeadLineView.headLineView(
                this@WorkSpaceActivity,
                first,
                binding.roundFirestHeadLineHolder
            )
            HeadLineView.headLineView(
                this@WorkSpaceActivity,
                first,
                binding.roundSecundHeadLineHolder
            )
        } else {
            binding.roundFirestLayoutHolder.visibility = View.GONE
            binding.roundSecundLayoutHolder.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        val roundMoney =
            Unique.getRoundDataFromLocal(this@WorkSpaceActivity, CachedHolder.CachedKey.round_rank)
        if (!Unique.isRoundEmptyString(roundMoney)) {
            binding.roundTextMoney.text = roundMoney
        }
    }

    private fun triggerPushEvent(first: RoundStructureData?) {
        binding.apply {
            binding.roundRBXTODollar.setOnClickListener {
                startActivity(Intent(this@WorkSpaceActivity, ExchangeActivity::class.java).apply {
                    putExtra(
                        CachedHolder.CachedKey.round_DISPLAY_NAME,
                        CachedHolder.HandleKey.rbx_round_do
                    )
                })
                if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(first.round_status) && first.round_status == "1") {
                    BeginApplication.showRoundRowTab(
                        this@WorkSpaceActivity,
                        first.round_small_2.random().round_main_image?.toUri()
                    )
                }
            }

            binding.roundDollarToRBX.setOnClickListener {
                startActivity(Intent(this@WorkSpaceActivity, ExchangeActivity::class.java).apply {
                    putExtra(
                        CachedHolder.CachedKey.round_DISPLAY_NAME,
                        CachedHolder.HandleKey.do_round_rbx
                    )
                })
                if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(first.round_status) && first.round_status == "1") {
                    BeginApplication.showRoundRowTab(
                        this@WorkSpaceActivity,
                        first.round_small_2.random().round_main_image?.toUri()
                    )
                }
            }

            binding.roundDollarToRBX.setOnClickListener {
                startActivity(Intent(this@WorkSpaceActivity, ExchangeActivity::class.java).apply {
                    putExtra(
                        CachedHolder.CachedKey.round_DISPLAY_NAME,
                        CachedHolder.HandleKey.do_round_rbx
                    )
                })
                if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(first.round_status) && first.round_status == "1") {
                    BeginApplication.showRoundRowTab(
                        this@WorkSpaceActivity,
                        first.round_small_2.random().round_main_image?.toUri()
                    )
                }
            }

            binding.roundScratch.setOnClickListener {
                startActivity(Intent(this@WorkSpaceActivity, CanvasActivity::class.java))
                if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(first.round_status) && first.round_status == "1") {
                    BeginApplication.showRoundRowTab(
                        this@WorkSpaceActivity,
                        first.round_small_2.random().round_main_image?.toUri()
                    )
                }
            }

            binding.roundBCToRBX.setOnClickListener {
                startActivity(Intent(this@WorkSpaceActivity, ExchangeActivity::class.java).apply {
                    putExtra(
                        CachedHolder.CachedKey.round_DISPLAY_NAME,
                        CachedHolder.HandleKey.bc_round
                    )
                })
                if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(first.round_status) && first.round_status == "1") {
                    BeginApplication.showRoundRowTab(
                        this@WorkSpaceActivity,
                        first.round_small_2.random().round_main_image?.toUri()
                    )
                }
            }

            binding.roundTBCToRBX.setOnClickListener {
                startActivity(Intent(this@WorkSpaceActivity, ExchangeActivity::class.java).apply {
                    putExtra(
                        CachedHolder.CachedKey.round_DISPLAY_NAME,
                        CachedHolder.HandleKey.tbc_round
                    )
                })
                if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(first.round_status) && first.round_status == "1") {
                    BeginApplication.showRoundRowTab(
                        this@WorkSpaceActivity,
                        first.round_small_2.random().round_main_image?.toUri()
                    )
                }
            }

            binding.roundOBCToRBX.setOnClickListener {
                startActivity(Intent(this@WorkSpaceActivity, ExchangeActivity::class.java).apply {
                    putExtra(
                        CachedHolder.CachedKey.round_DISPLAY_NAME,
                        CachedHolder.HandleKey.obx_round
                    )
                })
                if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(first.round_status) && first.round_status == "1") {
                    BeginApplication.showRoundRowTab(
                        this@WorkSpaceActivity,
                        first.round_small_2.random().round_main_image?.toUri()
                    )
                }
            }

            binding.roundQuiz.setOnClickListener {
                startActivity(Intent(this@WorkSpaceActivity, BeginBrainTextActivity::class.java))
                if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(first.round_status) && first.round_status == "1") {
                    BeginApplication.showRoundRowTab(
                        this@WorkSpaceActivity,
                        first.round_small_2.random().round_main_image?.toUri()
                    )
                }
            }

            binding.roundSpinWheels.setOnClickListener {
                startActivity(Intent(this@WorkSpaceActivity, LuckySpinActivity::class.java))
                if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(first.round_status) && first.round_status == "1") {
                    BeginApplication.showRoundRowTab(
                        this@WorkSpaceActivity,
                        first.round_small_2.random().round_main_image?.toUri()
                    )
                }
            }

            binding.roundMeme.setOnClickListener {
                startActivity(Intent(this@WorkSpaceActivity, FunnyActivity::class.java))
                if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(first.round_status) && first.round_status == "1") {
                    BeginApplication.showRoundRowTab(
                        this@WorkSpaceActivity,
                        first.round_small_2.random().round_main_image?.toUri()
                    )
                }
            }

            binding.roundTipsTricks.setOnClickListener {
                startActivity(Intent(this@WorkSpaceActivity, TutorialActivity::class.java))
                if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(first.round_status) && first.round_status == "1") {
                    BeginApplication.showRoundRowTab(
                        this@WorkSpaceActivity,
                        first.round_small_2.random().round_main_image?.toUri()
                    )
                }
            }

            binding.roundDictionary.setOnClickListener {
                startActivity(Intent(this@WorkSpaceActivity, WordListActivity::class.java))
                if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(first.round_status) && first.round_status == "1") {
                    BeginApplication.showRoundRowTab(
                        this@WorkSpaceActivity,
                        first.round_small_2.random().round_main_image?.toUri()
                    )
                }
            }

            binding.roundSetting.setOnClickListener {
                startActivity(Intent(this@WorkSpaceActivity, UserSettingsActivity::class.java))
                if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(first.round_status) && first.round_status == "1") {
                    BeginApplication.showRoundRowTab(
                        this@WorkSpaceActivity,
                        first.round_small_2.random().round_main_image?.toUri()
                    )
                }
            }

        }
    }

    private fun triggerBackEvent(first: RoundStructureData?) {
        val back: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                CloseDialog.viewCloseDialog(
                    this@WorkSpaceActivity,
                    object : CloseDialog.CloseDialogEvent {

                        override fun yesEvent() {
                            finishAffinity()
                        }

                        override fun noEvent() {

                        }

                    })
            }

        }
        onBackPressedDispatcher.addCallback(this, back)
    }


}