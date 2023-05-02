package com.lahdev.platformscience.util

import android.content.Context
import com.lahdev.platformscience.model.entity.Driver
import com.lahdev.platformscience.model.entity.Shipment
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

/**
 * Allows to load data (shipments and drivers)  from a json file
 */
class FileDataExtractor  {
    private lateinit var shipments: JSONArray
    private lateinit var drivers: JSONArray

     fun loadFromFile(context: Context) {
        try {
            val jsonString: String
            val inputStream = context.getAssets().open("file.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            jsonString = String(buffer, charset("UTF-8"))
            try {
                val jsonObject = JSONObject(jsonString)
                shipments = jsonObject.getJSONArray("shipments")
                drivers = jsonObject.getJSONArray("drivers")
            } catch (ex: JSONException) {
                ex.printStackTrace()
            }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }

     fun getShipments(): ArrayList<Shipment> {
        val itemList = ArrayList<Shipment>()
        try {
            for (i in 0 until shipments.length()) {
                val jsonObject: String = shipments.get(i) as String
                val pattern = "(\\d+)\\s+(.+)".toRegex()
                val matchResult = pattern.matchEntire(jsonObject)
                if (matchResult != null) {
                    val id = matchResult.groupValues[1].toLong()
                    val name = matchResult.groupValues[2]
                    itemList.add(Shipment(id, name))
                } else {
                    System.err.println("not match: $jsonObject")
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return itemList
    }

     fun getDrivers(): ArrayList<Driver> {
        val itemList = ArrayList<Driver>()
        try {
            for (i in 0 until drivers.length()) {
                val name: String = drivers.get(i) as String
                itemList.add(Driver(name = name))
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return itemList
    }
}