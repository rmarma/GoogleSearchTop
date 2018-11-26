package ru.rma.apps.google.search.top.core.data.room.daos

import androidx.room.Room
import io.reactivex.subscribers.TestSubscriber
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import ru.rma.apps.google.search.top.core.data.room.AppDatabase
import ru.rma.apps.google.search.top.core.data.room.entities.SearchResultRoom

@RunWith(RobolectricTestRunner::class)
class SearchResultRoomDaoTests {

    private val list = listOf(
        SearchResultRoom("link1", "title1", "snippet1"),
        SearchResultRoom("link2", "title2", "snippet2"),
        SearchResultRoom("link3", "title3", "snippet3")
    )

    private lateinit var db: AppDatabase
    private lateinit var dao: SearchResultRoomDao

    @Before
    fun createDatabase() {
        db = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.systemContext,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = db.searchResultRoomDao()
    }

    @After
    fun closeDatabase() {
        db.close()
    }

    @Test
    fun insert() {
        dao.insert(list)
        val ts1 = TestSubscriber<List<SearchResultRoom>>()
        dao.results().toFlowable().subscribe(ts1)
        ts1.assertValueCount(1)
        ts1.assertValue(list)
    }

    @Test
    fun delete() {
        dao.insert(list)
        dao.delete(SearchResultRoom("link2", "title2", "snippet2"))
        val ts1 = TestSubscriber<List<SearchResultRoom>>()
        dao.results().toFlowable().subscribe(ts1)
        ts1.assertValueCount(1)
        ts1.assertValue(listOf(
            SearchResultRoom("link1", "title1", "snippet1"),
            SearchResultRoom("link3", "title3", "snippet3")
        ))
    }

    @Test
    fun query() {
        dao.insert(list)
        // запрос
        val ts1 = TestSubscriber<List<SearchResultRoom>>()
        dao.results("title2", 1).toFlowable().subscribe(ts1)
        ts1.assertValueCount(1)
        ts1.assertValue(listOf(
            SearchResultRoom("link2", "title2", "snippet2")
        ))

        // лимит
        val limit = 2
        val ts2 = TestSubscriber<List<SearchResultRoom>>()
        dao.results("title", limit).toFlowable().subscribe(ts2)
        ts2.assertValueCount(1)
        ts2.assertValue {
            it.size == limit
        }
    }
}