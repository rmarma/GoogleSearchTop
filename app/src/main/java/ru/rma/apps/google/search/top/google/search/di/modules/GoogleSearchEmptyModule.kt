package ru.rma.apps.google.search.top.google.search.di.modules

import dagger.Module
import dagger.Provides
import ru.rma.apps.google.search.top.core.di.annotations.Empty
import ru.rma.apps.google.search.top.core.di.scopes.ActivityScope
import ru.rma.apps.google.search.top.google.search.ui.views.GoogleSearchEmpty
import ru.rma.apps.google.search.top.google.search.ui.views.GoogleSearchView

@Module
class GoogleSearchEmptyModule {

    @Empty
    @Provides
    @ActivityScope
    fun provideViewEmpty(): GoogleSearchView = GoogleSearchEmpty()
}