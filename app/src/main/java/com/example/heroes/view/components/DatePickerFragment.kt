package com.example.heroes.view.components

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class DatePickerFragment(
    private val tag: String,
    private val formattedDateCallback: (String, String, String) -> Unit
) :
    DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        val day = p3
        val month = p2 + 1
        val year = p1

        val date = LocalDate.of(year, month, day)
        val brazilFormattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
        val americanFormattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        this.formattedDateCallback(tag, brazilFormattedDate, americanFormattedDate)
    }
}