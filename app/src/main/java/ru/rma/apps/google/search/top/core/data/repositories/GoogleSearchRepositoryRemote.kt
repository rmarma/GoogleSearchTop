package ru.rma.apps.google.search.top.core.data.repositories

import io.reactivex.Single
import ru.rma.apps.google.search.top.core.business.mappers.SearchResultMapper
import ru.rma.apps.google.search.top.core.data.entities.SearchResultEntity
import ru.rma.apps.google.search.top.core.data.net.GoogleSearchApi
import javax.inject.Inject

class GoogleSearchRepositoryRemote @Inject constructor(
    private val api: GoogleSearchApi,
    private val mapper: SearchResultMapper
) : GoogleSearchRepository {

    override fun results(query: String, limit: Int): Single<List<SearchResultEntity>> {
        return api.search(query, limit).map { mapper.itemsToResults(it.items) }
    }
}