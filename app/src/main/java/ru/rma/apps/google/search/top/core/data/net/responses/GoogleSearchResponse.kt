package ru.rma.apps.google.search.top.core.data.net.responses

import com.google.gson.annotations.SerializedName

data class GoogleSearchResponse(
    @SerializedName("items")
    val items: List<GoogleSearchResponseItem>
)