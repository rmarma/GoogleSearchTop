package ru.rma.apps.google.search.top.google.search.ui.presenters

import org.junit.Test
import org.mockito.Mockito.*
import ru.rma.apps.google.search.top.google.search.business.interactors.GoogleSearchInteractor
import ru.rma.apps.google.search.top.google.search.ui.cache.GoogleSearchCache
import ru.rma.apps.google.search.top.google.search.ui.models.SearchResultModel
import ru.rma.apps.google.search.top.google.search.ui.views.GoogleSearchView

class GoogleSearchPresenterImplTests {

    private val interactor = mock(GoogleSearchInteractor::class.java)
    private val view = mock(GoogleSearchView::class.java)
    private val viewEmpty = mock(GoogleSearchView::class.java)
    private val cache = mock(GoogleSearchCache::class.java)

    private val presenter = GoogleSearchPresenterImpl(
            emptyView = viewEmpty,
            interactor = interactor,
            cache = cache
    )

    @Test
    fun attachView_cacheEmpty() {
        val list = emptyList<SearchResultModel>()
        `when`(cache.results).thenReturn(list)
        presenter.attachView(view)
        verify(view).searchResults(emptyList())
        verify(view).hideProgress()
        verify(view).hideResults()
        verify(view).showEmpty()
    }

    @Test
    fun attachView_cacheIsNotEmpty() {
        val list = listOf(
                SearchResultModel("link1"),
                SearchResultModel("link2")
        )
        `when`(cache.results).thenReturn(list)
        presenter.attachView(view)
        verify(view).searchResults(emptyList())
        verify(view).hideProgress()
        verify(view).showResults()
        verify(view).hideEmpty()
    }

    @Test
    fun destroy_clearCache() {
        presenter.destroy()
        verify(cache).clear()
    }
}