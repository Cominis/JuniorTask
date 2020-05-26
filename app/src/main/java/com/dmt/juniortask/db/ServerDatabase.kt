package com.dmt.juniortask.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ServerEntity::class], version = 2, exportSchema = false)
abstract class ServerDatabase : RoomDatabase() {
    abstract val serverDao: ServerDao
}