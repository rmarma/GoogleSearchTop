package ru.rma.apps.google.search.top.core.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ru.rma.apps.google.search.top.core.di.scopes.ActivityScope
import ru.rma.apps.google.search.top.google.search.di.modules.GoogleSearchEmptyModule
import ru.rma.apps.google.search.top.google.search.di.modules.GoogleSearchModule
import ru.rma.apps.google.search.top.google.search.ui.views.GoogleSearchActivity

@Module(includes = [AndroidSupportInjectionModule::class])
interface ActivitiesModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [GoogleSearchModule::class,
            GoogleSearchEmptyModule::class]
    )
    fun googleSearchActivity(): GoogleSearchActivity
}