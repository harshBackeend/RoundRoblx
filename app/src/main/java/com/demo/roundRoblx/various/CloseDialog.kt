package com.demo.roundRoblx.various

import android.R
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.demo.roundRoblx.databinding.CloseDialogViewBinding

object CloseDialog {

    fun viewCloseDialog(context: Context, closeDialogEvent: CloseDialogEvent) {
        val closeDialog = Dialog(context)
        val roundBinding = CloseDialogViewBinding.inflate(LayoutInflater.from(context))
        closeDialog.setContentView(roundBinding.root)
        closeDialog.setCancelable(false)
        closeDialog.window!!.setBackgroundDrawableResource(R.color.transparent)
        closeDialog.window!!.setDimAmount(0.9f)
        val roundWidth = (context.resources.displayMetrics.widthPixels * 0.90).toInt()
        closeDialog.window!!.setLayout(roundWidth, ViewGroup.LayoutParams.WRAP_CONTENT)

        roundBinding.buttonNo.setOnClickListener {
            closeDialog.dismiss()
            closeDialogEvent.noEvent()
        }

        roundBinding.buttonYes.setOnClickListener {
            closeDialog.dismiss()
            closeDialogEvent.yesEvent()
        }

        closeDialog.show()
    }

    interface CloseDialogEvent {
        fun yesEvent()
        fun noEvent()
    }
}