package ru.rma.apps.google.search.top.google.search.business.interactors

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.functions.BiFunction
import ru.rma.apps.google.search.top.core.data.repositories.GoogleSearchRepository
import ru.rma.apps.google.search.top.core.di.annotations.Io
import ru.rma.apps.google.search.top.core.di.annotations.Local
import ru.rma.apps.google.search.top.core.di.annotations.Remote
import ru.rma.apps.google.search.top.core.di.annotations.Ui
import ru.rma.apps.google.search.top.google.search.ui.models.SearchResultModel
import javax.inject.Inject

const val LIMIT = 10

class GoogleSearchInteractorImpl @Inject constructor(
        @Ui private val uiScheduler: Scheduler,
        @Io private val ioScheduler: Scheduler,
        @Remote private val remote: GoogleSearchRepository,
        @Local private val local: GoogleSearchRepository
) : GoogleSearchInteractor {


    override fun searchResults(
            queries: Observable<CharSequence>,
            clicks: Observable<Unit>,
            refreshes: Observable<Unit>
    ): Flowable<List<SearchResultModel>> {
        return clicks.mergeWith(refreshes)
                .withLatestFrom(queries, BiFunction { _: Unit, query: CharSequence -> query.toString().trim() })
                .toFlowable(BackpressureStrategy.BUFFER)
                .subscribeOn(uiScheduler)
                .observeOn(ioScheduler)
                .switchMap { query ->
                    if (query.isEmpty()) {
                        Flowable.just(emptyList())
                    } else {
                        remote.results(query, LIMIT)
                                .onErrorReturnItem(emptyList())
                                .flatMap { list ->
                                    if (list.isEmpty()) {
                                        local.results(query, LIMIT)
                                    } else {
                                        local.saveResults(list)
                                    }
                                }
                                .toFlowable()
                    }
                }
                .map { list -> list.map { SearchResultModel(it.link, it.title, it.snippet) } }
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
    }
}
