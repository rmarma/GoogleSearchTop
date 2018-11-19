package ru.rma.apps.google.search.top.core.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.rma.apps.google.search.top.core.di.annotations.Io
import ru.rma.apps.google.search.top.core.di.annotations.Ui
import javax.inject.Singleton

@Module
class SchedulersModule {

    @Ui
    @Provides
    @Singleton
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Io
    @Provides
    @Singleton
    fun ioScheduler(): Scheduler = Schedulers.io()
}