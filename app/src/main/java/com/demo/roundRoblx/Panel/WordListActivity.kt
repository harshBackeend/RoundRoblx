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
import com.demo.roundRoblx.databinding.ActivityWordListBinding
import com.demo.roundRoblx.various.BeginApplication
import com.demo.roundRoblx.various.RecyclerAdapter
import com.demo.roundRoblx.various.Unique

class WordListActivity : AppCompatActivity(), RecyclerAdapter.RecyclerAdapterClick {

    private lateinit var binding: ActivityWordListBinding
    private lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWordListBinding.inflate(layoutInflater)
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

        binding.roundAppBar.roundAppTitle.text = resources.getString(R.string.dictionary)

        adapter = RecyclerAdapter(this)

        BeginApplication.roundRowResponse.observe(this) {
            val roundWordList = addRoundWordList()
            if (it?.first != null && !Unique.isRoundEmptyString(it.first!!.round_status) && it.first!!.round_status == "1" && it.first?.round_big != null && it.first?.round_big?.size!! > 0) {
                for (i in roundWordList.size downTo 0) {
                    if (i % 4 == 0) {
                        roundWordList.add(
                            i,
                            RoundStructureData.CatalogModel(roundBigLayout = it.first?.round_big?.random())
                        )
                    }
                }
            }
            adapter.infoAdd(roundWordList)
            triggerPushEvent(it?.first)
            triggerBackEvent(it?.first)
        }

        binding.roundWordList.adapter = adapter
    }

    private fun triggerPushEvent(first: RoundStructureData?) {
        binding.roundAppBar.roundPreviousButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun addRoundWordList(): ArrayList<RoundStructureData.CatalogModel> {
        return arrayListOf<RoundStructureData.CatalogModel>(

            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_aa),
                roundSubTitleHolder = getString(R.string.round_aa_d),
                roundBigLayout = null
            ),

            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_13),
                roundSubTitleHolder = getString(R.string.round_13_d),
                roundBigLayout = null
            ),


            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_1337),
                roundSubTitleHolder = getString(R.string.round_1337_d),
                roundBigLayout = null
            ),


            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_abc),
                roundSubTitleHolder = getString(R.string.round_abc_d),
                roundBigLayout = null
            ),


            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_afk),
                roundSubTitleHolder = getString(R.string.round_afk_d),
                roundBigLayout = null
            ),


            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_alias),
                roundSubTitleHolder = getString(R.string.round_in_d),
                roundBigLayout = null
            ),


            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_alt),
                roundSubTitleHolder = getString(R.string.round_in),
                roundBigLayout = null
            ),


            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_annually),
                roundSubTitleHolder = getString(R.string.round_in_roblox),
                roundBigLayout = null
            ),


            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_beta),
                roundSubTitleHolder = getString(R.string.round_in_roblox_beta),
                roundBigLayout = null
            ),


            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_brb),
                roundSubTitleHolder = getString(R.string.round_brb_d),
                roundBigLayout = null
            ),


            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_chile),
                roundSubTitleHolder = getString(R.string.round_chile_d),
                roundBigLayout = null
            ),


            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_dec),
                roundSubTitleHolder = getString(R.string.in_roblox_the_word_dec),
                roundBigLayout = null
            ),


            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_disc),
                roundSubTitleHolder = getString(R.string.round_disc_d),
                roundBigLayout = null
            ),


            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_e_e),
                roundSubTitleHolder = getString(R.string.round_in_roblox_e),
                roundBigLayout = null
            ),


            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_fe),
                roundSubTitleHolder = getString(R.string.round_in_roblox_fe_),
                roundBigLayout = null
            ),


            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_ffs),
                roundSubTitleHolder = getString(R.string.round_in_roblox_ffs),
                roundBigLayout = null
            ),


            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_lel),
                roundSubTitleHolder = getString(R.string.round_in_roblox_lel),
                roundBigLayout = null
            ),


            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_limited_u),
                roundSubTitleHolder = getString(R.string.round_in_roblox_limited),
                roundBigLayout = null
            ),


            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_gfx),
                roundSubTitleHolder = getString(R.string.round_in_roblox_gfx),
                roundBigLayout = null
            ),


            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_gg),
                roundSubTitleHolder = getString(R.string.round_in_roblox_gg),
                roundBigLayout = null
            ),


            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_hr),
                roundSubTitleHolder = getString(R.string.round_in_roblox_hr),
                roundBigLayout = null
            ),


            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_limited_u),
                roundSubTitleHolder = getString(R.string.round_in_roblox_limited),
                roundBigLayout = null
            ),


            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_lmad),
                roundSubTitleHolder = getString(R.string.round_in_roblox_lmad),
                roundBigLayout = null
            ),


            RoundStructureData.CatalogModel(
                roundViewType = 0,
                roundTitleHolder = getString(R.string.round_lmao),
                roundSubTitleHolder = getString(R.string.round_in_roblox_lmao),
                roundBigLayout = null
            )
        )
    }

    private fun triggerBackEvent(first: RoundStructureData?) {
        val back: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (first != null && first.round_small_2 != null && !Unique.isRoundEmptyString(
                        first.round_status
                    ) && first.round_status == "1" && first.round_small_2.isNotEmpty()
                ) {
                    BeginApplication.showRoundRowTab(
                        this@WordListActivity,
                        first.round_small_2.random().round_main_image?.toUri()
                    )
                }
                finish()
            }

        }
        onBackPressedDispatcher.addCallback(this, back)
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
                this@WordListActivity,
                roundClass.first?.round_small_2?.random()?.round_main_image?.toUri()
            )
        }
    }


}