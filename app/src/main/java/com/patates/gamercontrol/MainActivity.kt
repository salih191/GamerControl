package com.patates.gamercontrol

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.patates.gamercontrol.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_kutuphane, R.id.nav_istatistik, R.id.nav_ayarlar
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        sql()


    }

    override fun onResume() {
        super.onResume()
        println("ressume")
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    fun sql(): Unit {
        try {
            val db=this.openOrCreateDatabase("Games", Context.MODE_PRIVATE,null)

            db.execSQL("CREATE TABLE IF NOT EXISTS \"Times\" (\n" +
                    "\t\"TimeID\"\tINTEGER,\n" +
                    "\t\"GameID\"\tINTEGER,\n" +
                    "\t\"StarTime\"\tTEXT,\n" +
                    "\t\"StopTime\"\tTEXT," +
                    "\tFOREIGN KEY(\"GameID\") REFERENCES \"Games\"(\"GameID\"),\n" +
                    "\tPRIMARY KEY(\"TimeID\" AUTOINCREMENT)\n" +
                    ");\n")

            db.execSQL("CREATE TABLE IF NOT EXISTS\"Games\" (" +
                    "\"GameID\"INTEGER," +
                    "\"GameName\"TEXT," +
                    "\"GameImageId\"INTEGER," +
                    "PRIMARY KEY(\"GameID\" AUTOINCREMENT)" +
                    ");")
            db.close()
        }catch (e:Exception){println(e.message)}
    }
}