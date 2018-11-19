package ru.rma.apps.google.search.top.google.search.business.cache

import android.util.Log
import ru.rma.apps.google.search.top.core.di.scopes.ActivityScope
import ru.rma.apps.google.search.top.google.search.ui.models.SearchResultModel
import javax.inject.Inject

@ActivityScope
class GoogleSearchCache @Inject constructor () {

    var results: List<SearchResultModel> = emptyList()

    fun clear() {
        results = emptyList()
    }
}