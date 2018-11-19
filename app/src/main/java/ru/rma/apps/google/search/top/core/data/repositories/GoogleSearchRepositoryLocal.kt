package ru.rma.apps.google.search.top.core.data.repositories

import io.reactivex.Single
import ru.rma.apps.google.search.top.core.data.entities.SearchResultEntity
import ru.rma.apps.google.search.top.core.data.room.AppDatabase
import javax.inject.Inject

class GoogleSearchRepositoryLocal @Inject constructor(
    private val db: AppDatabase
) : GoogleSearchRepository {


    override fun results(query: String, limit: Int): Single<List<SearchResultEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}