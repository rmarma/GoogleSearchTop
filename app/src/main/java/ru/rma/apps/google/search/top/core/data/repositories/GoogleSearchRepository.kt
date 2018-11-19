package ru.rma.apps.google.search.top.core.data.repositories

import io.reactivex.Single
import ru.rma.apps.google.search.top.core.data.entities.SearchResultEntity

interface GoogleSearchRepository {

    fun results(query: String, limit: Int): Single<List<SearchResultEntity>>
}