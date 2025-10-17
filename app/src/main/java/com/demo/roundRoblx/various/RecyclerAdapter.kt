package com.demo.roundRoblx.various

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.roundRoblx.assignmentData.RoundStructureData
import com.demo.roundRoblx.databinding.LayoutRoundMgBottomViewBinding
import com.demo.roundRoblx.databinding.RoundTutorialLayoutBinding
import com.demo.roundRoblx.databinding.RoundWordLayoutBinding

class RecyclerAdapter(val recyclerAdapterClick: RecyclerAdapterClick) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listOfInfoMain: ArrayList<RoundStructureData.CatalogModel> = arrayListOf()

    fun infoAdd(listOfInfo: ArrayList<RoundStructureData.CatalogModel>) {
        listOfInfoMain.clear()
        listOfInfoMain.addAll(listOfInfo)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return listOfInfoMain[position].roundViewType
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            2 -> {
                TutorialViewHolder(
                    RoundTutorialLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            1 -> {
                LayoutRoundMgBottomView(
                    LayoutRoundMgBottomViewBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
            }

            else -> {
                WordViewHolder(
                    RoundWordLayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when (holder) {
            is TutorialViewHolder -> {
                holder.combine(listOfInfoMain[position], position)
            }

            is WordViewHolder -> {
                holder.combine(listOfInfoMain[position], position)
            }

            is LayoutRoundMgBottomView -> {
                holder.combine(listOfInfoMain[position], position)
            }
        }
    }

    override fun getItemCount(): Int = listOfInfoMain.size

    inner class TutorialViewHolder(val binding: RoundTutorialLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun combine(model: RoundStructureData.CatalogModel, position: Int) {
            binding.apply {
                roundWordTitle.text = model.roundTitleHolder

                itemView.setOnClickListener {
                    recyclerAdapterClick.detailTutorial(model)
                }
            }
        }
    }

    inner class WordViewHolder(val binding: RoundWordLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun combine(model: RoundStructureData.CatalogModel, position: Int) {
            binding.apply {
                roundWordTitle.text = model.roundTitleHolder
                roundWordSubTitle.text = model.roundSubTitleHolder

                roundWordShare.setOnClickListener {
                    recyclerAdapterClick.wordListEvent(model)
                }
            }
        }
    }

    inner class LayoutRoundMgBottomView(val binding: LayoutRoundMgBottomViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun combine(model: RoundStructureData.CatalogModel, position: Int) {
            binding.apply {
                layoutMiddle.roundBottomView.visibility = View.VISIBLE
                Glide.with(binding.layoutMiddle.roundImageProfile)
                    .load(model.roundBigLayout?.round_p)
                    .into(binding.layoutMiddle.roundImageProfile)

                if (!Unique.isRoundEmptyString(model.roundBigLayout?.round_main_text)) {
                    binding.layoutMiddle.roundHeadLine.visibility = View.VISIBLE
                    binding.layoutMiddle.roundHeadLine.text = model.roundBigLayout?.round_main_text
                } else {
                    binding.layoutMiddle.roundHeadLine.visibility = View.GONE
                }

                if (!Unique.isRoundEmptyString(model.roundBigLayout?.round_main_sub_text)) {
                    binding.layoutMiddle.roundSubLine.visibility = View.VISIBLE
                    binding.layoutMiddle.roundSubLine.text =
                        model.roundBigLayout?.round_main_sub_text
                } else {
                    binding.layoutMiddle.roundSubLine.visibility = View.GONE
                }

                if (!Unique.isRoundEmptyString(model.roundBigLayout?.round_main_text)) {
                    binding.layoutMiddle.roundHeadLineImage.visibility = View.VISIBLE
                    Glide.with(binding.layoutMiddle.roundMainButton)
                        .load(model.roundBigLayout?.round_main_image)
                        .into(binding.layoutMiddle.roundHeadLineImage)
                } else {
                    binding.layoutMiddle.roundHeadLineImage.visibility = View.GONE
                }

                if (!Unique.isRoundEmptyString(model.roundBigLayout?.round_main_button)) {
                    binding.layoutMiddle.roundMainButton.visibility = View.VISIBLE
                    binding.layoutMiddle.roundMainButton.text =
                        model.roundBigLayout?.round_main_button
                } else {
                    binding.layoutMiddle.roundMainButton.visibility = View.GONE
                }

                binding.layoutMiddle.roundHeadLine.isSelected = true
                binding.layoutMiddle.roundSubLine.isSelected = true

                itemView.setOnClickListener {
                    recyclerAdapterClick.headLineEvent(model)
                }
            }
        }
    }


    interface RecyclerAdapterClick {
        fun detailTutorial(mode: RoundStructureData.CatalogModel)
        fun wordListEvent(mode: RoundStructureData.CatalogModel)
        fun headLineEvent(mode: RoundStructureData.CatalogModel)
    }


}