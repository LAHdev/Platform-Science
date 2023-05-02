package com.lahdev.platformscience.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.lahdev.platformscience.PlatformScienceApp
import com.lahdev.platformscience.R
import com.lahdev.platformscience.model.AppDatabase
import com.lahdev.platformscience.util.FileDataExtractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class MainActivity : AppCompatActivity() {
    private lateinit var model: FileDataExtractor
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = (applicationContext as PlatformScienceApp).db
        CoroutineScope(Dispatchers.IO).launch {

            //This helps to avoid to insert the data from the same day
            if (db.shipmentDao().getLastUpdate() != LocalDate.now()) {
                model = FileDataExtractor()
                loadData()
            }
        }

        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun loadData() {
        model.loadFromFile(applicationContext)
        db.driverDao().insertAll(model.getDrivers())
        db.shipmentDao().insertAll(model.getShipments())
    }
}