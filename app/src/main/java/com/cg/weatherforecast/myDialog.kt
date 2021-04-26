package com.cg.weatherforecast

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class myDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder= AlertDialog.Builder(activity!!)
        builder.setMessage("Please wait...")
        builder.setTitle("Getting Place!")
        return builder.create()
    }
}