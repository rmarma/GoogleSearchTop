package ru.rma.apps.google.search.top.google.search.ui.views

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import kotlinx.android.synthetic.main.activity_google_search.*
import ru.rma.apps.google.search.top.R
import ru.rma.apps.google.search.top.core.ui.views.BaseActivity
import ru.rma.apps.google.search.top.google.search.ui.adapters.SearchResultAdapter
import ru.rma.apps.google.search.top.google.search.ui.models.SearchResultModel
import ru.rma.apps.google.search.top.google.search.ui.presenters.GoogleSearchPresenter
import javax.inject.Inject

class GoogleSearchActivity : BaseActivity(), GoogleSearchView {

    @Inject
    lateinit var presenter: GoogleSearchPresenter
    @Inject
    lateinit var adapter: SearchResultAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_search)

        recyclerViewSearch?.let {
            it.setHasFixedSize(true)
            it.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
            it.adapter = adapter
        }
        presenter.setup(inputTextSearch.textChanges().share(), buttonSearch.clicks().share())
        presenter.created()
    }

    override fun onStart() {
        super.onStart()

        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()

        presenter.detachView()
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.destroy()
    }


    override fun searchResults(results: List<SearchResultModel>) {
        adapter.itmes(results)
    }
}
