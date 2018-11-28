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


    override fun created(view: GoogleSearchView) {
        this.view = view
        // setup view
        this.view.hideProgress()
        this.view.showResults()
        this.view.hideEmpty()
        // setup presenter
        this.view.setupPresenter()
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

    override fun setup(queries: Observable<CharSequence>,
                       clicks: Observable<Unit>,
                       refreshes: Observable<Unit>) {
        // очищаем прошлые подписки
        compositeDisposable.clear()
        // подписываемся
        compositeDisposable.add(
                interactor.searchResults(
                        queries,
                        clicks.doOnNext {
                            view.showProgress()
                            view.showResults()
                            view.hideEmpty()
                        },
                        refreshes.doOnNext {
                            view.showResults()
                            view.hideEmpty()
                        }
                )
                        .subscribe({
                            cache.results = it
                            updateViewFromCache()
                        }, {
                            // TODO Handle Error
                            view.setupPresenter()
                        })
        )
    }


    private fun updateViewFromCache() {
        val list = cache.results
        view.searchResults(list)
        view.hideProgress()
        if (list.isEmpty()) {
            view.hideResults()
            view.showEmpty()
        } else {
            view.showResults()
            view.hideEmpty()
        }
    }
}