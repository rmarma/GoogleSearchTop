package ru.rma.apps.google.search.top.core.business.mappers

import ru.rma.apps.google.search.top.core.data.entities.SearchResultEntity
import ru.rma.apps.google.search.top.core.data.net.responses.GoogleSearchResponseItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchResultMapper @Inject constructor() {

    fun itemToResult(item: GoogleSearchResponseItem) = SearchResultEntity(item.link, item.title, item.snippet)

    fun itemsToResults(items: List<GoogleSearchResponseItem>) = items.map { itemToResult(it) }
}