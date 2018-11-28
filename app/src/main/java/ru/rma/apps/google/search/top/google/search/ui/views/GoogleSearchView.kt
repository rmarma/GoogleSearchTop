package ru.rma.apps.google.search.top.google.search.ui.views

import ru.rma.apps.google.search.top.core.ui.views.BaseView
import ru.rma.apps.google.search.top.google.search.ui.models.SearchResultModel

interface GoogleSearchView : BaseView {

    fun setupPresenter()
    fun searchResults(results: List<SearchResultModel>)
    fun showResults()
    fun hideResults()
    fun showProgress()
    fun hideProgress()
    fun showEmpty()
    fun hideEmpty()
}