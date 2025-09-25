package com.demo.roundRoblx.various

import android.content.Context
import android.view.View
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.demo.roundRoblx.assignmentData.RoundStructureData
import com.demo.roundRoblx.databinding.LayoutRoundBottomViewBinding

object HeadLineView {
    fun headLineView(
        context: Context,
        roundModel: RoundStructureData,
        view: LayoutRoundBottomViewBinding
    ) {
        if (roundModel.round_big != null && roundModel.round_big.isNotEmpty()) {
            view.roundBottomView.visibility = View.VISIBLE
            val roundBigMode = roundModel.round_big.random()
            view.apply {

                Glide.with(view.roundImageProfile).load(roundBigMode.round_p)
                    .into(view.roundImageProfile)

                if (!Unique.isRoundEmptyString(roundBigMode.round_main_text)) {
                    roundHeadLine.visibility = View.VISIBLE
                    roundHeadLine.text = roundBigMode.round_main_text
                } else {
                    roundHeadLine.visibility = View.GONE
                }

                if (!Unique.isRoundEmptyString(roundBigMode.round_main_sub_text)) {
                    roundSubLine.visibility = View.VISIBLE
                    roundSubLine.text = roundBigMode.round_main_sub_text
                } else {
                    roundSubLine.visibility = View.GONE
                }

                if (!Unique.isRoundEmptyString(roundBigMode.round_main_text)) {
                    roundHeadLineImage.visibility = View.VISIBLE
                    Glide.with(view.roundMainButton).load(roundBigMode.round_main_image)
                        .into(view.roundHeadLineImage)
                } else {
                    roundHeadLineImage.visibility = View.GONE
                }

                if (!Unique.isRoundEmptyString(roundBigMode.round_main_button)) {
                    roundMainButton.visibility = View.VISIBLE
                    roundMainButton.text = roundBigMode.round_main_button
                } else {
                    roundMainButton.visibility = View.GONE
                }

                roundBottomView.setOnClickListener {
                    if (roundModel.round_small_2 != null && roundModel.round_small_2.isNotEmpty()){
                        BeginApplication.showRoundRowTab(
                            context,
                            roundModel.round_small_2.random().round_main_image?.toUri()
                        )
                    }
                }

                roundHeadLineImage.setOnClickListener {
                    roundBottomView.performClick()
                }

                roundMainButton.setOnClickListener {
                    roundBottomView.performClick()
                }

            }
        }else{
            view.roundBottomView.visibility = View.GONE
        }
    }
}