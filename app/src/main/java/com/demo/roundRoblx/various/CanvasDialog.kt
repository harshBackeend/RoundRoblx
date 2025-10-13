package com.demo.roundRoblx.various

import android.R
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.demo.roundRoblx.databinding.CanvasDialogViewBinding

object CanvasDialog {

    fun viewCanvasDialog(
        context: Context,
        clamMoney: String,
        canvasDialogEvent: CanvasDialogEvent
    ) {
        val canvasDialog = Dialog(context)
        val roundBinding = CanvasDialogViewBinding.inflate(LayoutInflater.from(context))
        canvasDialog.setContentView(roundBinding.root)
        canvasDialog.setCancelable(false)
        canvasDialog.window!!.setBackgroundDrawableResource(R.color.transparent)
        canvasDialog.window!!.setDimAmount(0.9f)
        val roundWidth = (context.resources.displayMetrics.widthPixels * 0.90).toInt()
        canvasDialog.window!!.setLayout(roundWidth, ViewGroup.LayoutParams.WRAP_CONTENT)

        roundBinding.roundMoney.text = clamMoney

        roundBinding.buttonClam.setOnClickListener {
            canvasDialog.dismiss()
            canvasDialogEvent.clamEvent()
        }


        canvasDialog.show()
    }

    interface CanvasDialogEvent {
        fun clamEvent()
    }
}