package ru.rma.apps.google.search.top

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import ru.rma.apps.google.search.top.core.di.components.DaggerAppComponent

class App : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}