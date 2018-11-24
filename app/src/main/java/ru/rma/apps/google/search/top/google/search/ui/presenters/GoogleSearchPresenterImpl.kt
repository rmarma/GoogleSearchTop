package ru.rma.apps.google.search.top.google.search.ui.presenters

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import ru.rma.apps.google.search.top.core.di.annotations.Empty
import ru.rma.apps.google.search.top.google.search.ui.cache.GoogleSearchCache
import ru.rma.apps.google.search.top.google.search.business.interactors.GoogleSearchInteractor
import ru.rma.apps.google.search.top.google.search.ui.views.GoogleSearchView
import javax.inject.Inject

class GoogleSearchPresenterImpl @Inject constructor(
        @Empty
        private val emptyView: GoogleSearchView,
        private val interactor: GoogleSearchInteractor,
        private val cache: GoogleSearchCache
) : GoogleSearchPresenter {

    private var view: GoogleSearchView = emptyView
    private val compositeDisposable = CompositeDisposable()


    override fun setup(queries: Observable<CharSequence>,
                       clicks: Observable<Unit>,
                       refreshes: Observable<Unit>) {
        compositeDisposable.add(interactor.searchResults(queries, clicks.doOnNext {
            view.showProgress()
        }, refreshes)
                .subscribe({
                    cache.results = it
                    view.hideProgress()
                    updateViewFromCache()
                }, {
                    it.printStackTrace()
                    view.hideProgress()
                }))
    }

    override fun created() {

    }

    override fun attachView(view: GoogleSearchView) {
        this.view = view
        updateViewFromCache()
    }

    override fun detachView() {
        this.view = emptyView
    }

    override fun destroy() {
        compositeDisposable.clear()
        cache.clear()
    }


    private fun updateViewFromCache() = view.searchResults(cache.results)
}