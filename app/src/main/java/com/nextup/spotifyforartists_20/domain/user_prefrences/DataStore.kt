package com.nextup.spotifyforartists_20.domain.user_prefrences

import android.content.Context
import android.content.SharedPreferences

class DataStore(context: Context)  {

    private val sharedPreference: SharedPreferences =  context.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)

    fun tags() : DataStoreTags {
        return  DataStoreTags
    }

    fun saveString(tag: String, data: String?){
        val editor = sharedPreference.edit()
        editor.putString(tag, data)
        editor.apply()
    }

    fun saveBoolean(tag: String, value: Boolean){
        val editor = sharedPreference.edit()
        editor.putBoolean(tag, value)
        editor.apply()
    }

    fun saveFloat(tag: String, value: Float){
        val editor = sharedPreference.edit()
        editor.putFloat(tag, value)
        editor.apply()
    }

    fun saveLong(tag: String, value: Long){
        val editor = sharedPreference.edit()
        editor.putLong(tag, value)
        editor.apply()
    }

    fun saveInt(tag: String, value: Int){
        val editor = sharedPreference.edit()
        editor.putInt(tag, value)
        editor.apply()
    }


    fun readString(tag: String): String?{
        return sharedPreference.getString(tag, null)
    }

    fun readBoolean(tag: String): Boolean {
        return sharedPreference.getBoolean(tag, false)
    }

    fun readInt(tag: String): Int {
        return sharedPreference.getInt(tag, 0)
    }

    fun readFloat(tag: String): Float {
        return sharedPreference.getFloat(tag, 0F)
    }

    fun readLong(tag: String): Long {
        return sharedPreference.getLong(tag, 0L)
    }



    fun delete(key: String){
        val editor = sharedPreference.edit()
        editor.remove(key).apply()
    }



}