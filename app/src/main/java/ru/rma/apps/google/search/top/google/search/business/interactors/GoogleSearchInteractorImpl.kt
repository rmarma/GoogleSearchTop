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

class GoogleSearchInteractorImpl @Inject constructor(
    @Ui private val uiScheduler: Scheduler,
    @Io private val ioScheduler: Scheduler,
    @Remote private val remote: GoogleSearchRepository,
    @Local private val local: GoogleSearchRepository
) : GoogleSearchInteractor {


    override fun searchResults(
        queries: Observable<CharSequence>,
        clicks: Observable<Unit>
    ): Flowable<List<SearchResultModel>> {
        return clicks.withLatestFrom(queries, BiFunction { _: Unit, query: CharSequence -> query })
            .toFlowable(BackpressureStrategy.BUFFER)
            .subscribeOn(uiScheduler)
            .observeOn(ioScheduler)
            .switchMap { query ->
                remote.results(query.toString(), 10)
                        // TODO put to local
                    .map { list -> list.map { SearchResultModel(it.link, it.title, it.snippet) } }
                    .toFlowable()
            }
//            .map { listOf(
//                SearchResultModel("1", "1"),
//                SearchResultModel("2", "2"),
//                SearchResultModel("3", "3"),
//                SearchResultModel("4", "4"),
//                SearchResultModel("5", "5"),
//                SearchResultModel("6", "6"),
//                SearchResultModel("7", "7"),
//                SearchResultModel("8", "8"),
//                SearchResultModel("9", "9"),
//                SearchResultModel("10", "10")
//            ) }
            .subscribeOn(ioScheduler)
            .observeOn(uiScheduler)
    }

}