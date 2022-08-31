package com.victorhvs.lotonaticos.presentation.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date?.dateToString(format: String = "dd/MM/yyyy"): String {
    if (this == null) {
        return "--"
    }
    // simple date formatter
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())

    // return the formatted date string
    return dateFormatter.format(this)
}

fun Number.toFormatedCurrency(): String {
    val df = DecimalFormat(
        "#,##0.00",
        DecimalFormatSymbols().apply {
            groupingSeparator = '.'
            decimalSeparator = ','
        }
    ).apply {
        isDecimalSeparatorAlwaysShown = true
    }
    return df.format(this)
}
