package ru.rma.apps.google.search.top.google.search.ui.cache

import androidx.lifecycle.ViewModel
import ru.rma.apps.google.search.top.google.search.ui.models.SearchResultModel

class GoogleSearchViewModel : ViewModel() {

    var results: List<SearchResultModel> = emptyList()

    fun clear() {
        results = emptyList()
    }
}