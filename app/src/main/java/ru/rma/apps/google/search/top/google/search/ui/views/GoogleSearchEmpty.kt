package ru.rma.apps.google.search.top.google.search.ui.views

import ru.rma.apps.google.search.top.google.search.ui.models.SearchResultModel

class GoogleSearchEmpty : GoogleSearchView {

    override fun searchResults(results: List<SearchResultModel>) {
        // ничего не делаем
    }

    override fun showResults() {
        // ничего не делаем
    }

    override fun hideResults() {
        // ничего не делаем
    }

    override fun showProgress() {
        // ничего не делаем
    }

    override fun hideProgress() {
        // ничего не делаем
    }

    override fun showEmpty() {
        // ничего не делаем
    }

    override fun hideEmpty() {
        // ничего не делаем
    }
}