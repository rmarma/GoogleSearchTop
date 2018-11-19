package ru.rma.apps.google.search.top.google.search.business.interactors

import io.reactivex.Flowable
import io.reactivex.Observable
import ru.rma.apps.google.search.top.google.search.ui.models.SearchResultModel

interface GoogleSearchInteractor {

    fun searchResults(
        queries: Observable<CharSequence>,
        clicks: Observable<Unit>
    ): Flowable<List<SearchResultModel>>
}