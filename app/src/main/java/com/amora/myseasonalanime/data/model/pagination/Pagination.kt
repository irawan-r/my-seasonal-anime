package com.amora.myseasonalanime.data.model.pagination

import com.squareup.moshi.Json

data class Pagination(

    @Json(name = "has_next_page")
    val hasNextPage: Boolean? = null,

    @Json(name = "last_visible_page")
    val lastVisiblePage: Int? = null,

    @Json(name = "items")
    val items: PageItems? = null,

    @Json(name = "current_page")
    val currentPage: Int? = null,
)