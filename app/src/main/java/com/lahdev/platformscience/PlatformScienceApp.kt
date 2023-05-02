package com.lahdev.platformscience

import android.app.Application
import androidx.room.Room
import com.lahdev.platformscience.model.AppDatabase

class PlatformScienceApp : Application() {

    lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()

        // Build the database
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "platform-science-db"
        ).build()
    }
}