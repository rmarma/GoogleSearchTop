package ru.rma.apps.google.search.top.core.data.net.responses

import com.google.gson.annotations.SerializedName

data class GoogleSearchResponseItem(
    @SerializedName("link")
    val link: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("snippet")
    val snippet: String
)