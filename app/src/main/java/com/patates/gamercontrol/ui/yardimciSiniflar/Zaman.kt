package com.patates.gamercontrol.ui.yardimciSiniflar

import android.content.Context

object Zaman {
    fun toplamOynamaSuresi(sureListesi:ArrayList<PlayTime>):Sure{
        var toplamSure:Double=0.0
            sureListesi.forEach {
                if(it.stopTime!=null){
                    var int:Int=((it.stopTime!!.time-it.startTime.time)/1000).toInt()
                    var double:Double=int.toDouble()/60
                    toplamSure+=double
                }
            }
        return doubleToSure(toplamSure)
    }
    fun ortalamaOyunaGirisSaati(sureListesi:ArrayList<PlayTime>):Int{
        val m = mutableMapOf<Int, Int>()
        sureListesi.forEach {
            var a=it.startTime.hours
            if (m[a] != null) m[a] = m[a]!! +1 else m[a] = 1
        }
        var saat= 0
        var tekrar=0
        m.forEach{
            if(it.value>tekrar) {
                saat=it.key
                tekrar=it.value
            }

        }
        return saat
    }
    fun ortalamaCikisSaati(sureListesi:ArrayList<PlayTime>):Int{
        val m = mutableMapOf<Int, Int>()
        sureListesi.forEach {
           it.stopTime?.let {
               var a=it.hours
               if (m[a] != null) m[a] = m[a]!! +1 else m[a] = 1
           }
        }
        var saat= 0
        var tekrar=0
        m.forEach{
            if(it.value>tekrar) {
                saat=it.key
                tekrar=it.value
            }

        }
        return saat
    }
    fun gunlukOrtalamaOynamaSaati(sureListesi:ArrayList<PlayTime>):Sure{
        var gunler= HashSet<Int>()
        var gunlukOrtalama=ArrayList<Double>()
        var toplam:Double=0.0
        sureListesi.forEach {
            gunler.add(it.startTime.day)
        }
        gunler.forEach{g->
            var ortalama=0.0
            var sayac=0
            sureListesi.filter { it.startTime.day==g }.forEach { p->
                p.stopTime?.let {
                    sayac++
                    ortalama+=((it.time-p.startTime.time)/60000).toDouble()
                }
                gunlukOrtalama.add(ortalama/sayac)
            }
        }
        gunlukOrtalama.forEach {
            toplam+=it
        }
        return doubleToSure(toplam/gunlukOrtalama.size)
    }
    fun oyunOynadigiGunSayisi(sureListesi:ArrayList<PlayTime>):Int{
        var gunler= HashSet<Int>()
        sureListesi.forEach {
            gunler.add(it.startTime.day)
        }
        return gunler.size
    }
    private fun doubleToSure(dk:Double):Sure{
        var saat=dk.toInt()/60
        var dakika=dk.toInt()-saat*60
        return Sure(saat,dakika)
    }
}