package com.patates.gamercontrol.ui.yardimciSiniflar

import android.content.Context

object Sp {
    inline fun <reified T> add(key:String, value:T, context: Context){
        var sharedPreferences=context.getSharedPreferences("com.patates.gamercontrol",Context.MODE_PRIVATE)
        when(T::class){
            Int::class->{
                sharedPreferences.edit().putInt(key,value as Int).apply()
            }
            String::class->{
                sharedPreferences.edit().putString(key,value as String).apply()
            }
        }
    }
    inline fun <reified T> get(key:String, context: Context): T {
        var sharedPreferences=context.getSharedPreferences("com.patates.gamercontrol",Context.MODE_PRIVATE)
        var value:T?=null
        when(T::class){
            Int::class->{
                value=sharedPreferences.getInt(key,0) as T
            }
            String::class->{
                value=sharedPreferences.getString(key,"") as T
            }
        }
        return value!!
    }
    fun remove(key: String,context: Context){
        var sharedPreferences=context.getSharedPreferences("com.patates.gamercontrol",Context.MODE_PRIVATE)
        sharedPreferences.edit().remove(key).apply()
    }
}