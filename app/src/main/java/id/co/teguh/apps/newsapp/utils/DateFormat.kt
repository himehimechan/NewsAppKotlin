package id.co.teguh.apps.newsapp.utils

import java.text.SimpleDateFormat
import java.util.*

class DateFormat {
    private var SDF_FORMAT_DATE_SQL: SimpleDateFormat? = null
    private var SDF_FORMAT_DATE_NORMAL: SimpleDateFormat? = null
    private var SDF_FORMAT_DATE_DAY_NORMAL: SimpleDateFormat? = null
    private var SDF_FORMAT_DAY_NORMAL: SimpleDateFormat? = null
    private var SDF_FORMAT_DATE_TIME_SQL: SimpleDateFormat? = null
    private var SDF_FORMAT_DATE_TIME_NORMAL: SimpleDateFormat? = null
    private var SDF_FORMAT_TIME_NORMAL: SimpleDateFormat? = null
    private var SDF_FORMAT_DATE_YEAR: SimpleDateFormat? = null
    private var SDF_FORMAT_DATE_WITH_FULL_MONTH_NORMAL: SimpleDateFormat? = null
    private var SDF_FORMAT_DATE_MONTH_YEAR_NORMAL: SimpleDateFormat? = null

    companion object {
        val shared = DateFormat()
    }

    private fun init() {
        if (SDF_FORMAT_DATE_NORMAL == null) SDF_FORMAT_DATE_NORMAL = SimpleDateFormat("dd MMM yyyy")
        if (SDF_FORMAT_DATE_WITH_FULL_MONTH_NORMAL == null) SDF_FORMAT_DATE_WITH_FULL_MONTH_NORMAL = SimpleDateFormat("dd MMMM yyyy")
        if (SDF_FORMAT_DAY_NORMAL == null) SDF_FORMAT_DAY_NORMAL = SimpleDateFormat("EEEE")
        if (SDF_FORMAT_DATE_TIME_NORMAL == null) SDF_FORMAT_DATE_TIME_NORMAL = SimpleDateFormat("dd MMM yyyy HH:mm:ss")
        if (SDF_FORMAT_TIME_NORMAL == null) SDF_FORMAT_TIME_NORMAL = SimpleDateFormat("HH:mm:ss")
        if (SDF_FORMAT_DATE_SQL == null) SDF_FORMAT_DATE_SQL = SimpleDateFormat("yyyy-MM-dd")
        if (SDF_FORMAT_DATE_TIME_SQL == null) SDF_FORMAT_DATE_TIME_SQL = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        if (SDF_FORMAT_DATE_YEAR == null) SDF_FORMAT_DATE_YEAR = SimpleDateFormat("yyyy")
        if (SDF_FORMAT_DATE_MONTH_YEAR_NORMAL == null) SDF_FORMAT_DATE_MONTH_YEAR_NORMAL = SimpleDateFormat(
            "MMMM yyyy"
        )
    }

    fun formatDateSql(date: Date?): String? {
        init()
        return SDF_FORMAT_DATE_SQL!!.format(date)
    }

    fun formatDateTimeSql(date: Date?): String? {
        init()
        return SDF_FORMAT_DATE_TIME_SQL!!.format(date)
    }

    fun formatDateNormal(date: Date?): String? {
        init()
        return SDF_FORMAT_DATE_NORMAL!!.format(date)
    }

    fun formatDateWithFullMonthNormal(date: Date?): String? {
        init()
        return SDF_FORMAT_DATE_WITH_FULL_MONTH_NORMAL!!.format(date)
    }

    fun formatDateTimeNormal(date: Date?): String? {
        init()
        return SDF_FORMAT_DATE_TIME_NORMAL!!.format(date)
    }

    fun formatTimeNormal(date: Date?): String? {
        init()
        return SDF_FORMAT_TIME_NORMAL!!.format(date)
    }

    fun formatMonthYearNormal(date: Date?): String? {
        init()
        return SDF_FORMAT_DATE_MONTH_YEAR_NORMAL!!.format(date)
    }

    fun formatDateYear(date: Date?): String? {
        init()
        return SDF_FORMAT_DATE_YEAR!!.format(date)
    }

    fun convertDateSqlToNormal(date: String?): String? {
        init()
        return formatDateNormal(parseDateSql(date))
    }

    fun convertDateNormalToSql(date: String?): String? {
        init()
        return formatDateSql(parseDateNormal(date))
    }

    fun convertDateTimeSqlToNormal(date: String?): String? {
        init()
        return formatDateTimeNormal(parseDateTimeSql(date))
    }

    fun convertDateTimeNormalToSql(date: String?): String? {
        init()
        return formatDateTimeSql(parseDateTimeNormal(date))
    }


    fun parseDateSql(date: String?): Date? {
        init()
        return try {
            SDF_FORMAT_DATE_SQL!!.parse(date)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun parseDateTimeSql(date: String?): Date? {
        init()
        return try {
            SDF_FORMAT_DATE_TIME_SQL!!.parse(date)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun parseDateNormal(date: String?): Date? {
        init()
        return try {
            SDF_FORMAT_DATE_NORMAL!!.parse(date)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun parseDateTimeNormal(date: String?): Date? {
        init()
        return try {
            SDF_FORMAT_DATE_TIME_NORMAL!!.parse(date)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun parseMonthYearNormal(date: String?): Date? {
        init()
        return try {
            SDF_FORMAT_DATE_MONTH_YEAR_NORMAL!!.parse(date)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun parseDateYear(date: String?): Date? {
        init()
        return try {
            SDF_FORMAT_DATE_YEAR!!.parse(date)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getDifferenceInSec(time1: Long, time2: Long): Long {
        return Math.abs(time1 - time2) / 1000
    }
}