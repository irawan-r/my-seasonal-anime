package com.amora.myseasonalanime.data

import com.amora.myseasonalanime.data.source.remote.response.detail.DataItem

/**
 *  The function is simply to get object from data object (JSON)
*/
interface DataSource {
    suspend fun getSeasonNow(): List<DataItem>
}