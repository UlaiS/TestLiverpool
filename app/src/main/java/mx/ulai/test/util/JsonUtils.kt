package mx.ulai.test.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.lang.reflect.Type



object JsonUtils {

    val gson: Gson = GsonBuilder().serializeSpecialFloatingPointValues().serializeNulls().create()


    fun toJson(o: Any?): String {
        return gson.toJson(o)
    }

    fun toLargeJson(o: Any?): String {
        val element = gson.toJsonTree(o)
        return gson.toJson(element)
    }

    fun toJsonTree(o: Any?): JsonElement {
        return gson.toJsonTree(o)
    }

    fun <T> fromJson(json: String?, t: Class<T>?): T {
        return gson.fromJson(json, t)
    }

    fun <T> fromJson(json: String?, type: Type?): T {
        return gson.fromJson(json, type)
    }

    inline fun <reified T> fromJson(json: String): T {
        return gson.fromJson(json, object : TypeToken<T>() {}.type)
    }

    inline fun <reified T> fromJson(json: JsonElement): T {
        return gson.fromJson(json, object : TypeToken<T>() {}.type)
    }

    inline fun <reified T> fromJson(obj: Any): T {
        val serialization = toJson(obj)
        return fromJson(serialization)
    }

    fun <T> fromJson(reader: JsonReader?, t: Class<T>?): T {
        return gson.fromJson(reader, t)
    }

    fun empty() = "{}"


    fun isJson(s: String?): Boolean {

        if (s.isNullOrEmpty()) return false

        try {
            JSONObject(s)
        } catch (e: JSONException) {
            try {
                JSONArray(s)
            } catch (e1: JSONException) {
                return false
            }
        }
        return true
    }

}