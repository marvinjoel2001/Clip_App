package com.example.clip_app

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class SimpleDialogFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
        var builder=AlertDialog.Builder(activity)
        var list:Array<String> = activity?.resources!!.getStringArray(R.array.choice_items)
        builder.setTitle("Seleccione una categoria")
        builder.setSingleChoiceItems(list,-1,{dialogInterface, i->

        })
        builder.setPositiveButton("Ok",{ dialogInterface:DialogInterface , i:Int ->})
    }
}