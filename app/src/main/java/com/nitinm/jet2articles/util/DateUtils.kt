package com.nitinm.jet2articles.util

import android.content.Context
import com.nitinm.jet2articles.R
import org.joda.time.DateTime
import org.joda.time.Days
import org.joda.time.Hours
import org.joda.time.Minutes

fun timeFromPublished(context: Context,createdDate:String) :String{
    val now = DateTime.now()
    val createdjodaDate = DateTime.parse(createdDate)

    val days = Days.daysBetween(createdjodaDate,now).days
    val hours = Hours.hoursBetween(createdjodaDate,now).hours
    val minutes = Minutes.minutesBetween( createdjodaDate,now).minutes

    val timeDifference =
        when {
            days > 0 -> "$days${context.getString(R.string.days_suffix)}"
            hours > 0 ->"$hours${context.getString(R.string.hours_suffix)}"
            else -> "$minutes${context.getString(R.string.minutes_suffix)}"
        }
    return timeDifference
}