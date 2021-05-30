package com.patates.gamercontrol.ui.yardimciSiniflar

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.coroutines.*
import org.json.JSONObject
import java.io.IOException
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

 class weatherTask(city:String,txtSicaklik: TextView,icon:ImageView,context: Context,txtHava:TextView) : AsyncTask<String, Void, String>() {
    var CITY=city
    var API="9582fb07cd16ab2443c898dd0cb5258f"
     var context=context
     var iconImage=icon
     var txtSicakik=txtSicaklik
     var txtHavaNasil=txtHava
    override fun onPreExecute() {
        super.onPreExecute()
        /* Showing the ProgressBar, Making the main design GONE */
    }

    override fun doInBackground(vararg params: String?): String? {
        var response:String?
        try{
            response = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API&lang=Tr").readText(
                Charsets.UTF_8
            )
        }catch (e: Exception){
            response = null
        }
        return response
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        try {
            /* Extracting JSON returns from the API */
            val jsonObj = JSONObject(result)
            val main = jsonObj.getJSONObject("main")
            val weather = jsonObj.getJSONArray("weather").getJSONObject(0)

            val temp = main.getString("temp")+"°C"
            txtSicakik.text=temp
            val iconCode=weather.getString("icon")
            if (havaNasil(iconCode)){
                txtHavaNasil.text="Hava bugün güzel oyun oynama dışarı çık"
            }else{
                txtHavaNasil.text="Hava kötü boş ver dışarıyı oyun oyna"
            }
            var icon_url="http://openweathermap.org/img/w/$iconCode.png"
            val result: Deferred<Bitmap?> = GlobalScope.async {
                URL(icon_url).toBitmap()
            }
            GlobalScope.launch(Dispatchers.Main) {
                // show bitmap on image view when available
                iconImage.setImageBitmap(result.await())
            }
        } catch (e: Exception) {

        }

    }
     fun URL.toBitmap(): Bitmap?{
         return try {
             BitmapFactory.decodeStream(openStream())
         }catch (e: IOException){
             null
         }
     }
     fun havaNasil(iconKodu:String):Boolean{
         when(iconKodu){
             "01d","01n","02d","02n","03d","03n","04d","04n"->return true
         }
         return false
     }
}