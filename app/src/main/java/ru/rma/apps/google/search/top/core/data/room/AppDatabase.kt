package ru.rma.apps.google.search.top.core.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.rma.apps.google.search.top.core.data.room.daos.SearchResultRoomDao
import ru.rma.apps.google.search.top.core.data.room.entities.SearchResultRoom

const val DATABASE_APP = "database_app"

@Database(entities = [SearchResultRoom::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun searchResultRoomDao(): SearchResultRoomDao
}