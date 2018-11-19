package ru.rma.apps.google.search.top.core.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val TABLE_SEARCH_RESULTS = "search_results"
const val COLUMN_LINK = "link"
const val COLUMN_TITLE = "title"
const val COLUMN_SNIPPET = "snippet"

@Entity(tableName = TABLE_SEARCH_RESULTS)
data class SearchResultRoom(
    @PrimaryKey
    @ColumnInfo(name = COLUMN_LINK) var link: String,
    @ColumnInfo(name = COLUMN_TITLE, index = true) var title: String = "",
    @ColumnInfo(name = COLUMN_SNIPPET) var snippet: String = ""
)