package ru.rma.apps.google.search.top.core.data.repositories

import io.reactivex.Single
import ru.rma.apps.google.search.top.core.business.mappers.SearchResultMapper
import ru.rma.apps.google.search.top.core.data.entities.SearchResultEntity
import ru.rma.apps.google.search.top.core.data.room.AppDatabase
import javax.inject.Inject

class GoogleSearchRepositoryLocal @Inject constructor(
    private val db: AppDatabase,
    private val mapper: SearchResultMapper
) : GoogleSearchRepository {

    override fun results(query: String, limit: Int): Single<List<SearchResultEntity>> {
        return db.searchResultRoomDao().results(query, limit).map {
            mapper.roomsToResults(it)
        }
    }

    override fun saveResults(list: List<SearchResultEntity>): Single<List<SearchResultEntity>> {
        return Single.just(list).map {
            db.searchResultRoomDao().insert(mapper.resultsToRooms(it))
            it
        }
    }
}