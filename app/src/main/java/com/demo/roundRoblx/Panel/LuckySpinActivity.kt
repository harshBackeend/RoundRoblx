package com.demo.roundRoblx.Panel

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
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
import com.demo.roundRoblx.databinding.ActivityLuckySpinBinding
import com.demo.roundRoblx.various.BeginApplication
import com.demo.roundRoblx.various.CachedHolder
import com.demo.roundRoblx.various.HeadLineView
import com.demo.roundRoblx.various.Unique
import com.demo.roundRoblx.various.Unique.getRoundDataFromLocal

class LuckySpinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLuckySpinBinding
    private val roundPart: Array<Int> = arrayOf(100, 200, 300, 400, 500, 600, 700, 800)
    private val roundPartSize: IntArray = IntArray(roundPart.size)
    private val roundRandom = java.util.Random()
    private var roundPartAgel: Int = 0
    private var roundIsRunning = false

    private val roundMiniSecond = 24 * 60 * 60 * 1000L
    private var roundCurrentDaySpinCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLuckySpinBinding.inflate(layoutInflater)
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

        binding.roundAppBar.roundAppTitle.text = resources.getString(R.string.spin_wheels)

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

        applySpinCount()
        fetchAgal()

    }

    private fun fetchAgal() {
        val roundMoneyAgal = 360 / roundPart.size

        for (i in roundPart.indices) {
            roundPartSize[i] = (i + 1) * roundMoneyAgal
        }
    }

    private fun applySpinCount() {
        val internalData =
            getSharedPreferences(CachedHolder.CachedKey.round_mainKey, MODE_PRIVATE)
        val isTodayOpen = internalData.getLong(CachedHolder.CachedKey.round_today_click, 0L)
        val nowTime = System.currentTimeMillis()
        val todayWheel =
            getRoundDataFromLocal(applicationContext, CachedHolder.CachedKey.round_count)

        roundCurrentDaySpinCount = try {
            todayWheel!!.toInt()
        } catch (e: Exception) {
            0
        }

        if (nowTime - isTodayOpen >= roundMiniSecond) {
            roundCurrentDaySpinCount = 9
            Unique.setRoundDataHolder(
                applicationContext,
                CachedHolder.CachedKey.round_count,
                roundCurrentDaySpinCount.toString()
            )
            binding.roundNoCostSpin.text =
                getString(R.string.round_free_spin, roundCurrentDaySpinCount.toString())
            internalData.edit().putLong(CachedHolder.CachedKey.round_today_click, nowTime).apply()
        } else {
            binding.roundNoCostSpin.text =
                getString(R.string.round_free_spin, roundCurrentDaySpinCount.toString())
        }

    }

    private fun triggerPushEvent(first: RoundStructureData?) {
        binding.roundAppBar.roundPreviousButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.buttonSpinNow.setOnClickListener {
            if (!roundIsRunning && roundCurrentDaySpinCount > 0) {
                roundCurrentDaySpinCount--
                binding.roundNoCostSpin.text = resources.getString(
                    R.string.round_free_spin,
                    roundCurrentDaySpinCount.toString()
                )
                val internalData =
                    getSharedPreferences(CachedHolder.CachedKey.round_mainKey, MODE_PRIVATE)
                val nowTime = System.currentTimeMillis()
                internalData.edit().putLong(CachedHolder.CachedKey.round_today_click, nowTime)
                    .apply()
                Unique.setRoundDataHolder(
                    this@LuckySpinActivity,
                    CachedHolder.CachedKey.round_count,
                    roundCurrentDaySpinCount.toString()
                )
                processing(first)
                roundIsRunning = true

            } else {
                Toast.makeText(
                    this@LuckySpinActivity,
                    resources.getString(R.string.round_please_spin_tomorrow),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun processing(first: RoundStructureData?) {
        roundPartAgel = roundRandom.nextInt(roundPart.size)
        val rippleEffect = RotateAnimation(
            0f,
            ((360 * roundPart.size) + roundPartSize[roundPartAgel]).toFloat(),
            RotateAnimation.RELATIVE_TO_SELF,
            0.5f,
            RotateAnimation.RELATIVE_TO_SELF,
            0.5f
        )
        rippleEffect.duration = 4000
        rippleEffect.fillAfter = true
        rippleEffect.interpolator = DecelerateInterpolator()
        rippleEffect.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(p0: Animation?) {
                val roundMoney = getRoundDataFromLocal(
                    this@LuckySpinActivity,
                    CachedHolder.CachedKey.round_rank
                )

                if (!Unique.isRoundEmptyString(roundMoney)) {
                    var roundMoneyU: Int = roundMoney!!.toInt()
                    roundMoneyU = roundMoneyU + roundPart[roundPart.size - (roundPartAgel + 1)]
                    Unique.setRoundDataHolder(
                        this@LuckySpinActivity,
                        CachedHolder.CachedKey.round_rank,
                        roundMoneyU.toString()
                    )
                    if (roundPart[roundPart.size - (roundPartAgel + 1)] > 0) {
                        Toast.makeText(
                            this@LuckySpinActivity,
                            getString(
                                R.string.round_added,
                                roundPart[roundPart.size - (roundPartAgel + 1)].toString()
                            ),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@LuckySpinActivity,
                            getString(R.string.round_lucky_again),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(
                            first.round_status
                        ) && first.round_status == "1"
                    ) {
                        BeginApplication.showRoundRowTab(
                            this@LuckySpinActivity,
                            first.round_small_2.random().round_main_image?.toUri()
                        )
                    }
                    modifyMoney()
                }
                roundIsRunning = false
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

            override fun onAnimationStart(p0: Animation?) {

            }

        })

        binding.roundMainLuckySpin.startAnimation(rippleEffect)

    }

    private fun definedInfo(first: RoundStructureData) {

        if (first.round_big != null) {
            HeadLineView.headLineView(
                this@LuckySpinActivity,
                first,
                binding.headLineHolder
            )
        } else {
            binding.buttonLayoutHolder.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        binding.roundAppBar.roundMoneyBagLayout.visibility = View.VISIBLE
        modifyMoney()
    }

    private fun modifyMoney() {
        val roundMoney =
            getRoundDataFromLocal(this@LuckySpinActivity, CachedHolder.CachedKey.round_rank)
        if (!Unique.isRoundEmptyString(roundMoney)) {
            binding.roundAppBar.roundTextMoney.text = roundMoney
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
                        this@LuckySpinActivity,
                        first.round_small_2.random().round_main_image?.toUri()
                    )
                }
                finish()
            }

        }
        onBackPressedDispatcher.addCallback(this, back)
    }
}