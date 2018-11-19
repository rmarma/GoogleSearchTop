package ru.rma.apps.google.search.top.core.data.room.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single
import ru.rma.apps.google.search.top.core.data.room.entities.COLUMN_TITLE
import ru.rma.apps.google.search.top.core.data.room.entities.SearchResultRoom
import ru.rma.apps.google.search.top.core.data.room.entities.TABLE_SEARCH_RESULTS

@Dao
interface SearchResultRoomDao {

    @Query("SELECT * FROM $TABLE_SEARCH_RESULTS")
    fun results(): Single<List<SearchResultRoom>>

    @Query("SELECT * FROM $TABLE_SEARCH_RESULTS")
    fun results(query: String, limit: Int): Single<List<SearchResultRoom>>

    @Insert
    fun insertAll(vararg responses: SearchResultRoom)

    @Delete
    fun delete(response: SearchResultRoom)

}