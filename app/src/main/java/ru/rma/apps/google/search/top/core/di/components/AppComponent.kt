package ru.rma.apps.google.search.top.core.di.components

import dagger.Component
import dagger.android.AndroidInjector
import ru.rma.apps.google.search.top.core.di.modules.AppModule
import ru.rma.apps.google.search.top.App
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}