package ru.rma.apps.google.search.top.core.business.mappers

import org.junit.Assert.*
import org.junit.Test
import ru.rma.apps.google.search.top.core.data.entities.SearchResultEntity
import ru.rma.apps.google.search.top.core.data.net.responses.GoogleSearchResponseItem
import ru.rma.apps.google.search.top.core.data.room.entities.SearchResultRoom

class SearchResultMapperTests {

    private val mapper = SearchResultMapper()

    @Test
    fun itemToResult() {
        val item = GoogleSearchResponseItem("link", "title", "snippet")
        val result = mapper.itemToResult(item)
        assertEquals(item.link, result.link)
        assertEquals(item.title, result.title)
        assertEquals(item.snippet, result.snippet)
    }

    @Test
    fun itemsToResults() {
        val items = listOf(
            GoogleSearchResponseItem("link1", "title1", "snippet1"),
            GoogleSearchResponseItem("link2", "title2", "snippet2"),
            GoogleSearchResponseItem("link3", "title3", "snippet3")
        )
        val results = mapper.itemsToResults(items)

        assertEquals(items.size, results.size)
        for (entry in items.withIndex()) {
            results[entry.index].let { result ->
                assertEquals(entry.value.link, result.link)
                assertEquals(entry.value.title, result.title)
                assertEquals(entry.value.snippet, result.snippet)
            }
        }
    }


    @Test
    fun roomToResult() {
        val item = SearchResultRoom("link", "title", "snippet")
        val result = mapper.roomToResult(item)
        assertEquals(item.link, result.link)
        assertEquals(item.title, result.title)
        assertEquals(item.snippet, result.snippet)
    }

    @Test
    fun roomsToResults() {
        val items = listOf(
            SearchResultRoom("link1", "title1", "snippet1"),
            SearchResultRoom("link2", "title2", "snippet2"),
            SearchResultRoom("link3", "title3", "snippet3")
        )
        val results = mapper.roomsToResults(items)

        assertEquals(items.size, results.size)
        for (entry in items.withIndex()) {
            results[entry.index].let { result ->
                assertEquals(entry.value.link, result.link)
                assertEquals(entry.value.title, result.title)
                assertEquals(entry.value.snippet, result.snippet)
            }
        }
    }


    @Test
    fun resultToRoom() {
        val item = SearchResultEntity("link", "title", "snippet")
        val result = mapper.resultToRoom(item)
        assertEquals(item.link, result.link)
        assertEquals(item.title, result.title)
        assertEquals(item.snippet, result.snippet)
    }

    @Test
    fun resultsToRooms() {
        val items = listOf(
            SearchResultEntity("link1", "title1", "snippet1"),
            SearchResultEntity("link2", "title2", "snippet2"),
            SearchResultEntity("link3", "title3", "snippet3")
        )
        val results = mapper.resultsToRooms(items)

        assertEquals(items.size, results.size)
        for (entry in items.withIndex()) {
            results[entry.index].let { result ->
                assertEquals(entry.value.link, result.link)
                assertEquals(entry.value.title, result.title)
                assertEquals(entry.value.snippet, result.snippet)
            }
        }
    }
}