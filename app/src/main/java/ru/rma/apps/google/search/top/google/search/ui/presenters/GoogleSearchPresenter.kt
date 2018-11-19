package ru.rma.apps.google.search.top.google.search.ui.presenters

import io.reactivex.Observable
import ru.rma.apps.google.search.top.core.ui.presenters.BasePresenter
import ru.rma.apps.google.search.top.google.search.ui.views.GoogleSearchView

interface GoogleSearchPresenter : BasePresenter<GoogleSearchView> {

    fun setup(queries: Observable<CharSequence>, clicks: Observable<Unit>)
}