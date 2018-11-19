package ru.rma.apps.google.search.top.core.data.net

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.rma.apps.google.search.top.core.data.net.responses.GoogleSearchResponse

const val URL_GOOGLEAPIS = "https://www.googleapis.com/"
private const val API_KEY = "AIzaSyD1vjTqOh0nAmci0jrGOWzAN0liU4ZPp5E"
private const val API_CX = "011292564169108028543:72bipxwuovw"

interface GoogleSearchApi {

    @GET("customsearch/v1?key=$API_KEY&cx=$API_CX")
    fun search(@Query("q") query: String, @Query("num") num: Int): Single<GoogleSearchResponse>
}