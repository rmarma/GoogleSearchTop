package ru.rma.apps.google.search.top.core.ui.presenters

import ru.rma.apps.google.search.top.core.ui.views.BaseView

interface BasePresenter<T : BaseView> {

    fun created(view: T)
    fun attachView(view: T)
    fun detachView()
    fun destroy()
}