package com.example.randomalarmclock

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.randomalarmclock.alarmsDatabase.AlarmsDao
import com.example.randomalarmclock.alarmsDatabase.AlarmsInfo


@Database(entities = [AlarmsInfo::class], version = 1)
abstract class AlarmAppDB : RoomDatabase() {

    abstract fun alarmsDao(): AlarmsDao

    companion object {

        @Volatile
        private var INSTANCE: AlarmAppDB? = null

        fun getDatabase(context: Context?): AlarmAppDB? {
            if (INSTANCE == null) {
                synchronized(AlarmAppDB::class) {
                    context?.run {
                        INSTANCE = Room.databaseBuilder(
                            applicationContext,
                            AlarmAppDB::class.java,
                            "alarms_database"
                        ).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}
