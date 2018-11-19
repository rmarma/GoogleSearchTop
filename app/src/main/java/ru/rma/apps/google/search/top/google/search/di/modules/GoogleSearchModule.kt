package ru.rma.apps.google.search.top.google.search.di.modules

import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.rma.apps.google.search.top.core.di.annotations.Empty
import ru.rma.apps.google.search.top.core.di.scopes.ActivityScope
import ru.rma.apps.google.search.top.google.search.business.interactors.GoogleSearchInteractor
import ru.rma.apps.google.search.top.google.search.business.interactors.GoogleSearchInteractorImpl
import ru.rma.apps.google.search.top.google.search.ui.presenters.GoogleSearchPresenter
import ru.rma.apps.google.search.top.google.search.ui.presenters.GoogleSearchPresenterImpl
import ru.rma.apps.google.search.top.google.search.ui.views.GoogleSearchActivity
import ru.rma.apps.google.search.top.google.search.ui.views.GoogleSearchEmpty
import ru.rma.apps.google.search.top.google.search.ui.views.GoogleSearchView

@Module
interface GoogleSearchModule {

    @Binds
    @ActivityScope
    fun view(view: GoogleSearchActivity): GoogleSearchView

    @Binds
    @ActivityScope
    fun presenter(presenter: GoogleSearchPresenterImpl): GoogleSearchPresenter

    @Binds
    @ActivityScope
    fun interactor(interactor: GoogleSearchInteractorImpl): GoogleSearchInteractor
}