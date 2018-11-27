package ru.rma.apps.google.search.top.google.search.business.interactors

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import ru.rma.apps.google.search.top.core.data.entities.SearchResultEntity
import ru.rma.apps.google.search.top.core.data.repositories.GoogleSearchRepository
import ru.rma.apps.google.search.top.google.search.ui.models.SearchResultModel

class GoogleSearchInteractorImplTests {

    private val remote = mock(GoogleSearchRepository::class.java)
    private val local = mock(GoogleSearchRepository::class.java)
    private val uiScheduler = Schedulers.trampoline()
    private val ioScheduler = Schedulers.trampoline()
    private val interactor = GoogleSearchInteractorImpl(
        uiScheduler = uiScheduler,
        ioScheduler = ioScheduler,
        remote = remote,
        local = local
    )

    @Test
    fun interactor_remoteSuccess() {
        val listRemote = listOf(
            SearchResultEntity("remote1", "query1"),
            SearchResultEntity("remote2", "query2"),
            SearchResultEntity("remote3", "query3")
        )
        val listLocal = listOf(
            SearchResultEntity("local1", "query1"),
            SearchResultEntity("local2", "query2"),
            SearchResultEntity("local3", "query3")
        )
        `when`(remote.results("query", LIMIT)).thenReturn(Single.just(listRemote))
        `when`(local.results("query", LIMIT)).thenReturn(Single.just(listLocal))
        `when`(local.saveResults(listRemote)).thenReturn(Single.just(listRemote))

        val queries: PublishSubject<CharSequence> = PublishSubject.create()
        val clicks: PublishSubject<Unit> = PublishSubject.create()
        val refreshes: PublishSubject<Unit> = PublishSubject.create()

        val testSubscriber = TestSubscriber<List<SearchResultModel>>()

        interactor.searchResults(queries, clicks, refreshes).subscribe(testSubscriber)
        queries.onNext("query")
        clicks.onNext(Unit)
        testSubscriber.assertValueCount(1)
        testSubscriber.assertValue(listOf(
            SearchResultModel("remote1", "query1"),
            SearchResultModel("remote2", "query2"),
            SearchResultModel("remote3", "query3")
        ))
    }

    @Test
    fun interactor_localSuccess() {
        val listRemote = emptyList<SearchResultEntity>()
        val listLocal = listOf(
            SearchResultEntity("local1", "query1"),
            SearchResultEntity("local2", "query2"),
            SearchResultEntity("local3", "query3")
        )
        `when`(remote.results("query", LIMIT)).thenReturn(Single.just(listRemote))
        `when`(local.results("query", LIMIT)).thenReturn(Single.just(listLocal))
        `when`(local.saveResults(listRemote)).thenReturn(Single.just(listRemote))

        val queries: PublishSubject<CharSequence> = PublishSubject.create()
        val clicks: PublishSubject<Unit> = PublishSubject.create()
        val refreshes: PublishSubject<Unit> = PublishSubject.create()

        val testSubscriber = TestSubscriber<List<SearchResultModel>>()

        interactor.searchResults(queries, clicks, refreshes).subscribe(testSubscriber)
        queries.onNext("query")
        clicks.onNext(Unit)
        testSubscriber.assertValueCount(1)
        testSubscriber.assertValue(listOf(
            SearchResultModel("local1", "query1"),
            SearchResultModel("local2", "query2"),
            SearchResultModel("local3", "query3")
        ))
    }
}