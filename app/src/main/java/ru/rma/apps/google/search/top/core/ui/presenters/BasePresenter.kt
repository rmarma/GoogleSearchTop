package ru.rma.apps.google.search.top.core.ui.presenters

import ru.rma.apps.google.search.top.core.ui.views.BaseView

interface BasePresenter<T : BaseView> {

    fun created()
    fun attachView(view: T)
    fun detachView()
    fun destroy()
}