package ru.rma.apps.google.search.top.google.search.ui.presenters

import io.reactivex.Observable
import ru.rma.apps.google.search.top.core.ui.presenters.BasePresenter
import ru.rma.apps.google.search.top.google.search.ui.cache.GoogleSearchViewModel
import ru.rma.apps.google.search.top.google.search.ui.views.GoogleSearchView

interface GoogleSearchPresenter : BasePresenter<GoogleSearchView> {

    fun cache(cache: GoogleSearchViewModel)

    fun setup(queries: Observable<CharSequence>,
              clicks: Observable<Unit>,
              refreshes: Observable<Unit>)
}