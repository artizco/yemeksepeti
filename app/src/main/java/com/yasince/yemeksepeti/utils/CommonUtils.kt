package com.yasince.yemeksepeti.utils

import android.content.Context
import com.google.gson.Gson
import java.io.IOException

object CommonUtils {

    @Throws(IOException::class)
    private fun loadJsonFromAssets(context: Context, jsonFileName: String): String {

        val manager = context.assets
        val stream = manager.open(jsonFileName)

        val size = stream.available()
        val buffer = ByteArray(size)
        stream.read(buffer)
        stream.close()

        return String(buffer, Charsets.UTF_8)
    }

    fun <T> mockIt(context: Context, fileName: String, classOfT: Class<T>): T {
        var response = classOfT.newInstance()
        try {
            response = Gson().fromJson(
                    CommonUtils.loadJsonFromAssets(context, fileName), classOfT)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return response
    }
}
