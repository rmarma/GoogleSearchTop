package ru.rma.apps.google.search.top.google.search.ui.presenters

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import ru.rma.apps.google.search.top.google.search.business.interactors.GoogleSearchInteractor
import ru.rma.apps.google.search.top.google.search.ui.cache.GoogleSearchCache
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
    fun presenter_destroy() {
        presenter.destroy()
        verify(cache).clear()
    }
}