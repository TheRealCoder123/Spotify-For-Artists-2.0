package com.nextup.spotifyforartists_20.domain.android

import android.app.Dialog
import android.content.Context
import com.nextup.spotifyforartists_20.R

object LoadingDialog {

    private lateinit var dialog: Dialog

    fun initLoadingDialog(context: Context){
        dialog = Dialog(context)
    }
    fun startLoadingDialog(){
        dialog.setContentView(R.layout.loading_dialog_layout)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
    }
    fun hideLoadingDialog(){
        dialog.dismiss()
    }

}