package com.demo.roundRoblx.Panel

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.demo.roundRoblx.R
import com.demo.roundRoblx.assignmentData.RoundStructureData
import com.demo.roundRoblx.databinding.ActivityTutorialBinding
import com.demo.roundRoblx.various.BeginApplication
import com.demo.roundRoblx.various.CachedHolder
import com.demo.roundRoblx.various.RecyclerAdapter
import com.demo.roundRoblx.various.Unique
import kotlin.jvm.java

class TutorialActivity : AppCompatActivity(), RecyclerAdapter.RecyclerAdapterClick {

    private lateinit var binding: ActivityTutorialBinding
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTutorialBinding.inflate(layoutInflater)
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

        adapter = RecyclerAdapter(this)

        BeginApplication.roundRowResponse.observe(this) {
            val roundTutorialList = addTutorial()
            if (it?.first != null && !Unique.isRoundEmptyString(it.first!!.round_status) && it.first!!.round_status == "1" && it.first?.round_big != null && it.first?.round_big?.size!! > 0) {
                for (i in roundTutorialList.size downTo 0) {
                    if (i % 4 == 0) {
                        roundTutorialList.add(
                            i,
                            RoundStructureData.CatalogModel(
                                roundViewType = 1,
                                roundBigLayout = it.first?.round_big?.random()
                            )
                        )
                    }
                }
            }
            adapter.infoAdd(roundTutorialList)
            triggerPushEvent(it?.first)
            triggerBackEvent(it?.first)
        }

