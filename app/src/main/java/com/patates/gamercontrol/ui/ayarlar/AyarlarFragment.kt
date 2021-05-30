package com.patates.gamercontrol.ui.ayarlar

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.patates.gamercontrol.R
import com.patates.gamercontrol.ui.yardimciSiniflar.*
import kotlinx.android.synthetic.main.fragment_ayarlar.*


class AyarlarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ayarlar,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            ZilSesiButton.setOnClickListener {
                sesSec()
            }
            verilerisilbtn.setOnClickListener {
                alert(it)
            }
            seekBar.max=100
            seekBar.progress=Sp.get("sesDuzeyi",it,100)
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                context?.let {
                    Sp.add("sesDuzeyi",seekBar.progress,it)
                }
            }
        })
    }

    fun sesSec(){
        activity?.let {

            if(ContextCompat.checkSelfPermission(it.applicationContext,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)
            }else
            {
                var soundIntent=Intent(Intent.ACTION_PICK,MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(soundIntent,2)
            }
        }
    }
    fun sehirSec(sehir:String){
        context?.let {
            Sp.add("sehir",sehir,it)
        }
    }
    fun allDataRemove(view: View){
        context?.let {
            Sp.allDataRemove(it)
            Db.allDataRemove(it)
            var action=AyarlarFragmentDirections.actionNavAyarlarToNavKutuphane()
            Navigation.findNavController(view).navigate(action)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode==1){
            if(grantResults.size>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                var soundIntent=Intent(Intent.ACTION_PICK,MediaStore.Audio.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(soundIntent,2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode==2 && resultCode==Activity.RESULT_OK &&data!=null){
            context?.let {
                Sp.add("sesUri",data.data.toString(),it)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
    fun alert(view: View){
        context?.let {
            val alertMassage = AlertDialog.Builder(context)
            alertMassage.setTitle("Uyarı")
            alertMassage.setMessage("Silme İşlemine devam etmek isyor musun")

            alertMassage.setPositiveButton("Onayla",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    allDataRemove(view)
                    Toast.makeText(context,"silindi",Toast.LENGTH_LONG).show()
                })
            alertMassage.setNegativeButton("İptal",DialogInterface.OnClickListener { dialogInterface, i ->
                Toast.makeText(context,"İşlem iptal edildi",Toast.LENGTH_LONG).show()
            })
            alertMassage.show()
        }
    }
}
