package ru.rma.apps.google.search.top.core.data.room.daos

import androidx.room.*
import io.reactivex.Single
import ru.rma.apps.google.search.top.core.data.room.entities.COLUMN_TITLE
import ru.rma.apps.google.search.top.core.data.room.entities.SearchResultRoom
import ru.rma.apps.google.search.top.core.data.room.entities.TABLE_SEARCH_RESULTS

@Dao
interface SearchResultRoomDao {

    @Query("SELECT * FROM $TABLE_SEARCH_RESULTS")
    fun results(): Single<List<SearchResultRoom>>

    @Query("SELECT * FROM $TABLE_SEARCH_RESULTS WHERE $COLUMN_TITLE LIKE '%' || :query || '%' LIMIT :limit")
    fun results(query: String, limit: Int): Single<List<SearchResultRoom>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<SearchResultRoom>)

    @Delete
    fun delete(response: SearchResultRoom)
}
