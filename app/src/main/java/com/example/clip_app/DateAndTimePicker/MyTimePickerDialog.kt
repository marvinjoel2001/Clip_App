package com.cats_soft.probadoralarmas

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class MyTimePickerDialog:DialogFragment() {
    private val c=Calendar.getInstance()
    private val hour=c.get(Calendar.HOUR_OF_DAY)
    private val minute=c.get(Calendar.MINUTE)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return TimePickerDialog(requireActivity(),android.R.style.Theme_Holo_Dialog,activity as TimePickerDialog.OnTimeSetListener,hour,minute,android.text.format.DateFormat.is24HourFormat(activity))
    }
}