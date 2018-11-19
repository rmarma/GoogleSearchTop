package ru.rma.apps.google.search.top.core.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.rma.apps.google.search.top.App
import ru.rma.apps.google.search.top.core.data.net.GoogleSearchApi
import ru.rma.apps.google.search.top.core.data.net.URL_GOOGLEAPIS
import ru.rma.apps.google.search.top.core.data.repositories.GoogleSearchRepository
import ru.rma.apps.google.search.top.core.data.repositories.GoogleSearchRepositoryLocal
import ru.rma.apps.google.search.top.core.data.repositories.GoogleSearchRepositoryRemote
import ru.rma.apps.google.search.top.core.data.room.AppDatabase
import ru.rma.apps.google.search.top.core.data.room.DATABASE_APP
import ru.rma.apps.google.search.top.core.di.annotations.Local
import ru.rma.apps.google.search.top.core.di.annotations.Remote
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(
    includes = [AndroidSupportInjectionModule::class,
        SchedulersModule::class,
        ActivitiesModule::class]
)
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: App): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient): GoogleSearchApi = Retrofit.Builder()
        .baseUrl(URL_GOOGLEAPIS)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
        .create(GoogleSearchApi::class.java)

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_APP)
        .build()

    @Remote
    @Provides
    @Singleton
    fun provideGoogleSearchRepositoryRemote(repository: GoogleSearchRepositoryRemote): GoogleSearchRepository =
        repository

    @Local
    @Provides
    @Singleton
    fun provideGoogleSearchRepositoryLocal(repository: GoogleSearchRepositoryLocal): GoogleSearchRepository = repository
}