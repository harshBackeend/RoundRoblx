package com.demo.roundRoblx.Panel

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
import com.demo.roundRoblx.databinding.ActivityExchangeBinding
import com.demo.roundRoblx.various.BeginApplication
import com.demo.roundRoblx.various.CachedHolder
import com.demo.roundRoblx.various.CloseDialog
import com.demo.roundRoblx.various.HeadLineView
import com.demo.roundRoblx.various.Unique

class ExchangeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExchangeBinding

    private var viewType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityExchangeBinding.inflate(layoutInflater)
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

    private fun definedInfo(first: RoundStructureData) {

        if (first.round_big != null) {
            HeadLineView.headLineView(
                this@ExchangeActivity,
                first,
                binding.headLineHolder
            )
        } else {
            binding.buttonLayoutHolder.visibility = View.GONE
        }
    }

    private fun getInfoFromOtherView() {
        try {
            if (intent != null) {
                viewType = intent.getStringExtra(CachedHolder.CachedKey.round_DISPLAY_NAME)
            }
        } catch (e: Exception) {

        }
    }

    private fun setInfoInView() {
        binding.apply {
            var displayTitle: String? = null
            var saval: String? = null
            var savalHint: String? = null
            roundMembershipLayout.visibility = View.GONE
            when (viewType) {

                CachedHolder.HandleKey.bc_round -> {
                    displayTitle = resources.getString(R.string.round_b_to_r)
                    saval = resources.getString(R.string.round_enter_your_b_q)
                    savalHint = resources.getString(R.string.round_enter_number_of_b_q_h)
                }

                CachedHolder.HandleKey.tbc_round -> {
                    roundMembershipLayout.visibility = View.VISIBLE
                    roundMembershipTitle.text =
                        resources.getString(R.string.robox_turbo_membership_calculator)
                    roundMembershipMonth.text = resources.getString(R.string._1_month_900_r)
                    roundMembershipYear.text = resources.getString(R.string._1_year_1200_r)
                    roundMembershipSubTitle.text =
                        resources.getString(R.string.tbc_stands_for_turbo_membership)
                    roundMembershipSecundSubTitle.text =
                        resources.getString(R.string.a_subscription_that_provides_even_morebenefits_and_features_compared_to_tbc)
                    displayTitle = resources.getString(R.string.round_t_to_r)
                    saval = resources.getString(R.string.round_enter_your_t_q)
                    savalHint = resources.getString(R.string.round_enter_number_of_t_q_h)
                }

                CachedHolder.HandleKey.obx_round -> {
                    roundMembershipLayout.visibility = View.VISIBLE
                    roundMembershipTitle.text =
                        resources.getString(R.string.robox_outrageous_membership_calculator)
                    roundMembershipMonth.text = resources.getString(R.string._1_month_700_r)
                    roundMembershipYear.text = resources.getString(R.string._1_year_1400_r)
                    roundMembershipSubTitle.text =
                        resources.getString(R.string.obc_stands_for_outrageous_membership)
                    roundMembershipSecundSubTitle.text =
                        resources.getString(R.string.a_premium_subscription_that_grants_exclusive_perks_on_robox)
                    displayTitle = resources.getString(R.string.round_o_to_r)
                    saval = resources.getString(R.string.round_enter_your_o_q)
                    savalHint = resources.getString(R.string.round_enter_number_of_o_q_h)
                }

                CachedHolder.HandleKey.rbx_round_do -> {
                    displayTitle = resources.getString(R.string.round_rbx_to_dollar_converter_t)
                    saval = resources.getString(R.string.round_enter_your_rbx_q)
                    savalHint = resources.getString(R.string.round_enter_number_of_rbx_q_h)
                }

                CachedHolder.HandleKey.do_round_rbx -> {
                    displayTitle = resources.getString(R.string.round_do_to_t)
                    saval = resources.getString(R.string.round_enter_your_dollar_q)
                    savalHint = resources.getString(R.string.round_enter_number_of_dollar_q_h)
                }
            }

            if (!Unique.isRoundEmptyString(displayTitle)
                && !Unique.isRoundEmptyString(saval)
                && !Unique.isRoundEmptyString(savalHint)
            ) {
                roundAppBar.roundAppTitle.text = displayTitle
                roundQ.text = saval
                roundEditText.hint = savalHint
                roundTextE.text = getString(R.string.error_please, savalHint)
            }
        }
    }

    private fun triggerPushEvent(first: RoundStructureData?) {

    }

    private fun triggerBackEvent(first: RoundStructureData?) {
        val back: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(
                        first.round_status
                    ) && first.round_status == "1" && first.round_small_2.isNotEmpty()
                ) {
                    BeginApplication.showRoundRowTab(
                        this@ExchangeActivity,
                        first.round_small_2.random().round_main_image?.toUri()
                    )
                }
                finish()
            }

        }
        onBackPressedDispatcher.addCallback(this, back)
    }
}