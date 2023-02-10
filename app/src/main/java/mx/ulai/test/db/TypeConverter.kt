package mx.ulai.test.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import mx.ulai.test.model.*

object TypeConverter {

    @TypeConverter
    @JvmStatic
    fun stringToPlpFlags(listPlpFlags: String): List<PlpFlags> {
        return Gson().fromJson(listPlpFlags, object : TypeToken<List<PlpFlags>>() {}.type)
    }

    @TypeConverter
    @JvmStatic
    fun plpFlagsToString(listOfString: List<PlpFlags>): String {
        return Gson().toJson(listOfString)
    }


    @TypeConverter
    @JvmStatic
    fun stringToVariantsColor(listVariantsColor: String): List<VariantsColor> {
        return Gson().fromJson(listVariantsColor, object : TypeToken<List<VariantsColor>>() {}.type)
    }

    @TypeConverter
    @JvmStatic
    fun variantsColorToString(listOfString: List<VariantsColor>): String {
        return Gson().toJson(listOfString)
    }


    @TypeConverter
    @JvmStatic
    fun stringToRecords(listRecords: String): List<Records> {
        return Gson().fromJson(listRecords, object : TypeToken<List<Records>>() {}.type)
    }

    @TypeConverter
    @JvmStatic
    fun recordsColorToString(listOfString: List<Records>): String {
        return Gson().toJson(listOfString)
    }

    @TypeConverter
    @JvmStatic
    fun stringToDwPromotionInfo(coord: String?): DwPromotionInfo? =
        coord?.let { Gson().fromJson(coord, object : TypeToken<DwPromotionInfo>() {}.type)}

    @TypeConverter
    @JvmStatic
    fun dwPromotionInfoToString(objectOfString: DwPromotionInfo?): String? =
        objectOfString?.let { Gson().toJson(objectOfString) }

    @TypeConverter
    @JvmStatic
    fun stringToPlpResults(coord: String?): PlpResults? =
        coord?.let { Gson().fromJson(coord, object : TypeToken<PlpResults>() {}.type)}

    @TypeConverter
    @JvmStatic
    fun plpResultsToString(objectOfString: PlpResults?): String? =
        objectOfString?.let { Gson().toJson(objectOfString) }

}