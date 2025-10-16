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
import com.demo.roundRoblx.databinding.ActivityFunnyBinding
import com.demo.roundRoblx.various.BeginApplication
import com.demo.roundRoblx.various.RecyclerAdapter
import com.demo.roundRoblx.various.Unique

class FunnyActivity : AppCompatActivity(), RecyclerAdapter.RecyclerAdapterClick {

    private lateinit var binding: ActivityFunnyBinding
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFunnyBinding.inflate(layoutInflater)
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

        binding.roundAppBar.roundAppTitle.text = resources.getString(R.string.memes)

        adapter = RecyclerAdapter(this)

        BeginApplication.roundRowResponse.observe(this) {
            val roundFunnyList = addFunnyList()
            if (it?.first != null && !Unique.isRoundEmptyString(it.first!!.round_status) && it.first!!.round_status == "1" && it.first?.round_big != null && it.first?.round_big?.size!! > 0) {
                for (i in roundFunnyList.size downTo 0) {
                    if (i % 4 == 0) {
                        roundFunnyList.add(
                            i,
                            RoundStructureData.CatalogModel(roundBigLayout = it.first?.round_big?.random())
                        )
                    }
                }
            }
            adapter.infoAdd(roundFunnyList)
            triggerPushEvent(it?.first)
            triggerBackEvent(it?.first)
        }