        binding.roundTutorialList.adapter = adapter
    }

    private fun addTutorial(): ArrayList<RoundStructureData.CatalogModel> {
        return arrayListOf<RoundStructureData.CatalogModel>(

            RoundStructureData.CatalogModel(
                roundViewType = 2,
                roundTitleHolder = getString(R.string.navigating_the_robx_app),
                roundSubTitleHolder = getString(R.string.the_robx_app_is_designed_with_simplicity_and_user_friendliness_in_mind_upon_opening_the_app_youll_be_greeted_with_a_clean_interface_that_showcases_the_core_functionalities_the_main_menu_will_present_you_with_options_for_purchasing_robx_managing_your_existing_robx_balance_and_exploring_the_world_of_in_game_items_and_experiences_you_can_easily_access_the_apps_settings_for_customizing_your_experience_and_managing_your_account),
                roundBigLayout = null
            ), RoundStructureData.CatalogModel(
                roundViewType = 2,
                roundTitleHolder = getString(R.string.master_the_marketplace),
                roundSubTitleHolder = getString(R.string.the_marketplace_is_your_treasure_chest_of_in_game_goodies_dont_just_browse_randomly_use_filters_to_narrow_down_your_search_look_for_items_that_align_with_your_current_game_interests_want_new_clothes_for_your_favorite_avatar_filter_by_category_price_and_popularity_always_check_out_the_featured_section_for_trending_items),
                roundBigLayout = null
            ), RoundStructureData.CatalogModel(
                roundViewType = 2,
                roundTitleHolder = getString(R.string.utilizing_robx_in_game),
                roundSubTitleHolder = getString(R.string.once_you_have_acquired_robx_you_can_use_them_to_purchase_a_variety_of_in_game_items_and_experiences_these_include_cosmetic_items_like_avatars_and_accessories_access_to_premium_game_features_and_exclusive_experiences_within_various_roblox_games),
                roundBigLayout = null
            ), RoundStructureData.CatalogModel(
                roundViewType = 2,
                roundTitleHolder = getString(R.string.managing_your_robx_balance),
                roundSubTitleHolder = getString(R.string.the_app_provides_a_clear_overview_of_your_current_robx_balance_you_can_easily_track_your_spending_and_monitor_your_robx_balance_to_make_informed_decisions_the_app_also_shows_your_recent_purchase_history_allowing_you_to_review_your_transactions),
                roundBigLayout = null
            ), RoundStructureData.CatalogModel(
                roundViewType = 2,
                roundTitleHolder = getString(R.string.redeeming_robx_codes),
                roundSubTitleHolder = getString(R.string.if_you_have_received_a_robx_code_from_a_friend_or_a_promotional_campaign_you_can_redeem_it_directly_within_the_app_navigate_to_the_redeem_codes_section_and_enter_the_code_accurately_your_robx_will_be_instantly_added_to_your_account_ready_to_be_used),
                roundBigLayout = null
            ), RoundStructureData.CatalogModel(
                roundViewType = 2,
                roundTitleHolder = getString(R.string.exploring_the_robx_marketplace),
                roundSubTitleHolder = getString(R.string.the_robx_marketplace_is_where_you_can_discover_a_wide_selection_of_digital_items_and_experiences_to_enhance_your_roblox_gameplay_explore_categories_like_clothing_accessories_emotes_and_game_passes_you_can_filter_your_search_by_price_popularity_and_other_criteria_to_quickly_find_what_youre_looking_for),
                roundBigLayout = null
            ), RoundStructureData.CatalogModel(
                roundViewType = 2,
                roundTitleHolder = getString(R.string.staying_informed_with_app_updates),
                roundSubTitleHolder = getString(R.string.the_robx_app_is_regularly_updated_with_new_features_improvements_and_bug_fixes_make_sure_to_check_for_updates_and_install_them_regularly_to_ensure_youre_always_enjoying_the_latest_features_and_a_seamless_experience),
                roundBigLayout = null
            ), RoundStructureData.CatalogModel(
                roundViewType = 2,
                roundTitleHolder = getString(R.string.engaging_with_the_community),
                roundSubTitleHolder = getString(R.string.the_app_also_serves_as_a_gateway_to_connect_with_the_vibrant_roblox_community_you_can_join_forums_participate_in_discussions_and_share_your_experiences_with_other_gamers_engaging_with_fellow_players_can_provide_valuable_insights_into_the_latest_trends_popular_games_and_tipsfor_maximizing_your_robx_usage_building_connections_within_the_community_can_enhance_your_overall_gaming_experience),
                roundBigLayout = null
            ), RoundStructureData.CatalogModel(
                roundViewType = 2,
                roundTitleHolder = getString(R.string.become_a_robx_savvy_buyer),
                roundSubTitleHolder = getString(R.string.dont_just_impulsively_buy_everything_that_catches_your_eye_plan_your_purchases_look_for_bundles_which_often_offer_a_better_value_for_your_robx_compare_prices_for_similar_items_across_different_sellers_and_remember_robx_can_be_used_for_more_than_just_cosmetic_items_game_passes_and_other_in_game_experiences_can_be_incredible_investments),
                roundBigLayout = null
            ), RoundStructureData.CatalogModel(
                roundViewType = 2,
                roundTitleHolder = getString(R.string.hunt_for_hidden_deals),
                roundSubTitleHolder = getString(R.string.the_marketplace_is_a_dynamic_environment_so_prices_can_fluctuate_keep_checking_back_on_items_you_want_some_items_might_be_marked_down_or_offered_as_part_of_limited_time_promotions_be_patient_and_you_might_find_yourself_lucky_enough_to_snag_a_coveted_item_at_a_bargain_price),
                roundBigLayout = null
            )
        )
    }

    override fun detailTutorial(mode: RoundStructureData.CatalogModel) {
        if (!Unique.isRoundEmptyString(mode.roundSubTitleHolder) &&
            !Unique.isRoundEmptyString(mode.roundTitleHolder)
        ) {
            startActivity(
                Intent(
                    this@TutorialActivity,
                    TutorialFullActivity::class.java
                ).apply {
                    putExtra(CachedHolder.CachedKey.round_DISPLAY_NAME, mode.roundTitleHolder)
                    putExtra(
                        CachedHolder.CachedKey.round_DISPLAY_SUB_NAME,
                        mode.roundSubTitleHolder
                    )
                })
        }
    }

    override fun wordListEvent(mode: RoundStructureData.CatalogModel) {

    }

    override fun headLineEvent(mode: RoundStructureData.CatalogModel) {
        val roundClass = BeginApplication.roundRowResponse.value
        if (roundClass?.first != null &&
            roundClass.first?.round_small_2 != null &&
            !Unique.isRoundEmptyString(roundClass.first?.round_status) &&
            roundClass.first?.round_status == "1" &&
            roundClass.first?.round_small_2 != null &&
            roundClass.first?.round_small_2!!.isNotEmpty()
        ) {
            BeginApplication.showRoundRowTab(
                this@TutorialActivity,
                roundClass.first?.round_small_2?.random()?.round_main_image?.toUri()
            )
        }
    }

    private fun triggerPushEvent(first: RoundStructureData?) {
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
                        this@TutorialActivity,
                        first.round_small_2.random().round_main_image?.toUri()
                    )
                }
                finish()
            }

        }
        onBackPressedDispatcher.addCallback(this, back)
    }
}