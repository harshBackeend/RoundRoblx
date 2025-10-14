package com.demo.roundRoblx.various

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.roundRoblx.assignmentData.RoundStructureData

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

    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {

    }

    override fun getItemCount(): Int = listOfInfoMain.size


    interface RecyclerAdapterClick {
        fun detailTutorial(mode: RoundStructureData.CatalogModel)
        fun wordListEvent(mode: RoundStructureData.CatalogModel)
        fun headLineEvent(mode: RoundStructureData.CatalogModel)
    }
}