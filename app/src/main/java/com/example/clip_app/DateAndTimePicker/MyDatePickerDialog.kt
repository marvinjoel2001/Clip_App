package com.cats_soft.probadoralarmas

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class MyDatePickerDialog:DialogFragment() {
    val c=Calendar.getInstance()
    val year=c.get(Calendar.YEAR)
    val month=c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return DatePickerDialog(requireActivity(),android.R.style.Theme_Holo_Dialog,activity as DatePickerDialog.OnDateSetListener,year,month,day)
    }
}