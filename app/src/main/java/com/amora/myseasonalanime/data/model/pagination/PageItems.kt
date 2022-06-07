package com.amora.myseasonalanime.data.model.pagination

import com.squareup.moshi.Json

data class PageItems(

    @Json(name="per_page")
    val perPage: Int? = null,

    @Json(name="total")
    val total: Int? = null,

    @Json(name="count")
    val count: Int? = null
)