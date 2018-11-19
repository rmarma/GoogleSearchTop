package ru.rma.apps.google.search.top.core.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.rma.apps.google.search.top.core.data.room.daos.SearchResultRoomDao
import ru.rma.apps.google.search.top.core.data.room.entities.SearchResponseRoom

const val DATABASE_APP = "database_app"

@Database(entities = arrayOf(SearchResponseRoom::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun searchResponseRoomDao(): SearchResultRoomDao
}