        binding.roundFunnyList.adapter = adapter
    }

    private fun addFunnyList(): ArrayList<RoundStructureData.CatalogModel> {
        return arrayListOf<RoundStructureData.CatalogModel>(

            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = resources.getString(R.string.round_robx_budgeting),
                roundSubTitleHolder = resources.getString(R.string.round_me_i_ll_save_my_robx_for_something_special_also_me_buys_a_hat_for_5_robx),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_robx),
                roundSubTitleHolder = getString(R.string.round_expectation),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_when),
                roundSubTitleHolder = getString(R.string.round_me),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_robx_scams),
                roundSubTitleHolder = getString(R.string.round_when_d),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_game_updates),
                roundSubTitleHolder = getString(R.string.round_when_the_game),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_robx_for_game_passes),
                roundSubTitleHolder = getString(R.string.round_me_i_ll_never),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_roblox_fashion),
                roundSubTitleHolder = getString(R.string.round_when_you),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_robx_inflation),
                roundSubTitleHolder = getString(R.string.round_when_you_realize_that_100_robx_doesn_t_buy_what_it_used_to_inflation_is_real),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_roblox_friends),
                roundSubTitleHolder = getString(R.string.round_when_your_friend_says_they_ll_help_you_in_a_game_but_just_stands_there_thanks_for_nothing),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_roblox_obby),
                roundSubTitleHolder = getString(R.string.round_me_this_obby_looks_easy_also_me_falls_on_the_first_jump),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_robx_trade_offers),
                roundSubTitleHolder = getString(R.string.round_when_someone_offers_you_a_trade_that_s_clearly_not_fair_nice_try_buddy),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_roblox_roleplay),
                roundSubTitleHolder = getString(R.string.round_when_you_join_a_roleplay_server_and_everyone_is_super_serious_am_i_in_the_right_place),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_roblox_events),
                roundSubTitleHolder = getString(R.string.round_when_you_miss_a_limited_time_event_crying_in_robx),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_roblox_updates_1),
                roundSubTitleHolder = getString(R.string.round_when_the_game_updates_and_you_have_to_relearn_the_controls_why_do_they_keep_changing_things),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_robx_goals),
                roundSubTitleHolder = getString(R.string.round_me_i_ll_save_up_for_that_cool_item_also_me_spends_it_all_on_random_stuff),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_roblox_friends),
                roundSubTitleHolder = getString(R.string.round_when_your_friend_says_they_ll_join_you_but_takes_forever_i_m_waiting),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_roblox_game_genres),
                roundSubTitleHolder = getString(R.string.round_when_you_try_a_new_game_genre_and_it_s_nothing_like_you_expected_what_is_this),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_roblox_builders),
                roundSubTitleHolder = getString(R.string.round_when_you_see_a_beautifully_built_game_i_can_barely_build_a_box),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_roblox_pets),
                roundSubTitleHolder = getString(R.string.round_when_you_finally_get_that_rare_pet_i_m_never_letting_you_go),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_roblox_rage),
                roundSubTitleHolder = getString(R.string.round_when_you_die_for_the_10th_time_in_a_row_i_m_done_with_this_game),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_roblox_friends_1),
                roundSubTitleHolder = getString(R.string.round_when_your_friend_gets_a_rare_item_and_you_don_t_i_m_happy_for_you_i_guess),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_roblox_game_passes),
                roundSubTitleHolder = getString(R.string.round_when_you_buy_a_game_pass_and_it_doesn_t_work_what_a_scam),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_roblox_updates),
                roundSubTitleHolder = getString(R.string.round_when_the_game_updates_and_you_have_to_start_from_scratch_why_roblox_why),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_roblox_fashion_1),
                roundSubTitleHolder = getString(R.string.round_when_you_see_someone_with_a_cool_outfit_i_need_that_in_my_life),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_roblox_community),
                roundSubTitleHolder = getString(R.string.round_when_you_find_a_group_of_friends_who_love_roblox_as_much_as_you_do_finally_my_people),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_relatable_gamer_meme_1),
                roundSubTitleHolder = getString(R.string.round_check_out_this_meme_when_your_wifi_disconnects_mid_game),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_fun_fact_about_robx),
                roundSubTitleHolder = getString(R.string.round_did_you_know_robx_has_over_40_million_games_created_by_users),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_gaming_joke),
                roundSubTitleHolder = getString(R.string.round_why_did_the_gamer_bring_a_ladder_to_reach_the_next_level),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_relatable_gamer_meme),
                roundSubTitleHolder = getString(R.string.round_pov_you_re_in_the_middle_of_a_boss_fight_and_the_wifi_says_goodbye),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_friend_joins_after_game_ends),
                roundSubTitleHolder = getString(R.string.round_when_your_friend_joins_the_server_right_after_you_rage_quit),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_lag_kills_you),
                roundSubTitleHolder = getString(R.string.round_robx_it_s_not_you_it_s_the_lag_but_you_still_died),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_spawn_trapping),
                roundSubTitleHolder = getString(R.string.round_spawning_in_the_middle_of_chaos_like_guess_i_ll_die),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_afk_teammates),
                roundSubTitleHolder = getString(R.string.round_afk_teammates_legends_who_do_nothing_but_still_win),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_accidental_leave),
                roundSubTitleHolder = getString(R.string.round_spent_an_hour_building_accidentally_hits_the_leave_button),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_pet_simulator_flex),
                roundSubTitleHolder = getString(R.string.round_friend_look_at_my_rare_pet_me_that_s_great_but_i_have_two_cats),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_tower_of_hell),
                roundSubTitleHolder = getString(R.string.round_tower_of_hell_be_like_you_fell_start_over_loser),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_adopt_me_trades),
                roundSubTitleHolder = getString(R.string.round_friend_fair_trade_only_proceeds_to_scam_me_for_my_legendary_pet),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_simulator_grinding),
                roundSubTitleHolder = getString(R.string.round_spends_5_hours_grinding_for_one_extra_level),
                roundBigLayout = null
            ),
            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_roleplay_troll),
                roundSubTitleHolder = getString(R.string.round_when_the_whole_server_is_roleplaying_but_you_re_just_there_to_troll),
                roundBigLayout = null
            )
        )
    }

    override fun detailTutorial(mode: RoundStructureData.CatalogModel) {

    }

    override fun wordListEvent(class_model: RoundStructureData.CatalogModel) {
        if (!Unique.isRoundEmptyString(class_model.roundTitleHolder) && !Unique.isRoundEmptyString(
                class_model.roundSubTitleHolder
            )
        ) {
            val roundSendData: Intent = Intent().setAction(Intent.ACTION_SEND)
            roundSendData.setType("text/plain")
            roundSendData.putExtra(
                Intent.EXTRA_TEXT,
                "${class_model.roundTitleHolder}\n${class_model.roundSubTitleHolder}"
            )
            startActivity(Intent.createChooser(roundSendData, "Share via"))
        }
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
                this@FunnyActivity,
                roundClass.first?.round_small_2?.random()?.round_main_image?.toUri()
            )
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
                        this@FunnyActivity,
                        first.round_small_2.random().round_main_image?.toUri()
                    )
                }
                finish()
            }

        }
        onBackPressedDispatcher.addCallback(this, back)
    }

    private fun triggerPushEvent(first: RoundStructureData?) {
        binding.roundAppBar.roundPreviousButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}