package com.nitinm.jet2articles.util

import android.content.Context
import android.widget.Toast
import com.nitinm.jet2articles.R
import java.text.DecimalFormat

fun Context.showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()

}

fun Int.getNumberInDisplayFormat(context: Context, suffix: String): String {

    val numberString = if (Math.abs(this / 1000) > 1)
        "${DecimalFormat("##.##").format((this.toDouble() / 1000))}${context.getString(R.string.k_suffix)} $suffix"
    else
        "$this $suffix"

    return numberString
}
