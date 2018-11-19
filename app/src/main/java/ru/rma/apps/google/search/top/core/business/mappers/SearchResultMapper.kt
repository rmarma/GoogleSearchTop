package ru.rma.apps.google.search.top.core.business.mappers

import ru.rma.apps.google.search.top.core.data.entities.SearchResultEntity
import ru.rma.apps.google.search.top.core.data.net.responses.GoogleSearchResponseItem
import ru.rma.apps.google.search.top.core.data.room.entities.SearchResultRoom
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchResultMapper @Inject constructor() {

    fun itemToResult(item: GoogleSearchResponseItem) = SearchResultEntity(item.link, item.title, item.snippet)

    fun itemsToResults(items: List<GoogleSearchResponseItem>) = items.map { itemToResult(it) }


    fun roomToResult(room: SearchResultRoom) = SearchResultEntity(room.link, room.title, room.snippet)

    fun roomsToResults(list: List<SearchResultRoom>) = list.map { roomToResult(it) }


    fun resultToRoom(item: SearchResultEntity) = SearchResultRoom(item.link, item.title, item.snippet)

    fun resultsToRooms(list: List<SearchResultEntity>) = list.map { resultToRoom(it) }
}